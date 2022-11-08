public class Main {
    public static int[][] bingo = new int[3][9];
    public static String[][] sBingo = new String[3][9];
    public static int columna;
    public static int numRandom;
    public static boolean flag = false;

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                do {
                    generateRandom();
                    setValue(i);
                } while (!flag);
            }
        }
        bingoToString();
        printSBingo();
        generateRandomPlayNums();
    }

    private static void generateRandomPlayNums() {
        int[] allNums = new int[90];
        int contador = 0;
        do {
            numRandom = (int) (Math.random() * (90 - 1) + 1);
            if (comprobarPlayNum(allNums)) {
                allNums[contador] = numRandom;
                contador++;
            }
        }while (contador < 90);
    }

    private static boolean comprobarPlayNum(int[] allNums) {
        int contador = 0;
        flag = true;
        for (int i = 0; i < allNums.length; i++) {
            if (numRandom == allNums[i]) {
                contador++;
            }
        }
        if (contador != 0){
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
        if (columna == 9) {
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
