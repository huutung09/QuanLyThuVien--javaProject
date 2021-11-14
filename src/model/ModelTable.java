package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModelTable<T> extends AbstractTableModel {
    private String[] columnNames;
    private List<T> data;
    private Listener<T> listener;

    public ModelTable(List<T> data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return listener.getTableValue(rowIndex, columnIndex, data);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return false;
    }

    public static interface Listener<T> {
        public Object getTableValue(int rowIndex, int columnIndex, List<T> data);
    }

    public void setListener(Listener<T> listener) {
        this.listener = listener;
    }
}
