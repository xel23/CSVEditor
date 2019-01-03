package csv.psi;

import com.intellij.psi.tree.IElementType;
import csv.CsvLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CsvTokenType extends IElementType {
    public CsvTokenType(@NotNull @NonNls String debugName) {
        super(debugName, CsvLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "CsvTokenType." + super.toString();
    }
}