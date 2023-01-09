import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RendererPersonalizado extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        if (table.getValueAt(row, col).equals("")) {
            label.setBackground(Color.CYAN);
        } else if (table.getValueAt(row, col).equals("X")){
            label.setBackground(Color.RED);
            label.setHorizontalAlignment(CENTER);
        } else {
            label.setBackground(Color.white);
            label.setHorizontalAlignment(CENTER);
        }
        return label;
    }
}