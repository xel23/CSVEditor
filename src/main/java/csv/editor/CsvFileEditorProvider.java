package csv.editor;

import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.fileEditor.impl.text.TextEditorProvider;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.SingleRootFileViewProvider;
import csv.CsvLanguage;
import org.jetbrains.annotations.NotNull;
import org.jdom.Element;

public class CsvFileEditorProvider implements AsyncFileEditorProvider {

    public static final String EDITOR_TYPE_ID = "csv-text-editor";

    public static boolean isCsvFile(@NotNull VirtualFile file) {
        return file.getFileType() instanceof LanguageFileType &&
                ((LanguageFileType) file.getFileType()).getLanguage().isKindOf(CsvLanguage.INSTANCE);
    }

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
        return isCsvFile(file) && !SingleRootFileViewProvider.isTooLargeForContentLoading(file);
    }

    // reload method
    @NotNull
    @Override
    public FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        return createEditorAsync(project, virtualFile).build();
    }

    @Override
    public FileEditorState readState(@NotNull Element sourceElement, @NotNull Project project,
                                     @NotNull VirtualFile file) {
        return TextEditorProvider.getInstance().readState(sourceElement, project, file);
    }

    @Override
    public void writeState(@NotNull FileEditorState state, @NotNull Project project, @NotNull Element targetElement) {
        TextEditorProvider.getInstance().writeState(state, project, targetElement);
    }

    @Override
    public void disposeEditor(@NotNull FileEditor editor) {
        TextEditorProvider.getInstance().disposeEditor(editor);
    }

    // reload method
    @NotNull
    @Override
    public Builder createEditorAsync(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        return new Builder() {
            @Override
            public FileEditor build() {
                TextEditor textEditor = (TextEditor)TextEditorProvider.getInstance().createEditor(project, virtualFile);
                return textEditor;
            }
        };
    }
}