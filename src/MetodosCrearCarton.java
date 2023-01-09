import javax.swing.*;

public class MetodosCrearCarton {
    public static void rellenarTabla(int[][] arrayBingo, String[][] arrayBingoString, JTable tablaCartonBingo) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                arrayBingoString[i][j] = String.valueOf(arrayBingo[i][j]);
                if (arrayBingoString[i][j].equals("0")) {
                    arrayBingoString[i][j] = "";
                }
                tablaCartonBingo.setValueAt(arrayBingoString[i][j], i, j);
            }
        }
    }

    public static void limpiarTabla(int[][] arrayBingo, JTable table) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                arrayBingo[i][j] = 0;
            }
        }
        table.setValueAt(" ",0,0);
    }

    public static void centrarYColorearCeldas(JTable table) {
        for (int i = 0; i < 9; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new RendererPersonalizado());
        }
    }
}
