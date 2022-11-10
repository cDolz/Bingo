import javax.swing.*;

public class Main {
    public static int[][] bingo = new int[3][9];
    public static String[][] sBingo = new String[3][9];
    public static int columna;
    public static int numRandom;
    public static boolean flag = false;
    public static int contadorLinea;
    public static int contadorBingo;

    public static void main(String[] args) {
        do {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    do {
                        generateRandom();
                        setValue(i);
                    } while (!flag);
                }
            }
            for (int i = 0; i < 9; i++) {
                if (bingo[0][i] == 0 && bingo[1][i] == 0 && bingo[2][i] == 0) {
                    flag = false;
                    break;
                }
            }
        } while (!flag);
        bingoToString();
        printSBingo();
        System.out.println();
        generateRandomPlayNums();
    }

    private static void generateRandomPlayNums() {
        int[] allNums = new int[90];
        int contadorNums = 0;
        do {
            numRandom = (int) (Math.random() * (90 - 1) + 1);
            if (validarPlayNum(allNums)) {
                allNums[contadorNums] = numRandom;
                contadorNums++;
                JOptionPane.showMessageDialog(null, "El " + numRandom + "!");
            }
            comprobarPlayNum();
            printSBingo();
            if (contadorLinea < 1) {
                comprobarLinea();
            }
            comprobarBingo();
            System.out.println();
        } while (contadorBingo < 1);
    }

    private static void comprobarLinea() {
        for (String[] strings : sBingo) {
            int contador = 0;
            for (String string : strings) {
                if (string.equals("X") || string.equals(" ")) {
                    contador++;
                }
                if (contador == 9) {
                    contadorLinea++;
                    JOptionPane.showMessageDialog(null, "LINEA!");
                }
            }
        }
    }

    private static void comprobarBingo() {
        int contador = 0;
        for (String[] strings : sBingo) {
            for (String string : strings) {
                if (string.equals("X") || string.equals(" ")) {
                    contador++;
                }
                if (contador == 27) {
                    contadorBingo++;
                    JOptionPane.showMessageDialog(null, "¡¡BINGO!!");
                }
            }
        }
    }

    private static void comprobarPlayNum() {
        for (int i = 0; i < sBingo.length; i++) {
            for (int j = 0; j < sBingo[i].length; j++) {
                if (sBingo[i][j].equals(Integer.toString(numRandom))) {
                    sBingo[i][j] = "X";
                }
            }
        }
    }

    private static boolean validarPlayNum(int[] allNums) {
        int contador = 0;
        flag = true;
        for (int allNum : allNums) {
            if (numRandom == allNum) {
                contador++;

            }
        }
        if (contador != 0) {
            flag = false;
        }
        return flag;
    }

    private static void bingoToString() {
        for (int i = 0; i < bingo.length; i++) {
            for (int j = 0; j < bingo[i].length; j++) {
                sBingo[i][j] = String.valueOf(bingo[i][j]);
            }
        }
    }

    private static void setValue(int i) {
        if (bingo[i][columna] == 0 && notSup() && numRandom != 0) {
            bingo[i][columna] = numRandom;
            flag = true;
        } else {
            flag = false;
        }
    }

    private static boolean notSup() {
        if (bingo[0][columna] != 0 && numRandom <= bingo[0][columna]) {
            flag = false;
        } else if (bingo[0][columna] == 0 && bingo[1][columna] != 0 && numRandom <= bingo[1][columna]) {
            flag = false;
        } else flag = bingo[0][columna] == 0 || bingo[1][columna] == 0;
        return flag;
    }

    private static void generateRandom() {
        columna = (int) (Math.random() * 9);
        int min = columna * 10;
        int max = (columna * 10) + 9;
        if (columna == 8) {
            max = 90;
        }
        numRandom = (int) (Math.random() * (max - min) + min);
    }

    private static void printSBingo() {
        for (int i = 0; i < sBingo.length; i++) {
            for (int j = 0; j < sBingo[i].length; j++) {
                if (sBingo[i][j].equals("0")) {
                    sBingo[i][j] = " ";
                }
                if (sBingo[i][j].length() == 1) {
                    System.out.print("[  " + sBingo[i][j] + " ]");
                } else {
                    System.out.print("[ " + sBingo[i][j] + " ]");
                }
            }
            System.out.println();
        }
    }
}
