// This is a generated file. Not intended for manual editing.
package com.lorenzoog.jplank.intellijplugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.lorenzoog.jplank.intellijplugin.psi.PlankTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.lorenzoog.jplank.intellijplugin.psi.*;

public class PlankFunHeaderImpl extends ASTWrapperPsiElement implements PlankFunHeader {

  public PlankFunHeaderImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PlankVisitor visitor) {
    visitor.visitFunHeader(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PlankVisitor) accept((PlankVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<PlankParameter> getParameterList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PlankParameter.class);
  }

  @Override
  @NotNull
  public PlankTypeDef getTypeDef() {
    return findNotNullChildByClass(PlankTypeDef.class);
  }

  @Override
  public @NotNull String getName() {
    return PlankPsiImplUtil.getName(this);
  }

  @Override
  public @NotNull PsiElement setName(String newName) {
    return PlankPsiImplUtil.setName(this, newName);
  }

}
