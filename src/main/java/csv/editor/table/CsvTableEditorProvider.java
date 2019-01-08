package csv.editor.table;

import com.intellij.openapi.fileEditor.AsyncFileEditorProvider;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.SingleRootFileViewProvider;
import csv.editor.CsvFileEditorProvider;
import csv.editor.table.swing.CsvTableEditorSwing;
import org.jetbrains.annotations.NotNull;

public class CsvTableEditorProvider implements AsyncFileEditorProvider, DumbAware {

    public static final String EDITOR_TYPE_ID = "csv-table-editor";

    // reload method
    @Override
    public String getEditorTypeId() {
        return EDITOR_TYPE_ID;
    }

    // reload method
    @Override
    public FileEditorPolicy getPolicy() {
        return FileEditorPolicy.NONE;
    }

    // reload method
    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        return CsvFileEditorProvider.isCsvFile(file) && !SingleRootFileViewProvider.isTooLargeForIntelligence(file);
    }

    // reload method
    @NotNull
    @Override
    public FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        return createEditorAsync(project, virtualFile).build();
    }

    // reload method
    @NotNull
    @Override
    public Builder createEditorAsync(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        return new Builder() {
            @Override
            public FileEditor build() {
                return new CsvTableEditorSwing(project, virtualFile);
            }
        };
    }
}