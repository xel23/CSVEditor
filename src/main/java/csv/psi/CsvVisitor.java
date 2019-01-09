// This is a generated file. Not intended for manual editing.
package csv.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class CsvVisitor extends PsiElementVisitor {

  public void visitField(@NotNull CsvField o) {
    visitPsiElement(o);
  }

  public void visitRecord(@NotNull CsvRecord o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}