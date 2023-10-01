package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Determinan {
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

    public static double getDeteminantOBE(double[][] matrix, int n, int m) {
        // cek yang kosongngan semua taruh ke bawah
        int pow = 0;
        double multi = 1.0;
        for (int i = 0; i < n; i++) {
            if (isRowZero(matrix[i], m)) {
                for (int x = i + 1; x < n; x++) {
                    // swapRowMatrix(i, x, n + 1);
                    // meragukan
                    double[] temp = matrix[i];
                    matrix[i] = matrix[x];
                    matrix[x] = temp;
                    pow++;
                }
            }
        }
        int col = 0;
        for (int i = 0; i < n; i++) {
            if (!isRowZero(matrix[i], m)) {

                if (matrix[i][col] != 0) {
                    // dibagi sek dengan dirinya sendiri biar satu
                    multi /= matrix[i][col];
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
                            multi /= matrix[i][col];

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
        return (double) ((Math.pow(-1, pow)) / multi);
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

    public static void mainProses(int menu, int methodInput) {
        double[][] matrix = new double[0][0];
        int n, m;
        n = 0;
        Scanner scan = new Scanner(System.in);

        if (methodInput == 1) {
            System.out.println("Masukkan ukuran matriks:");
            n = scan.nextInt();
            matrix = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
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
        } else if (matrix.length != matrix[0].length) {
            System.out.println("input tidak valid");
            return;
        }

        // lanjut proses matrix
        if (menu == 1) {
            System.out.println("Menggunakan Metode Gauss");
            double res = getDeterminanEksKof(matrix, n);
            System.out.println("hasilnya adalah " + res);

        } else if (menu == 2) {
            System.out.println("Menggunakan Metode Balikan");
            double res = getDeteminantOBE(matrix, n, n);
            System.out.println("hasilnya adalah " + res);

        } else {
            System.out.println("kembali ke menu utamaa");
            return;

        }
    }
}
