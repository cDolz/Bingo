import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class FormularioCarton extends JFrame {
    private JPanel panelCartonBingo;
    private JTable tablaCartonBingo;
    private JButton botonGenerarCarton;
    private JButton botonJugar;
    private JLabel tituloCartonBingo;
    private JTable tablaBingoJugar;
    private int[][] arrayBingo = new int[3][9];
    private String[][] arrayBingoString = new String[3][9];
    private int columna;
    private boolean flag;
    private int numeroRandom;
    private int[] numerosHastaNoventa = new int[90];
    private int contadorNumerosHastaNoventa;
    boolean linea;
    boolean bingo;
    private static FormularioCarton formularioCarton;
    public FormularioCarton() {
        setContentPane(panelCartonBingo);
        DefaultTableModel modeloTablaCarton = new DefaultTableModel(3, 9);
        tablaCartonBingo.setModel(modeloTablaCarton);
        DefaultTableModel modeloTablaJugar = new DefaultTableModel(1, 1);
        tablaBingoJugar.setModel(modeloTablaJugar);
        botonGenerarCarton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MetodosCrearCarton.limpiarTabla(arrayBingo, tablaBingoJugar);
                Arrays.fill(numerosHastaNoventa, 0);
                bingo = false;
                linea = false;
                contadorNumerosHastaNoventa = 0;
                do {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 5; j++) {
                            do {
                                generarNumeroRandomPorColumna();
                                controlarNumerosAcabadosEnNueve();
                                setearValor(i);
                            } while (!flag);
                        }
                    }
                    tresCerosEnColumna();
                } while (!flag);
                MetodosCrearCarton.rellenarTabla(arrayBingo, arrayBingoString, tablaCartonBingo);
                MetodosCrearCarton.centrarYColorearCeldas(tablaCartonBingo);
            }
        });
        botonJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do {
                    numeroRandom = (int) (Math.random() * (91 - 1) + 1);
                    flag = true;
                    for (int i = 0; i < numerosHastaNoventa.length; i++) {
                        if (numeroRandom == numerosHastaNoventa[i]) {
                            flag = false;
                        }
                    }
                } while (!flag);
                numerosHastaNoventa[contadorNumerosHastaNoventa] = numeroRandom;
                contadorNumerosHastaNoventa++;
                tablaBingoJugar.setValueAt(numeroRandom, 0, 0);
                tablaBingoJugar.getColumnModel().getColumn(0).setCellRenderer(new RendererPersonalizado());
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (tablaBingoJugar.getValueAt(0, 0).toString().equals(tablaCartonBingo.getValueAt(i, j))) {
                            tablaCartonBingo.setValueAt("X", i, j);
                            MetodosCrearCarton.centrarYColorearCeldas(tablaCartonBingo);
                        }
                    }
                }
                cantarLinea();
                cantarBingo();
                if (bingo) {
                    formularioCarton.setVisible(false);
                    formularioCarton.dispose();
                }
            }

    });
}

    private void cantarLinea() {
        if (!linea) {
            for (int i = 0; i < 3; i++) {
                int contador = 0;
                for (int j = 0; j < 9; j++) {
                    if (tablaCartonBingo.getValueAt(i, j).equals("") || tablaCartonBingo.getValueAt(i, j).equals("X")) {
                        contador++;
                    }
                    if (contador == 9) {
                        JOptionPane.showMessageDialog(null, "LINEA :)");
                        linea = true;
                    }

                }
            }
        }
    }

    private void cantarBingo() {
        if (!bingo) {
            int contador = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    if (tablaCartonBingo.getValueAt(i, j).equals("") || tablaCartonBingo.getValueAt(i, j).equals("X")) {
                        contador++;
                    }
                    if (contador == 27) {
                        JOptionPane.showMessageDialog(null, "BINGO :D");
                        bingo = true;
                    }

                }
            }
        }
    }

    public static void crearCarton() {
        formularioCarton = new FormularioCarton();
        formularioCarton.setExtendedState(formularioCarton.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        formularioCarton.setVisible(true);
        formularioCarton.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void generarNumeroRandomPorColumna() {
        columna = (int) (Math.random() * 9);
        int rangoMinimo = columna * 10;
        int rangoMaximo = (columna * 10) + 10;
        if (columna == 8) {
            rangoMaximo = 91;
        }
        numeroRandom = (int) (Math.random() * (rangoMaximo - rangoMinimo) + rangoMinimo);
    }

    private void controlarNumerosAcabadosEnNueve() {
        if (numeroRandom % 10 == 9) {
            if ((arrayBingo[1][columna] == 0 || arrayBingo[2][columna] == 0) && arrayBingo[0][columna] % 10 == 9) {
                arrayBingo[0][columna]--;
            } else if (arrayBingo[2][columna] == 0 && arrayBingo[1][columna] % 10 == 9 && arrayBingo[0][columna] == 0) {
                arrayBingo[1][columna]--;
            }
        }
    }

    private void setearValor(int i) {
        if (arrayBingo[i][columna] != 0 || numeroRandom == 0) {
            flag = false;
        } else if (i == 1 && numeroRandom <= arrayBingo[0][columna]) {
            flag = false;
        } else if (i == 2 && (numeroRandom <= arrayBingo[0][columna] || numeroRandom <= arrayBingo[1][columna])) {
            flag = false;
        } else if (arrayBingo[2][columna] == 0 && (arrayBingo[0][columna] != 0 && arrayBingo[1][columna] != 0)) {
            flag = false;
        } else {
            arrayBingo[i][columna] = numeroRandom;
            flag = true;
        }
    }

    private void tresCerosEnColumna() {
        for (int i = 0; i < 9; i++) {
            if (arrayBingo[0][i] == 0 && arrayBingo[1][i] == 0 && arrayBingo[2][i] == 0) {
                flag = false;
                MetodosCrearCarton.limpiarTabla(arrayBingo, tablaBingoJugar);
                System.out.println("tres ceros.");
                break;
            }
        }
    }
}
