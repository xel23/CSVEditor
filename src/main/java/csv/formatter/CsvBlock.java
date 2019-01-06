package csv.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import csv.psi.CsvTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CsvBlock extends AbstractBlock {
    protected CsvFormattingInfo myFormattingInfo;

    protected CsvBlock(@NotNull ASTNode node, CsvFormattingInfo formattingInfo) {
        super(node, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment());
        this.myFormattingInfo = formattingInfo;
    }

// reload method buildChildren from AbstractBlock
    @Override
    protected List<Block> buildChildren() {
        List<ASTNode> tdNodes = new ArrayList<>();
        List<Block> blocks = new ArrayList<>();
        tdNodes.add(getNode().getFirstChildNode());
        CsvBlockField currentField = null;
        while (tdNodes.size() > 0) {
            ASTNode node = tdNodes.remove(tdNodes.size() - 1);
            if (node == null) {
                continue;
            }

            IElementType elementType = node.getElementType();
            tdNodes.add(node.getTreeNext());
            if (elementType == CsvTypes.RECORD) {
                tdNodes.add(node.getFirstChildNode());
            } else if (elementType == CsvTypes.FIELD) {
                currentField = new CsvBlockField(node, myFormattingInfo);
                if (currentField.getTextLength() > 0) {
                    blocks.add(currentField);
                }
            } else if (elementType == CsvTypes.COMMA || elementType == CsvTypes.CRLF) {
                blocks.add(new CsvBlockElement(node, myFormattingInfo, currentField));
            } else if (elementType != TokenType.WHITE_SPACE && node.getTextLength() > 0) {
                blocks.add(new CsvDummyBlock(node, myFormattingInfo));
            }
        }
        return blocks;
    }

// reload method getSpacing from AbstractBlock
    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        Spacing spacing = null;
        return spacing;
    }


// reload method getSpacing from AbstractBlock
    @Override
    public boolean isLeaf() {
        return getNode().getFirstChildNode() == null;
    }
}