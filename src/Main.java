import javax.swing.*;

public class Main {
    public static int bingo[][] = new int[3][9];
    public static int k = 0;

    public static void main(String[] args) {
        boolean flag;
        int rangoUno = 0;
        int rangoDos = 10;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    try {
                        bingo[j][k] = Integer.parseInt(JOptionPane.showInputDialog("Dime un numero entre el " +
                                rangoUno + " y el " + rangoDos));
                        if (bingo[j][k] != 0 && (bingo[j][k] >= rangoUno && bingo[j][k] < rangoDos)) {
                            flag = true;
                            if (bingo[1][k] != 0 && (bingo[1][k] == bingo[0][k] ||
                                    bingo[1][k] == bingo[2][k])) {
                                flag = false;
                                JOptionPane.showMessageDialog(null, "Valor incorrecto");
                            } else if (bingo[2][k] != 0 && (bingo[2][k] == bingo[0][k] ||
                                    bingo[2][k] == bingo[1][k])) {
                                flag = false;
                                JOptionPane.showMessageDialog(null, "Valor incorrecto");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Valor incorrecto");
                            flag = false;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Valor incorrecto");
                        flag = false;
                    }
                } while (!flag);
            }
            ordenar();
            rangoUno += 10;
            rangoDos += 10;
            k++;
        }
        for (int i = 0; i < bingo.length; i++) {
            for (int j = 0; j < bingo[i].length; j++) {
                System.out.print(bingo[i][j] + ", ");
            }
            System.out.println("");
        }
    }

    public static void ordenar() {
        if ( bingo[0][k] >  bingo[1][k] &&  bingo[0][k] >  bingo[2][k]) {
            int aux = 0;
            if (bingo[1][k] > bingo[2][k]) {
                aux = bingo[0][k];
                bingo[0][k] = bingo[2][k];
                bingo[2][k] = aux;
            } else {
                aux =  bingo[0][k];
                bingo[0][k] = bingo[1][k];
                bingo[1][k] = bingo[2][k];
                bingo[2][k] = aux;
            }
        } else if (bingo[1][k] > bingo[0][k] && bingo[1][k] > bingo[2][k]) {
            int aux = 0;
            int auxDos = 0;
            if ( bingo[0][k] > bingo[2][k]) {
                aux = bingo[0][k];
                auxDos = bingo[1][k];
                bingo[0][k] = bingo[2][k];
                bingo[1][k] = aux;
                bingo[2][k] = auxDos;
            } else {
                aux = bingo[1][k];
                bingo[1][k] = bingo[2][k];
                bingo[2][k] = aux;
            }
        } else {
            if ( bingo[0][k] >  bingo[1][k]) {
                int aux = bingo[0][k];
                bingo[0][k] = bingo[1][k];
                bingo[1][k] = aux;
            }
        }

    }
}