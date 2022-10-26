import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int bingo[][] = new int[3][9];
        boolean flag = false;
        /*for (int i = 0; i < bingo.length; i++) {
            for (int j = 0; j < bingo[i].length; j++) {
                bingo[i][j] = Integer.parseInt(JOptionPane.showInputDialog("Dime un numero"));
            }
        }*/
        for (int i = 0; i < 3; i++) {
            do {
                try {
                    bingo[i][0] = Integer.parseInt(JOptionPane.showInputDialog("Dime un numero"));
                    if (bingo[i][0] > 0 && bingo[i][0] < 10) {
                        flag = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Valor incorrecto");
                        flag = false;
                    }
                    if (bingo[i][0] < bingo[0][0]) {
                        bingo[0][0] = bingo[2][0];
                        bingo[i][0] = bingo[0][0];
                    } else if (bingo[i][0] > bingo[0][0]) {
                        bingo[0][0] = bingo[2][0];
                        bingo[i][0] = bingo[0][0];
                    } else {
                        bingo[i][0] = bingo[0][0];
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Valor incorrecto");
                    flag = false;
                }
            } while (!flag);
        }
        for (int i = 0; i < bingo.length; i++) {
            for (int j = 0; j < bingo[i].length; j++) {
                System.out.print(bingo[i][j] + ", ");
            }
            System.out.println("");
        }
    }
}