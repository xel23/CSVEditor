package csv.editor.table.api;

import java.util.EventListener;
import java.util.EventObject;

public class TableDataChangeEvent extends EventObject {

    private Object[][] value;

    public interface Listener extends EventListener {
        void onTableDataChanged(TableDataChangeEvent event);
    }

    public TableDataChangeEvent(Object source, Object[][] values) {
        super(source);
        this.value = values;
    }
}