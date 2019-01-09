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

public class CsvRecordImpl extends ASTWrapperPsiElement implements CsvRecord {

  public CsvRecordImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CsvVisitor visitor) {
    visitor.visitRecord(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CsvVisitor) accept((CsvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CsvField> getFieldList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CsvField.class);
  }

}
