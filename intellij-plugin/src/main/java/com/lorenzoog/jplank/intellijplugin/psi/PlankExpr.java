// This is a generated file. Not intended for manual editing.
package com.lorenzoog.jplank.intellijplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface PlankExpr extends PsiElement {

  @Nullable
  PlankAssignExpr getAssignExpr();

  @Nullable
  PlankIfExpr getIfExpr();

  @Nullable
  PlankInstanceExpr getInstanceExpr();

  @Nullable
  PlankSizeofExpr getSizeofExpr();

}
