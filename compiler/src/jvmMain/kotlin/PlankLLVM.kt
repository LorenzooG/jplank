package com.lorenzoog.jplank.compiler

import com.lorenzoog.jplank.analyzer.BindingContext
import com.lorenzoog.jplank.compiler.instructions.EntryPoint
import com.lorenzoog.jplank.element.PlankFile
import io.vexelabs.bitbuilder.llvm.ir.Module
import io.vexelabs.bitbuilder.llvm.ir.Value
import org.bytedeco.llvm.global.LLVM
import org.bytedeco.llvm.global.LLVM.LLVMInitializeNativeAsmParser
import org.bytedeco.llvm.global.LLVM.LLVMInitializeNativeAsmPrinter
import org.bytedeco.llvm.global.LLVM.LLVMInitializeNativeDisassembler
import org.bytedeco.llvm.global.LLVM.LLVMInitializeNativeTarget
import org.bytedeco.llvm.global.LLVM.LLVMLinkInMCJIT
import pw.binom.io.Closeable

class PlankLLVM(
    private val modules: List<PlankFile>,
    private val bindingContext: BindingContext
) : Closeable {
  lateinit var context: PlankContext
    private set

  private val instructionMapper = InstructionMapper(TypeMapper(), bindingContext)

  fun initialize(file: PlankFile) {
    LLVMLinkInMCJIT()
    LLVMInitializeNativeAsmPrinter()
    LLVMInitializeNativeAsmParser()
    LLVMInitializeNativeDisassembler()
    LLVMInitializeNativeTarget()

    val module = Module(LLVM.LLVMModuleCreateWithName(file.module))

    context = PlankContext.of(file, instructionMapper, bindingContext, module)
  }

  fun compile(file: PlankFile): List<Value> {
    return (modules + file)
      .flatMap { module ->
        val moduleContext = context.createScope(module)

        context.map(module.program).map { it.codegen(moduleContext) }
      }
      .filterNotNull() + EntryPoint().codegen(context)
  }

  override fun close() {
  }
}
