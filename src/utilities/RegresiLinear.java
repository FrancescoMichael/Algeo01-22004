package utilities;

import java.util.Scanner;

import utilities.*;

public class RegresiLinear {
    public static void multiRegresi(double[][] mtrx, int n) {

        // n : jumlah peubah + 1
        // persamaan berbentuk Xb = y
        // solusi : b = inverse(X)*y
        // pisah matrix dl jadi x dan y
        Scanner objScan = new Scanner(System.in);

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
        System.out.println("Masukkan Jumlah sampel yang ingin di uji");
        int tc = objScan.nextInt();
        while (tc > 0) {
            double[] test = new double[n];
            double res = 0;
            for (int i = 0; i < n; i++) {
                test[i] = objScan.nextDouble();
            }
            for (int i = 0; i < n; i++) {
                res += (test[i] * b[i][0]);
            }
            System.out.println("maka hasilnya pada test case di atas adalah " + res);
            tc--;
        }

    }
}
