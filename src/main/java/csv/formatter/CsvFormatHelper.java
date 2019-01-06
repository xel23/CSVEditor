package csv.formatter;

import com.intellij.formatting.Block;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.common.AbstractBlock;
import csv.psi.CsvTypes;
import csv.settings.CsvCodeStyleSettings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public final class CsvFormatHelper {

    @Contract("null -> false")
    public static boolean isQuotedField(@Nullable CsvBlock block) {
        if (block != null && block.getNode().getElementType() == CsvTypes.FIELD) {
            List<Block> subBlocks = block.buildChildren();
            if (subBlocks.size() > 0) {
                AbstractBlock abstractBlock = (AbstractBlock) subBlocks.get(0);
                return abstractBlock.getNode().getElementType() == CsvTypes.QUOTE;
            }
        }
        return false;
    }

    public static int getTextLength(@NotNull ASTNode node, @NotNull CodeStyleSettings codeStyleSettings) {
        CsvCodeStyleSettings csvCodeStyleSettings = codeStyleSettings.getCustomSettings(CsvCodeStyleSettings.class);
        String text = node.getText();
        int length = 0;
        if (csvCodeStyleSettings.TABULARIZE && !csvCodeStyleSettings.WHITE_SPACES_OUTSIDE_QUOTES &&
                text.startsWith("\"")) {
            text = text.substring(1, text.length() - 1);
            length += 2;
        }
        length += text.length();

        return length;
    }

    private CsvFormatHelper() {
        // static utility class
    }
}