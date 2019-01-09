package csv.editor.table;

import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.fileEditor.FileEditorStateLevel;
import csv.editor.CsvEditorSettingsExternalizable;

public class CsvTableEditorState implements FileEditorState {

    private int[] columnWidths;
    private Integer rowLines;

    public CsvTableEditorState() {
        columnWidths = new int[0];
    }

    public int[] getColumnWidths() {
        return this.columnWidths;
    }

    public void setColumnWidths(int[] widths) {
        this.columnWidths = widths;
    }

    public int getRowLines() {
        if (rowLines == null) {
            rowLines = CsvEditorSettingsExternalizable.getInstance().getTableEditorRowHeight();
        }
        return rowLines;
    }

    public void setRowLines(int rowLinesArg) {
        rowLines = rowLinesArg;
    }

    @Override
    public boolean canBeMergedWith(FileEditorState fileEditorState, FileEditorStateLevel fileEditorStateLevel) {
        return false;
    }
}
