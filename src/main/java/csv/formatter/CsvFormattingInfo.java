package csv.formatter;

import com.intellij.formatting.SpacingBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import csv.CsvColumnInfo;
import csv.CsvColumnInfoMap;
import csv.settings.CsvCodeStyleSettings;

import java.util.Map;

public class CsvFormattingInfo extends CsvColumnInfoMap<ASTNode> {

    private SpacingBuilder mySpacingBuilder;
    private CodeStyleSettings myCodeStyleSettings;

    public SpacingBuilder getSpacingBuilder() {
        return mySpacingBuilder;
    }

    public CsvCodeStyleSettings getCsvCodeStyleSettings() {
        return myCodeStyleSettings.getCustomSettings(CsvCodeStyleSettings.class);
    }

    public CodeStyleSettings getCodeStyleSettings() {
        return myCodeStyleSettings;
    }

    public CsvFormattingInfo(CodeStyleSettings codeStyleSettings, SpacingBuilder spacingBuilder, Map<Integer,
            CsvColumnInfo<ASTNode>> infoColumnMap) {
        super(infoColumnMap);
        this.mySpacingBuilder = spacingBuilder;
        this.myCodeStyleSettings = codeStyleSettings;
    }
}