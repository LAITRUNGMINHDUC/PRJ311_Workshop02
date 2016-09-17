/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author duclt
 */
public class EmployeeModel<E> extends AbstractTableModel {

    private String[] header;
    private int[] indexes;
    private Vector<Employee> data;

    public EmployeeModel(String[] header, int[] indexes) {
        this.header = new String[header.length];
        this.indexes = new int[indexes.length];

        System.arraycopy(header, 0, this.header, 0, this.header.length);
        System.arraycopy(indexes, 0, this.indexes, 0, this.indexes.length);

        this.data = new Vector<>();
    }

    public Vector<Employee> getData() {
        return data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        if (column >= 0 && column < header.length) {
            return header[column];
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= data.size()
                || columnIndex < 0 || columnIndex >= header.length) {
            return null;
        }

        Employee emp = data.get(rowIndex);
        switch (indexes[columnIndex]) {
            case 0:
                return emp.getCode();
            case 1:
                return emp.getName();
            case 2:
                return emp.getAddress();
            case 3:
                return emp.isSex();
            case 4:
                return emp.getSalary();
        }

        return null;
    }

}
