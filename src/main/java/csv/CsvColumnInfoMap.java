package csv;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CsvColumnInfoMap<T> {

    private final Map<Integer, CsvColumnInfo<T>> myInfoColumnMap;
    private final Map<T, CsvColumnInfo<T>> myReverseInfoColumnMap;

    private boolean hasErrors = false;

    public CsvColumnInfoMap(Map<Integer, CsvColumnInfo<T>> infoColumnMap, boolean hasErrorsArg) {
        this.myInfoColumnMap = infoColumnMap;
        this.myReverseInfoColumnMap = new HashMap<>();
        buildReverseMap();
        setHasErrors(hasErrorsArg);
    }

    public CsvColumnInfoMap(Map<Integer, CsvColumnInfo<T>> infoColumnMap) {
        this(infoColumnMap, false);
    }

    private void buildReverseMap() {
        for (CsvColumnInfo<T> columnInfo : this.myInfoColumnMap.values()) {
            for (T element : columnInfo.getElements()) {
                this.myReverseInfoColumnMap.put(element, columnInfo);
            }
        }
    }

    public CsvColumnInfo<T> getColumnInfo(T element) {
        return myReverseInfoColumnMap.get(element);
    }

    public CsvColumnInfo<T> getColumnInfo(int columnIndex) {
        return myInfoColumnMap.get(columnIndex);
    }

    public Map<Integer, CsvColumnInfo<T>> getColumnInfos() {
        return Collections.unmodifiableMap(this.myInfoColumnMap);
    }

    public boolean hasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrorsArg) {
        hasErrors = hasErrorsArg;
    }
}