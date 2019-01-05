package csv.settings;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.openapi.options.Configurable;
import com.intellij.psi.codeStyle.*;
import csv.CsvLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CsvCodeStyleSettingsProvider extends CodeStyleSettingsProvider {
    @Override
    public CustomCodeStyleSettings createCustomSettings(CodeStyleSettings settings) {
        return new CsvCodeStyleSettings(settings);
    }

    @Nullable
    @Override
    public String getConfigurableDisplayName() {
        return CsvLanguage.INSTANCE.getDisplayName();
    }

    @NotNull
    @Override
    public Configurable createSettingsPage(CodeStyleSettings settings, CodeStyleSettings originalSettings) {
        return new CodeStyleAbstractConfigurable(settings, originalSettings, CsvLanguage.INSTANCE.getDisplayName()) {
            @Override
            protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
                return new CsvCodeStyleMainPanel(getCurrentSettings(), settings);
            }

            @Nullable
            @Override
            public String getHelpTopic() {
                return null;
            }
        };
    }

    private static class CsvCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {
        CsvCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
            super(CsvLanguage.INSTANCE, currentSettings, settings);
        }

        @Override
        protected void initTabs(CodeStyleSettings settings) {
            addTab(new CsvCodeStyleOptionTreeWithPreviewPanel(settings));
        }

        protected class CsvCodeStyleOptionTreeWithPreviewPanel extends MyWrappingAndBracesPanel {
            public CsvCodeStyleOptionTreeWithPreviewPanel(CodeStyleSettings settings) {
                super(settings);
            }

            @Override
            protected String getTabTitle() {
                return "Settings";
            }

            @Override
            public LanguageCodeStyleSettingsProvider.SettingsType getSettingsType() {
                return LanguageCodeStyleSettingsProvider.SettingsType.LANGUAGE_SPECIFIC;
            }
        }
    }
}