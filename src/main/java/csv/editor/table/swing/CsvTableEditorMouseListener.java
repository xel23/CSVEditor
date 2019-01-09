package csv.editor.table.swing;

import com.intellij.openapi.ui.JBPopupMenu;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CsvTableEditorMouseListener extends CsvTableEditorUtilBase implements MouseListener {

    private JBPopupMenu rowPopupMenu;
    private JBPopupMenu columnPopupMenu;

    public CsvTableEditorMouseListener(CsvTableEditorSwing csvTableEditorArg) {
        super(csvTableEditorArg);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // mouseClicked
    }

    protected JBPopupMenu getRowPopupMenu() {
        if (rowPopupMenu == null) {
            rowPopupMenu = new JBPopupMenu();
            JMenuItem menuItem = new JMenuItem("New row before", csvTableEditor.btnAddRowBefore.getIcon());
            menuItem.addActionListener(csvTableEditor.tableEditorActions.addRowBefore);
            rowPopupMenu.add(menuItem);
            menuItem = new JMenuItem("New row after", csvTableEditor.btnAddRow.getIcon());
            menuItem.addActionListener(csvTableEditor.tableEditorActions.addRowAfter);
            rowPopupMenu.add(menuItem);
            menuItem = new JMenuItem("Delete selected row(s)", csvTableEditor.btnRemoveRow.getIcon());
            menuItem.addActionListener(csvTableEditor.tableEditorActions.deleteRow);
            rowPopupMenu.add(menuItem);
        }
        return rowPopupMenu;
    }

    protected JBPopupMenu getColumnPopupMenu() {
        if (columnPopupMenu == null) {
            columnPopupMenu = new JBPopupMenu();
            JMenuItem menuItem = new JMenuItem("New column before", csvTableEditor.btnAddColumnBefore.getIcon());
            menuItem.addActionListener(csvTableEditor.tableEditorActions.addColumnBefore);
            columnPopupMenu.add(menuItem);
            menuItem = new JMenuItem("New column after", csvTableEditor.btnAddColumn.getIcon());
            menuItem.addActionListener(csvTableEditor.tableEditorActions.addColumnAfter);
            columnPopupMenu.add(menuItem);
            menuItem = new JMenuItem("Delete selected column ", csvTableEditor.btnRemoveColumn.getIcon());
            menuItem.addActionListener(csvTableEditor.tableEditorActions.deleteColumn);
            columnPopupMenu.add(menuItem);
        }
        return columnPopupMenu;
    }

    protected void showPopupMenu(JBPopupMenu popupMenu, Component component, int x, int y) {
        if (!popupMenu.isShowing()) {
            popupMenu.show(component, x, y);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int currentColumn = csvTableEditor.getTable().columnAtPoint(e.getPoint());
        JBPopupMenu menu;
        if (e.getSource() instanceof JTableHeader) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                csvTableEditor.selectColumn(currentColumn, (e.isControlDown() || e.isShiftDown()));
            }
            menu = this.getColumnPopupMenu();
        } else {
            menu = this.getRowPopupMenu();
        }
        if (e.isPopupTrigger()) {
            showPopupMenu(menu, e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JBPopupMenu menu;
        if (e.getSource() instanceof JTableHeader) {
            menu = this.getColumnPopupMenu();
        } else {
            menu = this.getRowPopupMenu();
        }
        if (e.isPopupTrigger()) {
            showPopupMenu(menu, e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // mouseEntered
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // mouseExited
    }
}