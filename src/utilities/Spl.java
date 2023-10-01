package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Spl {
    public static boolean solCheck(double[][] mat, int n, int m) {
        // true jika tidak ada solusi, false jika ada solusi
        boolean ans = true;

        // cek baris terakhir
        int i = 0;
        while (i < m - 1 && ans) {
            if (mat[n - 1][i] != 0) {
                ans = false;
            }
            i++;
        }

        return ans;
    }

    public static void printSolusi(double[][] mat, int n, int m) {
        // matriks mat berukuran n x m;
        // 3 kasus :
        // 1. No solution : baris terakhir 0 semua kecuali mat[n][m]
        // 2. Single solution : baris terakhir tersisa 1 utama
        // 3. Parametric solution : baris terakhir 0 semua

        double[] sol = new double[m - 1]; // menyimpan solusi
        boolean[] valSol = new boolean[m - 1]; // apakah valuenya ada atau belum
        String[] strSol = new String[m - 1]; // solusi dalam bentuk string
        String[] paramLetter = { "p", "q", "r", "s", "t", "u", "v", "w" };

        // insiasi tiap array
        for (int i = 0; i < m - 1; i++) {
            sol[i] = 0;
        }
        for (int i = 0; i < m - 1; i++) {
            valSol[i] = false;
        }
        for (int i = 0; i < m - 1; i++) {
            strSol[i] = " ";
        }

        // Kasus 1
        // No solution
        if (solCheck(mat, n, m) && mat[n - 1][m - 1] != 0) {
            // Kasus 1
            System.out.println("Tidak ada solusi.");
        } else if (solCheck(mat, n, m) && mat[n - 1][m - 1] == 0) {
            // Kasus 3
            System.out.println("Solusi yang diberikan berada dalam bentuk parametrik.");

            int indParam = 0; // menggunakan huruf p terlebih dahulu

            for (int i = n - 2; i >= 0; i--) {
                // skip baris terakhir karena sudah pasti bernilai 0 semua
                int j = 0, oneLeading = -1;
                while (j < m - 1) {
                    if (mat[i][j] == 1 && oneLeading == -1) {
                        // mencari indeks 1 utama tiap baris
                        oneLeading = j;
                    }

                    if (mat[i][j] != 0) {
                        if (valSol[j]) {
                            // cek apakah solusinya angka atau tidak
                            mat[i][m - 1] -= (mat[i][j] * sol[j]);
                        } else {
                            // berarti parametrik
                            // cek apakah solusi parametrik sudah ada atau belum
                            if (strSol[j] == " ") {
                                // belum ada solusi parametrik sama sekali
                                strSol[j] = paramLetter[indParam];
                                indParam++;
                            } else {
                                // sudah ada solusi

                            }
                        }
                    }
                    j++;
                }

                // sol[oneLeading] = mat[i][m-1];
                // valSol[oneLeading] = true;
                strSol[oneLeading] = String.valueOf(mat[i][m - 1]);
                ;

                j = oneLeading + 1;
                while (j < m - 1) {
                    if (mat[i][j] != 0) {
                        if (strSol[j] == "0.0") {
                            mat[i][j] = 0;
                        }
                        if (mat[i][j] > 0) {
                            strSol[oneLeading] += "-";
                            strSol[oneLeading] += "(";
                            if (mat[i][j] != 1) {
                                strSol[oneLeading] += String.valueOf(mat[i][j]);
                            }
                            strSol[oneLeading] += strSol[j];
                            strSol[oneLeading] += ")";
                        } else if (mat[i][j] < 0) {
                            strSol[oneLeading] += "+";
                            strSol[oneLeading] += "(";
                            mat[i][j] *= (-1);
                            if (mat[i][j] != 1) {
                                strSol[oneLeading] += String.valueOf(mat[i][j]);
                            }
                            strSol[oneLeading] += strSol[j];
                            strSol[oneLeading] += ")";
                        }
                    }
                    j++;
                }
            }
            for (int i = 0; i < m - 1; i++) {
                System.out.print(strSol[i]);
                System.out.print(" ");
            }
        } else {
            // Kasus 2
            for (int i = n - 1; i >= 0; i--) {
                int j = 0, oneLeading = -1;
                // mencari 1 utama
                // mencari konstanta mulai 1 utama hingga kolom ke m-1
                while (j < m - 1) {
                    if (mat[i][j] == 1 && oneLeading == -1) {
                        oneLeading = j;
                    }
                    if (mat[i][j] != 0) {
                        mat[i][m - 1] -= (mat[i][j] * sol[j]);
                    }
                    j++;
                }
                // System.out.println(oneLeading);
                sol[oneLeading] = mat[i][m - 1];
            }
            for (int i = 0; i < m - 1; i++) {
                System.out.print(sol[i]);
                System.out.print(" ");
            }
        }
    }

    public static double[] add2Row(double[] matrix, double[] mtrx, int col) {
        // row a - n*row b
        for (int i = 0; i < col; i++) {
            matrix[i] += (mtrx[i]);
        }
        return matrix;
    }

    public static boolean isColZero(double[][] matrix, int a, int n) {
        for (int i = 0; i < n; i++) {
            if (matrix[i][a] != 0)
                return false;
        }
        return true;
    }

    public static double[] substract2Row(double[] matrix, double[] mtrx, double n, int col) {
        // row a - n*row b
        for (int i = 0; i < col; i++) {
            matrix[i] -= (n * mtrx[i]);
        }
        return matrix;
    }

    public static double[] divideRow(double[] matrix, int col, double div) {
        // row a - n*row b
        for (int i = 0; i < col; i++) {
            matrix[i] /= div;
        }
        return matrix;
    }

    public static boolean isRowZero(double[] matrix, int n) {
        for (int i = 0; i < n; i++) {
            if (matrix[i] != 0)
                return false;
        }
        return true;
    }

    public static void displayAugmentedMatrix(double[][] matrix, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double[][] getRowEchelon(double[][] matrix, int n, int m) {
        // cek yang kosongngan semua taruh ke bawah
        for (int i = 0; i < n; i++) {
            if (isRowZero(matrix[i], m)) {
                for (int x = i + 1; x < n; x++) {
                    // swapRowMatrix(i, x, n + 1);
                    // meragukan
                    double[] temp = matrix[i];
                    matrix[i] = matrix[x];
                    matrix[x] = temp;
                }
            }
        }
        int col = 0;
        for (int i = 0; i < n; i++) {
            if (!isRowZero(matrix[i], m)) {

                if (matrix[i][col] != 0) {
                    // dibagi sek dengan dirinya sendiri biar satu
                    matrix[i] = divideRow(matrix[i], m, matrix[i][col]);
                    for (int j = i + 1; j < n; j++) {
                        // untuk ngenolkan yg bukan baris 1
                        if (matrix[j][col] != 0) {
                            matrix[j] = substract2Row(matrix[j], matrix[i], matrix[j][col] / matrix[i][col], m);
                        }
                    }
                } else {
                    // gimana kalo ada baris dan kolom yg nol
                    if (isColZero(matrix, col, n)) {
                        // majuin kolomnya aja, tapi i jangan nambah
                        i--;
                    } else {
                        // kalo kolomnya ndak nol
                        int temp = i;
                        int idx = -1;
                        while (temp < n && idx == -1) {
                            if (matrix[temp][col] != 0) {
                                idx = temp;
                            }
                            temp++;
                        }
                        if (idx == -1) {
                            i--;
                        } else {
                            // add2Row(i, idx, 1, n + 1);
                            matrix[i] = add2Row(matrix[i], matrix[idx], m);
                            matrix[i] = divideRow(matrix[i], m, matrix[i][col]);
                            for (int j = i + 1; j < n; j++) {
                                if (matrix[j][col] != 0) {
                                    matrix[j] = substract2Row(matrix[j], matrix[i], matrix[j][col] / matrix[i][col], m);
                                }
                            }
                        }
                    }
                }
                col++;
            }
        }
        return matrix;
    }

    public static double[][] getReductionRowEchelon(double[][] matrix, int n, int m) {
        // cek yang kosongngan semua taruh ke bawah
        for (int i = 0; i < n; i++) {
            if (isRowZero(matrix[i], m)) {
                for (int x = i + 1; x < n; x++) {
                    // swapRowMatrix(i, x, n + 1);
                    // meragukan
                    double[] temp = matrix[i];
                    matrix[i] = matrix[x];
                    matrix[x] = temp;
                }
            }
        }
        int col = 0;
        for (int i = 0; i < n; i++) {
            if (!isRowZero(matrix[i], m)) {

                if (matrix[i][col] != 0) {
                    // dibagi sek dengan dirinya sendiri biar satu
                    matrix[i] = divideRow(matrix[i], m, matrix[i][col]);
                    for (int j = 0; j < n; j++) {
                        if (j != i) {
                            // untuk ngenolkan yg bukan baris 1
                            if (matrix[j][col] != 0) {
                                matrix[j] = substract2Row(matrix[j], matrix[i], matrix[j][col] / matrix[i][col], m);
                            }
                        }
                    }
                } else {
                    // gimana kalo ada baris dan kolom yg nol
                    if (isColZero(matrix, col, n)) {
                        // majuin kolomnya aja, tapi i jangan nambah
                        i--;
                    } else {
                        // kalo kolomnya ndak nol
                        int temp = i;
                        int idx = -1;
                        while (temp < n && idx == -1) {
                            if (matrix[temp][col] != 0) {
                                idx = temp;
                            }
                            temp++;
                        }
                        if (idx == -1) {
                            i--;
                        } else {
                            // add2Row(i, idx, 1, n + 1);
                            matrix[i] = add2Row(matrix[i], matrix[idx], m);
                            matrix[i] = divideRow(matrix[i], m, matrix[i][col]);
                            for (int j = 0; j < n; j++) {
                                if (j != i) {
                                    if (matrix[j][col] != 0) {
                                        matrix[j] = substract2Row(matrix[j], matrix[i], matrix[j][col] / matrix[i][col],
                                                m);
                                    }
                                }
                            }
                        }
                    }
                }
                col++;
            }
        }
        return matrix;
    }

    public static double[][] inverseMatrixOBE(double[][] matriks, int n) {
        double[][] temp = new double[n][n * 2];
        double[][] mIdentitas = new double[n][n];
        // masukkan ke matriks temp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = matriks[i][j];
                if (i == j) {
                    mIdentitas[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][n + j] = mIdentitas[i][j];
            }
        }
        matriks = temp;
        matriks = getReductionRowEchelon(matriks, n, n * 2);
        temp = matriks;
        matriks = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriks[i][j] = temp[i][n + j];
            }
        }
        return matriks;
    }

    public static double[][] readFile(String fileName) {

        try {
            String[] tempData;
            tempData = new String[1000];
            String dir = "../test/input/" + fileName;
            File myObj = new File(dir);
            Scanner myReader = new Scanner(myObj);

            int rowMat = 0, colMat = 0;

            // baca file
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                tempData[rowMat] = data;
                rowMat++;
            }

            // hitung jumlah kolom
            for (int i = 0; i < tempData[0].length(); i++) {
                if (tempData[0].charAt(i) == ' ') {
                    colMat++;
                }
            }
            colMat++;
            double[][] data = new double[rowMat][colMat];

            int ind_col;
            for (int i = 0; i < rowMat; i++) {
                // tiap baris
                ind_col = 0;
                String tempString = tempData[i];
                String tempIsi = "";
                for (int j = 0; j < tempData[i].length(); j++) {
                    if (tempString.charAt(j) == ' ') {
                        data[i][ind_col] = Double.valueOf(tempIsi);
                        tempIsi = "";
                        ind_col += 1;
                    } else {
                        tempIsi += tempString.charAt(j);
                    }
                }
                // karakter terakhir
                data[i][ind_col] = Double.valueOf(tempIsi);
            }

            System.out.println("Matriks " + fileName + " berhasil dibaca.");
            return data;
        } catch (FileNotFoundException e) {
            double[][] a = new double[0][0];
            System.out.println("File tidak ditemukan.");
            return a;
        }
    }

    public static double[][] multiplyMatrix(double[][] a, double[][] b) {
        double[][] c = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    public static double getDeterminanEksKof(double[][] m, int n) {
        // untuk matrix n*n cari determinan dengan ekspansi kofaktor
        double res;
        res = 0.0;
        if (n == 2) {
            return (m[0][0] * m[1][1] - m[1][0] * m[0][1]);
        } else {
            for (int ii = 0; ii < n; ii++) {
                int x = 0;
                double temp[][] = new double[n - 1][n - 1];
                for (int i = 1; i < n; i++) {
                    int y = 0;
                    for (int j = 0; j < n; j++) {
                        if (j != ii) {
                            temp[x][y] = m[i][j];
                            y++;
                        }
                    }
                    x++;
                }
                res += (double) (((ii % 2 == 0) ? 1.0 : -1.0) * (m[0][ii]) * getDeterminanEksKof(temp, n - 1));
            }
        }
        return res;
    }

    public static double[][] cramerMethod(double[][] matrix, int n) {
        double[][] res = new double[n][1];
        double det = getDeterminanEksKof(matrix, n);
        for (int i = 0; i < n; i++) {
            displayAugmentedMatrix(matrix, n, n);
            // swapColMatrix(i, n, n);
            for (int j = 0; j < n; j++) {
                double temp;
                temp = matrix[j][i];
                matrix[j][i] = matrix[j][n];
                matrix[j][n] = temp;
            }
            res[i][0] = getDeterminanEksKof(matrix, n);
            // swapColMatrix(i, n, n);
            for (int j = 0; j < n; j++) {
                double temp;
                temp = matrix[j][i];
                matrix[j][i] = matrix[j][n];
                matrix[j][n] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            res[i][0] /= det;
        }
        return res;

    }

    public static void mainProses(int menu, int methodInput) {
        double[][] matrix = new double[0][0];
        int n, m;
        n = 0;
        m = -1;
        Scanner scan = new Scanner(System.in);

        if (methodInput == 1) {
            System.out.println("Masukkan jumlah baris:");
            n = scan.nextInt();
            System.out.println("Masukkan jumlah kolom:");
            m = scan.nextInt();
            matrix = new double[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = scan.nextDouble();
                }
            }
        } else if (methodInput == 2) {
            System.out.println("Masukkan nama file:");
            String FileName = scan.nextLine();
            matrix = readFile(FileName);
            if (matrix.length == 0) {
                return;
            }
            n = matrix.length;
            m = matrix[0].length;
        } else {
            System.out.println("kembali ke menu utama");
            return;
        }

        if (matrix.length == 0) {
            System.out.println("input tidak valid");
            return;
        }

        // lanjut proses matrix
        if (menu == 1) {
            System.out.println("Menggunakan Metode Gauss");
            matrix = getRowEchelon(matrix, n, m);
            displayAugmentedMatrix(matrix, n, m);
            printSolusi(matrix, n, m);

        } else if (menu == 2) {
            System.out.println("Menggunakan Metode Gauss-Jordan");
            matrix = getReductionRowEchelon(matrix, n, m);
            displayAugmentedMatrix(matrix, n, m);
            printSolusi(matrix, n, m);

        } else if (menu == 3) {
            System.out.println("Menggunakan Metode Matriks Balikan");
            double[][] value = new double[n][1];
            for (int i = 0; i < n; i++) {
                value[i][0] = matrix[i][m - 1];
            }
            matrix = inverseMatrixOBE(matrix, n);
            matrix = multiplyMatrix(matrix, value);
            displayAugmentedMatrix(matrix, n, 1);

        } else if (menu == 4) {
            if (n != m - 1) {
                System.out.println("Tidak Bisa Menggunakan Metode Cramer");
            } else {
                System.out.println("Menggunakan Metode Cramer");
                matrix = cramerMethod(matrix, n);
                displayAugmentedMatrix(matrix, n, 1);
            }

        } else {
            System.out.println("kembali ke menu utamaa");
            return;

        }
    }

}
