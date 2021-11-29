import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel
{
    private double[] coefficients;
    private double xBeg;
    private double xEnd;
    private double step;

    public GornerTableModel(double xBeg, double xEnd, double step, double[] coefficients) {
        this.xBeg = xBeg;
        this.xEnd = xEnd;
        this.step = step;
        this.coefficients = coefficients;
    }
    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
