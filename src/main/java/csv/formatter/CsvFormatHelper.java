package csv.formatter;

import com.intellij.formatting.Block;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.common.AbstractBlock;
import csv.CsvColumnInfo;
import csv.CsvLanguage;
import csv.psi.CsvTypes;
import  csv.psi.CsvElementType;
import csv.settings.CsvCodeStyleSettings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    public static SpacingBuilder createSpaceBuilder(@NotNull CodeStyleSettings settings) {
        CsvCodeStyleSettings csvCodeStyleSettings = settings.getCustomSettings(CsvCodeStyleSettings.class);
        SpacingBuilder builder = new SpacingBuilder(settings, CsvLanguage.INSTANCE);
        if (csvCodeStyleSettings.TABULARIZE) {
            builder
                    .after(CsvTypes.COMMA).spaceIf(csvCodeStyleSettings.SPACE_AFTER_SEPARATOR)
                    .after(CsvTypes.CRLF).spaces(0)
                    .after(CsvElementType.DOCUMENT_START).spaces(0);
            if (csvCodeStyleSettings.TABULARIZE && !csvCodeStyleSettings.WHITE_SPACES_OUTSIDE_QUOTES) {
                builder.before(CsvTypes.QUOTE).spaces(0);
            }
        } else if (csvCodeStyleSettings.SPACE_AFTER_SEPARATOR) {
            builder.after(CsvTypes.COMMA).spaces(1);
        }

        if (csvCodeStyleSettings.TABULARIZE) {
            builder
                    .before(CsvTypes.COMMA).spaceIf(csvCodeStyleSettings.SPACE_BEFORE_SEPARATOR)
                    .before(CsvTypes.CRLF).spaces(0);
            if (csvCodeStyleSettings.TABULARIZE && !csvCodeStyleSettings.WHITE_SPACES_OUTSIDE_QUOTES) {
                builder.after(CsvTypes.QUOTE).spaces(0);
            }
        } else if (csvCodeStyleSettings.SPACE_BEFORE_SEPARATOR) {
            builder.before(CsvTypes.COMMA).spaces(1);
        }

        return builder;
    }

    public static ASTNode getRoot(final ASTNode node) {
        ASTNode currentNode = node;
        ASTNode parent;
        while ((parent = currentNode.getTreeParent()) != null) {
            currentNode = parent;
        }
        return currentNode;
    }

    public static Map<Integer, CsvColumnInfo<ASTNode>> createColumnInfoMap(@NotNull ASTNode root,
                                                                           CodeStyleSettings settings) {
        Map<Integer, CsvColumnInfo<ASTNode>> columnInfoMap = new HashMap<>();
        ASTNode child = root.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() == CsvTypes.RECORD) {
                Integer column = 0;
                ASTNode subChild = child.getFirstChildNode();
                while (subChild != null) {
                    if (subChild.getElementType() == CsvTypes.FIELD) {
                        int length = getTextLength(subChild, settings);
                        if (!columnInfoMap.containsKey(column)) columnInfoMap.put(column, new CsvColumnInfo(length));
                        else if (columnInfoMap.get(column).getMaxLength() < length) {
                            columnInfoMap.get(column).setMaxLength(length);
                        }
                        columnInfoMap.get(column).addElement(subChild);
                        ++column;
                    }
                    subChild = subChild.getTreeNext();
                }
            }
            child = child.getTreeNext();
        }
        return columnInfoMap;
    }

    private CsvFormatHelper() {
        // static utility class
    }
}