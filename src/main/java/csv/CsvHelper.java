package csv;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import csv.psi.CsvField;
import csv.psi.CsvFile;
import csv.psi.CsvRecord;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class CsvHelper {

    @NotNull
    @Contract("_ -> new")
    public static CsvColumnInfoMap<PsiElement> createColumnInfoMap(CsvFile csvFile) {
        Map<Integer, CsvColumnInfo<PsiElement>> columnInfoMap = new HashMap<>();
        CsvRecord[] records = PsiTreeUtil.getChildrenOfType(csvFile, CsvRecord.class);
        int row = 0;
        for (CsvRecord record : records) {
            int column = 0;
            for (CsvField field : record.getFieldList()) {
                Integer length = field.getTextLength();
                if (!columnInfoMap.containsKey(column)) {
                    columnInfoMap.put(column, new CsvColumnInfo(length));
                } else if (columnInfoMap.get(column).getMaxLength() < length) {
                    columnInfoMap.get(column).setMaxLength(length);
                }
                columnInfoMap.get(column).addElement(field, row);
                ++column;
            }
            ++row;
        }
        return new CsvColumnInfoMap(columnInfoMap, PsiTreeUtil.hasErrorElements(csvFile));
    }

    @Contract("null -> !null")
    public static String unquoteCsvValue(String content) {
        if (content == null) {
            return "";
        }
        String result = content.trim();
        if (result.length() > 1 && result.startsWith("\"") && result.endsWith("\"")) {
            result = result.substring(1, result.length() - 1);
        }
        result = result.replaceAll("(?:\")\"", "\"");
        return result;
    }

    @NotNull
    public static <T> T[][] deepCopy(T[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }

    private CsvHelper() {
        // static utility class
    }
}