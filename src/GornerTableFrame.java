import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GornerTableFrame extends JFrame
{
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private double[] coefficients;

    private JTextField xBegTF;
    private JTextField xEndTF;
    private JTextField stepTF;

    private Box hResultTableBox;

    private GornerTableCellRenderer renderer = new GornerTableCellRenderer();

    private GornerTableModel dataTable;

    GornerTableFrame(double[] coefficients) {
        super("ТАБУЛИРОВАНИЕ ПО СХЕМЕ ГОРНЕРА");
        this.coefficients = coefficients;
        setSize(WIDTH, HEIGHT);

        Toolkit tk = Toolkit.getDefaultToolkit();
        setLocation((tk.getScreenSize().width - WIDTH) / 2, (tk.getScreenSize().height - HEIGHT) / 2);

        constructBot();
        constructMid();
        constructTop();
    }

    private void constructTop() {
        JLabel xBegLabel = new JLabel("X от: ");
        xBegTF = new JTextField("0.0", 15);
        xBegTF.setMaximumSize(xBegTF.getPreferredSize());

        JLabel xEndLabel = new JLabel("до: ");
        xEndTF = new JTextField("1.0", 15);
        xEndTF.setMaximumSize(xEndTF.getPreferredSize());

        JLabel stepLabel = new JLabel("с шагом: ");
        stepTF = new JTextField("0.1", 15);
        stepTF.setMaximumSize(stepTF.getPreferredSize());

        Box hBoxTextFields = Box.createHorizontalBox();
        //hBoxTextFields.setBorder(BorderFactory.createBevelBorder(1));
        hBoxTextFields.add(Box.createHorizontalGlue());
        hBoxTextFields.add(xBegLabel);
        hBoxTextFields.add(Box.createHorizontalStrut(10));
        hBoxTextFields.add(xBegTF);
        hBoxTextFields.add(Box.createHorizontalStrut(30));
        hBoxTextFields.add(xEndLabel);
        hBoxTextFields.add(Box.createHorizontalStrut(10));
        hBoxTextFields.add(xEndTF);
        hBoxTextFields.add(Box.createHorizontalStrut(30));
        hBoxTextFields.add(stepLabel);
        hBoxTextFields.add(Box.createHorizontalStrut(10));
        hBoxTextFields.add(stepTF);
        hBoxTextFields.add(Box.createHorizontalGlue());

        hBoxTextFields.setPreferredSize(new Dimension(
                (int) hBoxTextFields.getMaximumSize().getWidth(),
                (int) hBoxTextFields.getMinimumSize().getHeight() * 2));

        add(hBoxTextFields, BorderLayout.SOUTH);
    }

    private void constructMid() {
        hResultTableBox = Box.createHorizontalBox();
        hResultTableBox.add(new JPanel());
        add(hResultTableBox, BorderLayout.CENTER);
    }

    private void constructBot() {
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double xBeg = Double.parseDouble(xBegTF.getText());
                    double xEnd = Double.parseDouble(xEndTF.getText());
                    double step = Double.parseDouble(stepTF.getText());

                    if (xBeg > xEnd) {
                        double tmp = xBeg;
                        xBeg = xEnd;
                        xEnd = tmp;
                        xBegTF.setText(String.valueOf(xBeg));
                        xEndTF.setText(String.valueOf(xEnd));
                    }

                    if (step <= 0) {
                        throw new NumberFormatException();
                    }

                    dataTable = new GornerTableModel(xBeg, xEnd, step, coefficients);
                    JTable table = new JTable(dataTable);
                    table.setDefaultRenderer(Double.class, renderer);
                    table.setRowHeight(30);
                    hResultTableBox.removeAll();
                    hResultTableBox.add(new JScrollPane(table));
                    validate();

                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(GornerTableFrame.this, "Неверные данные", "Ошибка", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

}
