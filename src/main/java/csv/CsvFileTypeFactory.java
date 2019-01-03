package csv;

import com.intellij.openapi.fileTypes.*;
import org.jetbrains.annotations.NotNull;

public class CsvFileTypeFactory extends FileTypeFactory {
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(CsvFileType.INSTANCE, String.join(FileTypeConsumer.EXTENSION_DELIMITER,
                new String[]{"csv", "CSV", "Csv"}));
    }
}