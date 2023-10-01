package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RegresiBerganda {
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

    public static double[][] multiRegresi(double[][] mtrx, int n) {

        // n : jumlah peubah + 1
        // persamaan berbentuk Xb = y
        // solusi : b = inverse(X)*y
        // pisah matrix dl jadi x dan y

        double[][] X = new double[n][n];
        double[][] y = new double[n][1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (j == n) {
                    y[i][0] = mtrx[i][j];
                } else {
                    X[i][j] = mtrx[i][j];
                }
            }
        }

        double[][] b, m;

        // Matriks m = new Matriks(n, n);
        // m.readFromVariable(X);
        // m.inverseMatrixOBE(n);
        m = X;
        m = inverseMatrixOBE(m, n);

        b = multiplyMatrix(m, y);
        return b;
    }

    public static double[][] prosesRegresi(double[][] mtrx, int rowMat, int colMat) {

        int n = rowMat; // banyak sampel
        int k = colMat - 1; // banyak parameter

        // persamaan berbentuk y = bX, cari b

        // matiks y
        double[][] y = new double[colMat][1];
        for (int ind = 0; ind < colMat; ind++) {
            y[ind][0] = 0;
            for (int i = 0; i < n; i++) {
                if (ind == 0) {
                    // jumlah yi aja
                    y[ind][0] += mtrx[i][colMat - 1];
                } else {
                    y[ind][0] += mtrx[i][colMat - 1] * mtrx[i][ind - 1];
                }
            }
        }

        // matriks X
        double[][] X = new double[colMat][colMat];
        for (int ind = 0; ind < colMat; ind++) {
            for (int j = 0; j < colMat; j++) {
                X[ind][j] = 0;
                if (j >= ind) {
                    // koordinat 0,0
                    if (ind == 0 && j == 0) {
                        X[ind][j] = n;
                    } else {
                        // inisiasi nilai 0
                        X[ind][j] = 0;
                        // masuk ke sigma
                        for (int i = 0; i < n; i++) {
                            // baris 0
                            if (ind == 0 && j > 0) {
                                X[ind][j] += mtrx[i][j - 1];
                            } else if (ind > 0) {
                                // baris selain 0
                                X[ind][j] += (mtrx[i][j - 1]) * (mtrx[i][ind - 1]);
                            }
                        }
                    }
                } else {
                    X[ind][j] = X[j][ind];
                }

            }
        }

        double[][] ans = new double[colMat][colMat + 1];

        for (int i = 0; i < colMat; i++) {
            for (int j = 0; j < colMat + 1; j++) {
                if (j == colMat) {
                    ans[i][j] = y[i][0];
                } else {
                    ans[i][j] = X[i][j];
                }
            }
        }

        return ans;
    }

    public static void ujiRegresi(double[][] b, double[] test, int n) {

        System.out.print("y = ");
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                System.out.print(b[i][0] + " ");
            } else {
                if (b[i][0] > 0)
                    System.out.print("+" + b[i][0] + "x" + i + " ");
                else if (b[i][0] < 0) {
                    System.out.print(b[i][0] + "x" + i + " ");
                }

            }
        }

        double res = 0;

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                res += b[i][0];
            } else {
                res += (test[i - 1] * b[i][0]);
            }
        }
        System.out.println("maka hasilnya pada test case di atas adalah " + res);

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

    public static void mainProses(int methodInput) {
        double[][] matrix = new double[0][0];
        int n, m;
        n = 0;
        m = -1;
        double[] uji = new double[0];
        Scanner scan = new Scanner(System.in);

        if (methodInput == 1) {
            System.out.println("Masukkan jumlah peubah:");
            m = scan.nextInt();
            System.out.println("Masukkan jumlah sampel:");
            n = scan.nextInt();
            matrix = new double[n][m + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m + 1; j++) {
                    matrix[i][j] = scan.nextDouble();
                }
            }
            uji = new double[m];
            System.out.println("Masukkan titik :");
            for (int i = 0; i < m; i++) {
                uji[i] = scan.nextDouble();
            }
            m++;

        } else if (methodInput == 2) {
            System.out.println("Masukkan nama file:");
            String FileName = scan.nextLine();
            matrix = readFile(FileName);
            if (matrix.length == 0) {
                return;
            }
            n = matrix.length;
            m = matrix[0].length;
            uji = new double[m - 1];
            System.out.println("Masukkan titik :");
            for (int i = 0; i < m - 1; i++) {
                uji[i] = scan.nextDouble();
            }
        } else {
            System.out.println("kembali ke menu utama");
            return;
        }

        if (matrix.length == 0) {
            System.out.println("input tidak valid");
            return;
        }

        // mulai proses
        matrix = prosesRegresi(matrix, n, m);
        matrix = multiRegresi(matrix, matrix.length);
        displayAugmentedMatrix(matrix, matrix.length, matrix[0].length);
        ujiRegresi(matrix, uji, matrix.length);
    }

}