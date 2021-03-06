// This is a generated file. Not intended for manual editing.
package csv.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static csv.psi.CsvTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import csv.psi.*;

public class CsvFieldImpl extends ASTWrapperPsiElement implements CsvField {

  public CsvFieldImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CsvVisitor visitor) {
    visitor.visitField(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CsvVisitor) accept((CsvVisitor)visitor);
    else super.accept(visitor);
  }

}
