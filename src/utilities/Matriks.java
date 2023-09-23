package utilities;

import java.util.Scanner;

public class Matriks {

    private double[][] mtrx;

    public void readSPLFromCLI(int n) {
        // untuk augmented matrix
        Scanner objScan = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                int temp = objScan.nextInt();
                mtrx[i][j] = temp;
            }
        }
        objScan.close();
    }

    public void displayAugmentedMatrix(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                System.out.print(this.mtrx[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void displayMatrix(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(this.mtrx[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    public void readFromCLI(int n) {
        // untuk matrix
        Scanner objScan = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = objScan.nextInt();
                mtrx[i][j] = temp;
            }
        }
        objScan.close();
    }

    public double determinanEksKof(double[][] m, int n) {
        // untuk matrix n*n cari determinan dengan ekspansi kofaktor
        double res;
        res = 0.0;
        if (n == 2) {
            return (m[0][0] * m[1][1] - m[1][0] * m[0][1]);
        } else {
            for (int ii = 0; ii < n; ii++) {
                int x = 0;
                double temp[][] = new double[n - 1][n - 1];
                for (int i = 0; i < n; i++) {
                    int y = 0;
                    for (int j = 0; j < n; j++) {
                        if (j != ii) {
                            temp[x][y] = m[i][j];
                            y++;
                        }
                    }
                    x++;
                }
                res += (double) (((ii % 2 == 0) ? 1.0 : -1.0) * (m[0][ii]) * determinanEksKof(temp, n - 1));
            }
        }
        return res;
    }

    public void swapRowMatrix(int a, int b, int col) {
        for (int i = 0; i < col; i++) {
            double temp;
            temp = this.mtrx[a][i];
            this.mtrx[a][i] = this.mtrx[b][i];
            this.mtrx[b][i] = temp;
        }
    }

    public boolean isRowZero(int a, int n) {
        for (int i = 0; i < n; i++) {
            if (this.mtrx[a][i] != 0)
                return false;
        }
        return true;
    }

    public boolean isColZero(int a, int n) {
        for (int i = 0; i < n; i++) {
            if (this.mtrx[i][a] != 0)
                return false;
        }
        return true;
    }

    public void substract2Row(int a, int b, double n, int col) {
        // row a - n*row b
        for (int i = 0; i < col; i++) {
            this.mtrx[a][i] -= (n * this.mtrx[b][i]);
        }
    }

    public void add2Row(int a, int b, double n, int col) {
        // row a - n*row b
                for (int i = 0; i < col; i++) {
            this.mtrx[a][i] += (n * this.mtrx[b][i]);
        }
    }

    public void divideRow(int a, double n, int col) {
        // row a - n*row b
        for (int i = 0; i < col; i++) {
            this.mtrx[a][i] /= n;
        }
    }

    public void getRowEchelon(int n) {
        // cek yang kosongngan semua taruh ke bawah
        for (int i = 0; i < n; i++) {
            if (isRowZero(i, n)) {
                for (int x = i + 1; x < n; x++) {
                    swapRowMatrix(i, x, n + 1);
                }
            }
        }
        int col = 0;
        for (int i = 0; i < n; i++) {
            if (!isRowZero(i, n)) {

                if (this.mtrx[i][col] != 0) {
                    // dibagi sek dengan dirinya sendiri biar satu
                    divideRow(i, this.mtrx[i][col], n + 1);
                    for (int j = i+1; j < n; j++) {
                            // untuk ngenolkan yg bukan baris 1
                            if (this.mtrx[j][col] != 0) {
                                substract2Row(j, i, this.mtrx[j][col] / this.mtrx[i][col], n + 1);
                            }   
                    }
                } else {
                    // gimana kalo ada baris dan kolom yg nol
                    if (isColZero(col, n)) {
                        // majuin kolomnya aja, tapi i jangan nambah
                        i--;
                    } else {
                        // kalo kolomnya ndak nol
                        int temp = i;
                        int idx = -1;
                        while (temp < n && idx == -1) {
                            if (this.mtrx[temp][col] != 0) {
                                idx = temp;
                            }
                            temp++;
                        }
                        if (idx == -1) {
                            i--;
                        } else {
                            add2Row(i, idx, 1, n + 1);
                            divideRow(i, this.mtrx[i][col], n + 1);
                            for (int j = i+1; j < n; j++) {
                                    if (this.mtrx[j][col] != 0) {
                                        substract2Row(j, i, this.mtrx[j][col] / this.mtrx[i][col], n + 1);
                                    }
                            }
                        }
                    }
                }
                col++;
            }
        }
    }

    // public void getEselonMatTereduksi(int n) {
    public void getReductionRowEchelon(int n, int o) {
        // cek yang kosongngan semua taruh ke bawah
        for (int i = 0; i < n; i++) {
            if (isRowZero(i, n)) {
                for (int x = i + 1; x < n; x++) {
                    swapRowMatrix(i, x, o);
                }
            }
        }
        int col = 0;
        for (int i = 0; i < n; i++) {
            if (!isRowZero(i, n)) {

                if (this.mtrx[i][col] != 0) {
                    // dibagi sek dengan dirinya sendiri biar satu
                    divideRow(i, this.mtrx[i][col], o);
                    for (int j = 0; j < n; j++) {
                        if (j != i){
                            // untuk ngenolkan yg bukan baris 1
                            if (this.mtrx[j][col] != 0) {
                                substract2Row(j, i, this.mtrx[j][col] / this.mtrx[i][col], o);
                            }
                        }
                    }
                } else {
                    // gimana kalo ada baris dan kolom yg nol
                    if (isColZero(col, n)) {
                        // majuin kolomnya aja, tapi i jangan nambah
                        i--;
                    } else {
                        // kalo kolomnya ndak nol
                        int temp = i;
                        int idx = -1;
                        while (temp < n && idx == -1) {
                            if (this.mtrx[temp][col] != 0) {
                                idx = temp;
                            }
                            temp++;
                        }
                        if (idx == -1) {
                            i--;
                        } else {
                            add2Row(i, idx, 1, o);
                            divideRow(i, this.mtrx[i][col], o);
                            for (int j = 0; j < n; j++) {
                                if (j != i){
                                    if (this.mtrx[j][col] != 0) {
                                        substract2Row(j, i, this.mtrx[j][col] / this.mtrx[i][col], o);
                                    }
                                }
                            }
                        }
                    }
                }
                col++;
            }
        }
    }


    public Matriks(int n) {
        this.mtrx = new double[n][n + 1];
    }

    public double[][] getMatriks() {
        return mtrx;
    }

    public double[][] expandMtrik(double[][] arr, int factor) {
        double[][] hasil = new double[arr.length * factor][arr[0].length * factor];
        for (int i = 0; i < hasil.length; i++) {
            for (int j = 0; j < hasil[i].length; j++) {
                int orgRow = i / ((arr.length - 1) * factor);
                int orgCol = j / ((arr[0].length - 1) * factor);
                hasil[i][j] = arr[orgRow][orgCol];
            }
        }

        return hasil;
    }

    public void printExpandMtrik(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }

            System.out.println();
        }
    }

    public void inverseMatrixOBE(int n) {
        double[][] temp = new double[n][n*2];
        double[][] mIdentitas = new double[n][n];
        // masukkan ke matriks temp;
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++){
                temp[i][j] = this.mtrx[i][j];
                if (i == j){
                    mIdentitas[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][n+j] = mIdentitas[i][j];
            }
        }
        this.mtrx = temp;
        getReductionRowEchelon(n, n*2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.mtrx[i][j] = this.mtrx[i][n+j];
            }
        }
    }
    
    

    public double[][] getInverseMatriks() {
        return null;
    }



}
