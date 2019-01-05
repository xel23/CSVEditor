package csv.settings;

import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import csv.CsvLanguage;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

public class CsvLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {
    @NotNull
    @Override
    public Language getLanguage() {
        return CsvLanguage.INSTANCE;
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        if (settingsType == SettingsType.LANGUAGE_SPECIFIC) {
            consumer.showCustomOption(CsvCodeStyleSettings.class,
                    "SEPARATOR_INDEX",
                    "Value separator",
                    "Separator",
                    CsvCodeStyleSettings.SUPPORTED_SEPARATORS_DISPLAY,
                    IntStream.rangeClosed(0, CsvCodeStyleSettings.SUPPORTED_SEPARATORS.length - 1).toArray());

            consumer.showCustomOption(CsvCodeStyleSettings.class,
                    "SPACE_BEFORE_SEPARATOR",
                    "Space before separator",
                    "Separator");
            consumer.showCustomOption(CsvCodeStyleSettings.class,
                    "SPACE_AFTER_SEPARATOR",
                    "Space after separator",
                    "Separator");

            consumer.showCustomOption(CsvCodeStyleSettings.class,
                    "TABULARIZE",
                    "Enabled",
                    "Tabularize (ignores Trimming settings)");
            consumer.showCustomOption(CsvCodeStyleSettings.class,
                    "WHITE_SPACES_OUTSIDE_QUOTES",
                    "Trimming/spacing outside quotes",
                    "Tabularize (ignores Trimming settings)");
            consumer.showCustomOption(CsvCodeStyleSettings.class,
                    "LEADING_WHITE_SPACES",
                    "Leading whitespaces",
                    "Tabularize (ignores Trimming settings)");
        }
    }

    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return "1,\"Hello, World!\",field1,3,5,44.44,77,aaaa,Some text,0.88\n" +
                "2,\"version 0.1 \"\"Cool\"\" abcd\",Russia,777,99.99,347.0,33.02,HHH,Dup,11.58\n" +
                "3,\"Some text, some text\",USSR,1996,13.25,0.00,78.0,dup,Some Name,0.0\n";
    }

    @Override
    public CommonCodeStyleSettings getDefaultCommonSettings() {
        CommonCodeStyleSettings commonSettings = new CommonCodeStyleSettings(getLanguage());
        commonSettings.initIndentOptions();
        commonSettings.getIndentOptions().TAB_SIZE = 1;
        commonSettings.getIndentOptions().INDENT_SIZE = 1;
        commonSettings.getIndentOptions().USE_TAB_CHARACTER = true;
        commonSettings.getIndentOptions().SMART_TABS = false;
        commonSettings.getIndentOptions().KEEP_INDENTS_ON_EMPTY_LINES = true;
        commonSettings.WRAP_ON_TYPING = CommonCodeStyleSettings.WrapOnTyping.NO_WRAP.intValue;
        commonSettings.WRAP_LONG_LINES = false;
        commonSettings.RIGHT_MARGIN = Integer.MAX_VALUE;
        return commonSettings;
    }
}