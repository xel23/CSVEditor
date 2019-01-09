package csv.editor;

import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.ui.CheckBoxWithColorChooser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CsvEditorSettingsProvider implements SearchableConfigurable {

    public static final String CSV_EDITOR_SETTINGS_ID = "Csv.Editor.Settings";

    private JPanel myMainPanel;
    private CheckBoxWithColorChooser cbTabHighlightColor;
    private JComboBox cbRowHeight;

    // reload method
    @NotNull
    @Override
    public String getId() {
        return CSV_EDITOR_SETTINGS_ID;
    }

    // reload method
    @Override
    public String getDisplayName() {
        return "CSV";
    }

    @Override
    public String getHelpTopic() {
        return "CSV Editor Options";
    }

    // reload method
    @Nullable
    @Override
    public JComponent createComponent() {
        return myMainPanel;
    }

    public boolean isModified(@NotNull JToggleButton toggleButton, boolean value) {
        return toggleButton.isSelected() != value;
    }

    // reload method
    @Override
    public boolean isModified() {
        CsvEditorSettingsExternalizable csvEditorSettingsExternalizable = CsvEditorSettingsExternalizable.getInstance();
        return  !Objects.equals(cbTabHighlightColor.getColor(), csvEditorSettingsExternalizable.getTabHighlightColor());
    }

    @Override
    public void reset() {
        CsvEditorSettingsExternalizable csvEditorSettingsExternalizable = CsvEditorSettingsExternalizable.getInstance();
        cbTabHighlightColor.setColor(csvEditorSettingsExternalizable.getTabHighlightColor());
    }

    // reload method
    @Override
    public void apply() {
        CsvEditorSettingsExternalizable csvEditorSettingsExternalizable = CsvEditorSettingsExternalizable.getInstance();
        csvEditorSettingsExternalizable.setTabHighlightColor(cbTabHighlightColor.getColor());
        csvEditorSettingsExternalizable.setTableEditorRowHeight(cbRowHeight.getSelectedIndex());
    }

    protected void createUIComponents() {
        cbTabHighlightColor = new CheckBoxWithColorChooser("Highlight tab separator   ");
        cbTabHighlightColor.setColor(Color.CYAN);
    }
}
