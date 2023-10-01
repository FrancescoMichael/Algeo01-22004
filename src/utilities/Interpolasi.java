package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Interpolasi {
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
        int n;
        n = 0;
        double target;
        target = 0;
        Scanner scan = new Scanner(System.in);

        if (methodInput == 1) {
            System.out.println("Masukkan jumlah baris:");
            n = scan.nextInt();
            matrix = new double[n + 1][2];
            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < 2; j++) {
                    matrix[i][j] = scan.nextDouble();
                }
            }
            target = scan.nextDouble();
            n++;
        } else if (methodInput == 2) {
            System.out.println("Masukkan nama file:");
            String FileName = scan.nextLine();
            matrix = readFile(FileName);
            if (matrix.length == 0) {
                return;
            }
            n = matrix.length;
            double[][] temp = matrix;
            matrix = new double[n - 1][2];
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < 2; j++) {
                    matrix[i][j] = temp[i][j];
                }
            }
            target = temp[n - 1][0];
            n--;
        } else {
            System.out.println("kembali ke menu utama");
            return;
        }
        if (matrix.length == 0) {
            System.out.println("input tidak valid");
            return;
        }

        // lanjut proses matrix
        System.out.println("Memulai interpolasi");
        // menggunakan bentuk Ax = B, cari matrix x

        // convert ke matrix A
        double[][] A;
        A = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = Math.pow(matrix[i][0], j);
            }
        }

        // convert ke matrix B
        double[][] B;
        B = new double[n][1];
        for (int i = 0; i < n; i++) {
            B[i][0] = matrix[i][1];
        }

        A = inverseMatrixOBE(A, n);

        matrix = multiplyMatrix(A, B);
        double res = 0;

        // displayAugmentedMatrix(matrix, n, 1);
        System.out.print("f(x) = ");
        StringBuilder str = new StringBuilder();
        str.append("f(x) = ");
        for (int i = 0; i < matrix.length; i++) {
            if (i == 0) {
                res += matrix[i][0];
                System.out.print(matrix[i][0] + " ");
                str.append(matrix[i][0] + " ");
            } else {
                res += (matrix[i][0] * Math.pow(target, i));
                if (matrix[i][0] > 0) {
                    System.out.print("+" + matrix[i][0] + "x^" + i + " ");
                    str.append("+" + matrix[i][0] + "x^" + i + " ");
                } else if (matrix[i][0] < 0) {
                    System.out.print(matrix[i][0] + "x^" + i + " ");
                    str.append(matrix[i][0] + "x^" + i + " ");
                }

            }
        }
        System.out.println("\nf(" + target + ")" + " = " + res);
        str.append("\nf(" + target + ")" + " = " + res);

        // print data
        System.out.println("\nApakah hasil akhir mau di save ke file? (1 for yes, lainnya no)");
        int p = scan.nextInt();

        if (p == 1) {
            Scanner scanString = new Scanner(System.in);
            System.out.println("masukkan nama file .txt");
            String path = scanString.nextLine();
            FileDitulis.Codot(str.toString(), path);
        }

    }
}
