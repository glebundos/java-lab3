import javax.swing.*;
import java.awt.*;

public class GornerTableFrame extends JFrame
{
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private double[] coefficients;

    private GornerTableCellRenderer renderer = new GornerTableCellRenderer();

    private GornerTableModel dataTable;

    GornerTableFrame(double[] coefficients) {
        super("ТАБУЛИРОВАНИЕ ПО СХЕМЕ ГОРНЕРА");
        this.coefficients = coefficients;
        setSize(WIDTH, HEIGHT);

        Toolkit tk = Toolkit.getDefaultToolkit();
        setLocation((tk.getScreenSize().width - WIDTH) / 2, (tk.getScreenSize().height - HEIGHT) / 2);

    }
}
