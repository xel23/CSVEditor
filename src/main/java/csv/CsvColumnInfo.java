package csv;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CsvColumnInfo<T> {

    private int myMaxLength;
    private Map<T, RowInfo> myElementInfos;
    private int mySize;

    public CsvColumnInfo(int maxLength) {
        this.myMaxLength = maxLength;
        this.myElementInfos = new HashMap<>();
        this.mySize = 0;
    }

    public int getMaxLength() {
        return myMaxLength;
    }

    public void setMaxLength(int maxLength) {
        this.myMaxLength = maxLength;
    }

    public int getSize() {
        return this.mySize;
    }

    public List<T> getElements() {
        List<T> result = new ArrayList<>(getSize());
        result.addAll(Collections.nCopies(getSize(), null));
        myElementInfos.values()
                .forEach(rowInfo -> result.set(rowInfo.myRow, rowInfo.myElement));
        return result;
    }

    protected void put(@NotNull T element, @NotNull RowInfo rowInfo) {
        myElementInfos.put(element, rowInfo);
        if (this.getSize() <= rowInfo.myRow) {
            this.mySize = rowInfo.myRow + 1;
        }
    }

    public void addElement(T element) {
        this.put(element, new RowInfo(element, myElementInfos.size()));
    }

    public void addElement(T element, int row) {
        this.put(element, new RowInfo(element, row));
    }

    public class RowInfo {
        private final T myElement;
        private final int myRow;

        RowInfo(@NotNull T element, @NotNull int row) {
            this.myElement = element;
            this.myRow = row;
        }

        @Override
        public int hashCode() {
            return myElement.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof CsvColumnInfo.RowInfo)) {
                return false;
            }
            return this.myElement.equals(((RowInfo) other).myElement);
        }
    }
}