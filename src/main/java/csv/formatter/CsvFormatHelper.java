package csv.formatter;

import com.intellij.formatting.Block;
import com.intellij.psi.formatter.common.AbstractBlock;
import csv.psi.CsvTypes;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public final class CsvFormatHelper {

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

    private CsvFormatHelper() {
        // static utility class
    }
}