package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RegresiLinear {
    static double[][] datatoMatrix(String[] tempData, int rowMat, int colMat) {
        double[][] ans;
        ans = new double[rowMat][colMat];
        int ind_col; // index kolom matrix
        for (int i = 0; i < rowMat; i++) {
            // tiap baris
            ind_col = 0;
            String tempString = tempData[i];
            String tempIsi = "";
            for (int j = 0; j < tempData[i].length(); j++) {
                if (tempString.charAt(j) == ' ') {
                    ans[i][ind_col] = Double.valueOf(tempIsi);
                    tempIsi = "";
                    ind_col += 1;
                } else {
                    tempIsi += tempString.charAt(j);
                }
            }
            // karakter terakhir
            ans[i][ind_col] = Double.valueOf(tempIsi);
        }
        return ans;
    }

    public static double[][] readFileRegresi(String path) {
        try {
            String[] tempData;
            tempData = new String[1000];

            File myObj = new File(path);
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

            double mtrx[][] = datatoMatrix(tempData, rowMat, colMat);

            myReader.close();
            return mtrx;

        } catch (FileNotFoundException e) {
            System.out.println("Your file is wrong.");
            // e.printStackTrace();

            double[][] x = new double[0][0];

            return x;
        }
    }

    public static double[][] readRegresiFromCli() {
        boolean x = true;
        Scanner objScan = new Scanner(System.in);
        // while (x) {
        System.out.print("Masukkan jumlah Peubah ");
        int n = objScan.nextInt();
        System.out.print("Masukkan jumlah Sampel ");
        int m = objScan.nextInt();
        System.out.println("silahkan masukkan data anda");
        double[][] mtrx = new double[m][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n + 1; j++) {
                mtrx[i][j] = objScan.nextDouble();
            }
        }

        return mtrx;

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

        double[][] b;

        Matriks m = new Matriks(n, n);
        m.readFromVariable(X);
        m.inverseMatrixOBE(n);

        b = m.multiplyMatrix(m.getMatriks(), y);
        return b;
    }

    public static void ujiRegresi(double[][] b, int n) {
        Scanner objScan = new Scanner(System.in);

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
        System.out.println("Masukkan titik yang ingin di uji");
        // int tc = objScan.nextInt();

        double[] test = new double[n];
        double res = 0;
        for (int i = 0; i < n; i++) {
            test[i] = objScan.nextDouble();
        }
        for (int i = 0; i < n; i++) {
            res += (test[i] * b[i][0]);
        }
        System.out.println("maka hasilnya pada test case di atas adalah " + res);

    }

}
