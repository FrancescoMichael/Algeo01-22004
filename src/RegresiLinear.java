import utilities.*;

public class RegresiLinear {
    public static void multiRegresi(double [][] y, double [][] X, int n){
        // n : jumlah peubah + 1
        // persamaan berbentuk Xb = y
        // solusi : b = inverse(X)*y
        double [][] b = new double[n][n];

        Matriks m = new Matriks();

        b = m.multiplyMatrix(X, y);
    }
}
