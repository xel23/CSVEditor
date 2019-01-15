package csv.editor;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.editor.ex.EditorSettingsExternalizable;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.beans.PropertyChangeSupport;

// https://www.jetbrains.org/intellij/sdk/docs/basics/persisting_state_of_components.html
@State(
        name = "CsvEditorSettings",
        storages = {@Storage("csv-plugin.xml")}
)
@SuppressWarnings("all")
public class CsvEditorSettingsExternalizable implements
        PersistentStateComponent<CsvEditorSettingsExternalizable.OptionSet> {

    public static final int TABLE_EDITOR_MIN_ROW_HEIGHT = 0;
    public static final int TABLE_EDITOR_MAX_ROW_HEIGHT = 5;
    public static final int TABLE_EDITOR_DEFAULT_ROW_HEIGHT = 3;

    public static final class OptionSet {
        public String TAB_HIGHLIGHT_COLOR;
        public int TABLE_EDITOR_ROW_HEIGHT;
        public boolean QUOTING_ENFORCED;

        public OptionSet() {
            EditorSettingsExternalizable editorSettingsExternalizable = EditorSettingsExternalizable.getInstance();
            TAB_HIGHLIGHT_COLOR = "-7777";
            QUOTING_ENFORCED = false;
            TABLE_EDITOR_ROW_HEIGHT = TABLE_EDITOR_DEFAULT_ROW_HEIGHT;
        }
    }

    private OptionSet myOptions = new OptionSet();
    private final PropertyChangeSupport myPropertyChangeSupport = new PropertyChangeSupport(this);

    public CsvEditorSettingsExternalizable() {
    }

    public static CsvEditorSettingsExternalizable getInstance() {
        return ApplicationManager.getApplication().isDisposed() ? new CsvEditorSettingsExternalizable() :
                ServiceManager.getService(CsvEditorSettingsExternalizable.class);
    }

    // reload method
    @Override
    public OptionSet getState() {
        return this.myOptions;
    }

    // reload method
    @Override
    public void loadState(@NotNull OptionSet state) {
        this.myOptions = state;
    }

    /**----- Settings section -----**/

    public Color getTabHighlightColor() {
        String color = getState().TAB_HIGHLIGHT_COLOR;
        try {
            return color == null || color.isEmpty() ? null : Color.decode(getState().TAB_HIGHLIGHT_COLOR);
        } catch (NumberFormatException exc) {
            return null;
        }
    }
    public void setTabHighlightColor(Color color) {
        getState().TAB_HIGHLIGHT_COLOR = color == null ? "" : "" + color.getRGB();
    }

    public int getTableEditorRowHeight() {
        return getState().TABLE_EDITOR_ROW_HEIGHT;
    }

    public void setTableEditorRowHeight(int rowHeight) {
        int finalRowHeight = rowHeight;
        if (finalRowHeight > TABLE_EDITOR_MAX_ROW_HEIGHT) finalRowHeight = TABLE_EDITOR_MAX_ROW_HEIGHT;
        if (finalRowHeight < TABLE_EDITOR_MIN_ROW_HEIGHT) finalRowHeight = TABLE_EDITOR_MIN_ROW_HEIGHT;
        getState().TABLE_EDITOR_ROW_HEIGHT = finalRowHeight;
    }

    public boolean isQuotingEnforced() {
        return getState().QUOTING_ENFORCED;
    }
}