package csv.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.resolve.FileContextUtil;
import csv.settings.CsvCodeStyleSettings;
import csv.psi.CsvTypes;

public final class CsvParserUtil {

    private CsvParserUtil() {
        // static utility class
    }

    public static boolean separator(PsiBuilder builder, int tokenType) {
        if (builder.getTokenType() == CsvTypes.COMMA) {
            PsiFile currentFile = builder.getUserDataUnprotected(FileContextUtil.CONTAINING_FILE_KEY);
            return builder.getTokenText().equals(
                    CsvCodeStyleSettings.getCurrentSeparator(builder.getProject(), currentFile != null ? currentFile.getLanguage() : null)
            );
        }
        return false;
    }

}