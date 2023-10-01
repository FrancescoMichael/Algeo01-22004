package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bikubik {
    public static double[][] getMatX(){
        double[][] X = new double[16][16];
        double xX = 0, yX = 0;

        // f
            int indRow = 0;
            for (; indRow < 4; indRow++) {
                if (indRow % 4 == 0) {
                    xX = 0;
                    yX = 0;
                } else if (indRow % 4 == 1) {
                    xX = 1;
                    yX = 0;
                } else if (indRow % 4 == 2) {
                    xX = 0;
                    yX = 1;
                } else {
                    xX = 1;
                    yX = 1;
                }
                double suku1 = 0, suku2 = 0;

                int indCol = 0;
                for (int i = 0; i <= 3; i++) {
                    for (int j = 0; j <= 3; j++) {
                        if (xX == 0 && j == 0) {
                            suku1 = 1;
                        } else {
                            suku1 = Math.pow(xX, j);
                        }

                        if (yX == 0 && i == 0) {
                            suku2 = 1;
                        } else {
                            suku2 = Math.pow(yX, i);
                        }

                        X[indRow][indCol] = suku1 * suku2;
                        indCol++;
                    }
                }
            }

            // fx
            for (; indRow < 8; indRow++) {
                if (indRow % 4 == 0) {
                    xX = 0;
                    yX = 0;
                } else if (indRow % 4 == 1) {
                    xX = 1;
                    yX = 0;
                } else if (indRow % 4 == 2) {
                    xX = 0;
                    yX = 1;
                } else {
                    xX = 1;
                    yX = 1;
                }

                double suku1 = 0, suku2 = 0;

                int indCol = 0;
                for (int j = 0; j < 4; j++) {
                    for (int i = 0; i < 4; i++) {
                        if (i == 0) {
                            X[indRow][indCol] = 0;
                            indCol++;
                        } else {
                            if (xX == 0 && (i - 1) <= 0) {
                                suku1 = 1;
                            } else {
                                suku1 = Math.pow(xX, i - 1);
                            }

                            if (yX == 0 && j <= 0) {
                                suku2 = 1;
                            } else {
                                suku2 = Math.pow(yX, j);
                            }

                            X[indRow][indCol] = i * suku1 * suku2;
                            indCol++;
                        }
                    }
                }
            }

            // fx
            for (; indRow < 12; indRow++) {
                if (indRow % 4 == 0) {
                    xX = 0;
                    yX = 0;
                } else if (indRow % 4 == 1) {
                    xX = 1;
                    yX = 0;
                } else if (indRow % 4 == 2) {
                    xX = 0;
                    yX = 1;
                } else {
                    xX = 1;
                    yX = 1;
                }

                double suku1 = 0, suku2 = 0;

                int indCol = 0;
                for (int j = 0; j < 4; j++) {
                    for (int i = 0; i < 4; i++) {
                        if (j == 0) {
                            X[indRow][indCol] = 0;
                            indCol++;
                        } else {
                            if (xX == 0 && i <= 0) {
                                suku1 = 1;
                            } else {
                                suku1 = Math.pow(xX, i);
                            }

                            if (yX == 0 && (j - 1) <= 0) {
                                suku2 = 1;
                            } else {
                                suku2 = Math.pow(yX, j - 1);
                            }

                            X[indRow][indCol] = j * suku1 * suku2;
                            indCol++;
                        }
                    }
                }
            }

            // fxy
            for (; indRow < 16; indRow++) {
                if (indRow % 4 == 0) {
                    xX = 0;
                    yX = 0;
                } else if (indRow % 4 == 1) {
                    xX = 1;
                    yX = 0;
                } else if (indRow % 4 == 2) {
                    xX = 0;
                    yX = 1;
                } else {
                    xX = 1;
                    yX = 1;
                }

                double suku1 = 0, suku2 = 0;

                int indCol = 0;
                for (int j = 0; j < 4; j++) {
                    for (int i = 0; i < 4; i++) {
                        if (xX == 0 && (i - 1) <= 0) {
                            suku1 = 1;
                        } else {
                            suku1 = Math.pow(xX, i - 1);
                        }

                        if (yX == 0 && (j - 1) <= 0) {
                            suku2 = 1;
                        } else {
                            suku2 = Math.pow(yX, j - 1);
                        }

                        X[indRow][indCol] = i * j * suku1 * suku2;
                        indCol++;
                    }
                }
            }
        return X;    
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

    public static boolean isRowZero(double[] matrix, int n) {
        for (int i = 0; i < n; i++) {
            if (matrix[i] != 0)
                return false;
        }
        return true;
    }

    public static double[] add2Row(double[] matrix, double[] mtrx, int col) {
        // row a - n*row b
        for (int i = 0; i < col; i++) {
            matrix[i] += (mtrx[i]);
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

    public static double getSolution(double[][] A, double a, double b){
        double ans = 0;
        int ind_a = 0;

        double suku1 = 0, suku2 = 0;

        for(int j = 0; j < 4; j++){
            for(int i = 0; i < 4; i++){
                if(a == 0 && (i) <= 0){
                    suku1 = 1;
                }else{
                    suku1 = Math.pow(a, i);
                }
                    
                if(b == 0 && (j) <= 0){
                    suku2 = 1;
                }else{
                    suku2 = Math.pow(b, j);
                }
            
                ans+=(suku1*suku2*A[ind_a][0]);
                ind_a++;
            }
        }

        return ans;
    }

    public static double[][] getSolA(double[][] y, double[][] X){
        // mendapatkan matriks A
        // A = inv(X)*y

        return multiplyMatrix(inverseMatrixOBE(X, 16), y);
    }

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

    public static double[][] readFile(String fileName){
        try {
            String[] tempData;
            tempData = new String[1000];

            // Scanner myPath = new Scanner(System.in);
            // System.out.println("Input your file path : ");
            // String path = myPath.nextLine();
            String dir = "../test/input/" + fileName;
            File myObj = new File(dir);
            Scanner myReader = new Scanner(myObj);

            int rowMat = 0, colMat = 4;

            // baca file
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                tempData[rowMat] = data;
                rowMat++;
            }

            // convert baris terakhir tempData ke a dan b
            double a, b;
            double[] tempAB;
            tempAB = new double[2];
            int ind_tempAB = 0;
            String tempIsi = "";
            for (int i = 0; i < tempData[rowMat - 1].length(); i++) {
                if (tempData[rowMat - 1].charAt(i) == ' ') {
                    tempAB[ind_tempAB] = Double.valueOf(tempIsi);
                    tempIsi = "";
                    ind_tempAB += 1;
                } else {
                    tempIsi += tempData[rowMat - 1].charAt(i);
                }
            }
            a = tempAB[0];
            b = Double.valueOf(tempIsi);

            // convert ke matrix
            // mtrx berbentuk 4x4
            double mtrx[][] = datatoMatrix(tempData, 4, 4);

            // menggunakan bentuk y = Xa, cari matrix a
            // convert ke matrix y
            double[][] y = new double[18][1];
            int ind_y = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    y[ind_y][0] = mtrx[i][j];
                    ind_y++;
                }
            }

            y[16][0] = a;
            y[17][0] = b;

            return y;
        }catch (FileNotFoundException e) {
            double[][] a = new double[0][0];
            System.out.println("File tidak ditemukan.");
            return a;
        }
    }

    public static void mainProses(int methodInput){
        // menggunakan bentuk y = XA
        // mencari A
        // X default
        // y dari input
        // akhirnya didapat model f
        // mendapat nilai f(a, b)
        double[][] matrix = new double[0][0];

        Scanner scan = new Scanner(System.in);

        // set up matrix X
        double[][] X = new double[16][16];
        X = getMatX();

        // init mat y
        double[][] y = new double[16][1];
        int ind_y = 0;

        // init var a, b
        double a, b;

        // input matriks y dan variabel a dan b
        if(methodInput == 1){
            // input dari cli
            String[] fungsi = {"f", "fx", "fy", "fxy"};
            String[] point = {"(0,0)", "(1,0)", "(0,1)", "(1,1)"};
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    // contoh : Masukkan titik f(0,0) : 
                    System.out.print("Masukkan titik " + fungsi[i] + point[j] + ": ");
                    y[ind_y][0] = scan.nextDouble();
                    ind_y++;
                }
            }
            // input a
            System.out.print("Masukkan titik a : ");
            a = scan.nextDouble();

            // input b
            System.out.print("Masukkan titik b : ");
            b = scan.nextDouble();
        }else{
            // input dari file
            System.out.println("Masukkan nama file: ");
            String FileName = scan.nextLine();

            // baris 0-15 input f
            // 17 a, 18 b
            double[][] mtrx = new double[18][1];
            mtrx = readFile(FileName);

            if(matrix.length == 0){
                return;
            }

            // mendapatkan matriks y
            for(int i = 0; i < 16; i++){
                y[i][0] = mtrx[i][0];
            }

            // mendapakan nilai a dan b
            a = mtrx[16][0];
            b = mtrx[17][0];
        }
        
        // sudah ada matrix X dan y
        // process start

        // mencari nilai a dengan matriks A
        double[][] A = new double[16][1];
        A = getSolA(y, X);

        // mencari hasil dari f(a,b)
        double result = getSolution(A, a, b);

        // output
        System.out.print("Hasil dari ... adalah ");
        System.out.print(result);
    }
}
