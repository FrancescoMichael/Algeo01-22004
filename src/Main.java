import java.util.Scanner;

import utilities.*;

public class Main {
    public static void methodInput() {
        System.out.println("Metode input\r\n" + //
                "1. Melalui cli\r\n" + //
                "2. Melalui file TXT\r\n" //
        );
    }

    public static void endOfProcess() {

    }

    public static void main(String[] args) {

        boolean process = true;
        Scanner objScan = new Scanner(System.in);
        while (process) {
            System.out.println("MENU\r\n" + //
                    "1. Sistem Persamaaan Linier\r\n" + //
                    "2. Determinan\r\n" + //
                    "3. Matriks balikan\r\n" + //
                    "4. Interpolasi Polinom\r\n" + //
                    "5. Interpolasi Bicubic Spline\r\n" + //
                    "6. Regresi linier berganda\r\n" + //
                    "7. Keluar\r\n" + //
                    "silahkan masukkan pilihan anda" +
                    "");
            int choice = objScan.nextInt();
            if (choice == 1) {
                System.out.println("MENU\r\n" + //
                        "1. Metode eliminasi Gauss\r\n" + //
                        "2. Metode eliminasi Gauss-Jordan\r\n" + //
                        "3. Metode matriks balikan\r\n" + //
                        "4. Kaidah Cramer\r\n");
                int subChoice = objScan.nextInt();
                if (subChoice == 1) {
                    methodInput();
                    int inputChoice = objScan.nextInt();
                    if (inputChoice == 1) {
                        System.out.println("Silahkan masukkan ukuran matriks anda ");
                        int n = objScan.nextInt();
                        System.out.println(n);
                        Matriks m = new Matriks(n, n + 1);
                        m.readSPLFromCLI(n);
                        m.getRowEchelon(n);
                        // sementara display matrix hasilnya dl aja
                        m.displayAugmentedMatrix(n);

                    } else if (inputChoice == 2) {

                    }

                } else if (subChoice == 2) {
                    methodInput();
                    int inputChoice = objScan.nextInt();
                    if (inputChoice == 1) {
                        System.out.println("Silahkan masukkan ukuran matriks anda ");
                        int n = objScan.nextInt();
                        System.out.println(n);
                        Matriks m = new Matriks(n, n + 1);
                        m.readSPLFromCLI(n);
                        m.getReductionRowEchelon(n, n + 1);
                        // sementara display matrix h a silnya dl aja
                        m.displayAugmentedMatrix(n);

                    } else if (inputChoice == 2) {

                    }

                } else if (subChoice == 3) {
                    methodInput();
                    int inputChoice = objScan.nextInt();
                    if (inputChoice == 1) {
                        System.out.println("Silahkan masukkan ukuran matriks anda ");
                        int n = objScan.nextInt();
                        System.out.println(n);
                        Matriks m = new Matriks(n, n + 1);
                        m.readSPLFromCLI(n);
                        double[][] value = new double[n][1];
                        for (int i = 0; i < n; i++) {
                            value[i][0] = m.getMatriks()[i][n];
                        }
                        m.inverseMatrixOBE(n);
                        double[][] result = m.multiplyMatrix(m.getMatriks(), value);
                        // sementara display matrix hasilnya dl aja
                        m.displayDataMatrix(result);

                    } else if (inputChoice == 2) {

                    }

                } else if (subChoice == 4) {
                    methodInput();
                    int inputChoice = objScan.nextInt();
                    if (inputChoice == 1) {
                        // System.out.println("Silahkan masukkan ukuran matriks anda ");
                        // int n = objScan.nextInt();
                        // System.out.println(n);
                        // Matriks m = new Matriks(n, n + 1);
                        // m.readSPLFromCLI(n);
                        // double[][] result = m.cramerMethod(n);
                        // // sementara display matrix hasilnya dl aja
                        // m.displayDataMatrix(result);
                    } else if (inputChoice == 2) {

                    }

                }
            } else if (choice == 2) {
                System.out.println("MENU\r\n" + //
                        "1. Metode reduksi baris\r\n" + //
                        "2. Metode ekspansi kofaktor\r\n" + //
                        "");
                int subChoice = objScan.nextInt();
                if (subChoice == 1) {
                    methodInput();
                    int inputChoice = objScan.nextInt();
                    if (inputChoice == 1) {

                    } else if (inputChoice == 2) {

                    }

                } else if (subChoice == 2) {
                    methodInput();
                    int inputChoice = objScan.nextInt();
                    if (inputChoice == 1) {

                    } else if (inputChoice == 2) {

                    }

                }
            } else if (choice == 4) {
                Scanner myPath = new Scanner(System.in);
                System.out.println("Input your file path : ");
                String path = myPath.nextLine();

                double[][] mtrx = ReadFile.readFileInterpolasi(path);
                Matriks m = new Matriks(mtrx.length - 1, mtrx.length - 1);
                double[][] a = new double[mtrx.length - 1][mtrx.length - 1];
                double[][] b = new double[mtrx.length - 1][1];
                for (int i = 0; i < mtrx.length - 1; i++) {
                    for (int j = 0; j < mtrx[0].length; j++) {
                        if (j == mtrx.length - 1) {
                            b[i][0] = mtrx[i][j];
                        } else {
                            a[i][j] = mtrx[i][j];
                        }
                    }
                }
                m.readFromVariable(a);
                m.inverseMatrixOBE(mtrx.length - 1);

                double[][] res = m.multiplyMatrix(m.getMatriks(), b);
                // System.out.println("masukkan data testnya");
                double test = mtrx[mtrx.length - 1][0];
                double result = 0;
                for (int i = 0; i < mtrx.length - 1; i++) {
                    if (i == 0) {
                        result += res[i][0];
                        System.out.println(res[i][0]);
                    } else {
                        System.out.println(" + " + res[i][0]);
                        result += (res[i][0] * Math.pow(test, i));
                    }
                }
                System.out.println(" = y");
                System.out.println("hasilnya " + result);

            } else if (choice == 5) {
                methodInput();
                int inputChoice = objScan.nextInt();
                if (inputChoice == 1) {

                } else if (inputChoice == 2) {
                    Scanner myPath = new Scanner(System.in);
                    System.out.println("Input your file path : ");
                    String path = myPath.nextLine();
                    double[][] om = ReadFile.readFileBicubicInterpolasi(path);
                    // for(int i = 0; i < 17; i++){
                    //     for(int j = 0; j < 17; j++){
                    //         System.out.print(om[i][j] + " ");
                    //     }
                    //     System.out.println();
                    // }
                    
                    double[][] kodok = ReadFile.readFileBicubicInterpolasi(path);
                    double[][] hasile = new double[16][16];
                    for (int i = 0; i < 16; i++) {
                        for (int j = 0; j < 16; j++) {
                            hasile[i][j] = om[i][j];
                            // System.out.print(hasile[i][j]);
                            // System.out.print(" ");
                        }
                        // System.out.println();
                    }
                    
                    // iki y
                    double[][] y = new double[16][1];
                    for (int i= 0; i < 16; i++) {
                        y[i][0] = om[i][16];
                    }
                    // iki a
                    double a = om[16][0];
                    // iki b
                    double b = om[16][1];

                    hasile = Bicubic.BicubicMase(hasile,y);
                    // // for (int i = 0; i<16; i++) {
                        
                    // //         System.out.println(hasile[i][0] + " ");
                        
                    // // }

                    System.out.println("hasilnya " + Bicubic.sangarOgXYneKetemu(hasile, a, b));
                }
            } else if (choice == 6) {
                methodInput();
                int inputChoice = objScan.nextInt();
                if (inputChoice == 1) {
                    // int n = objScan.nextInt();
                    // int c = objScan.nextInt();
                    // Matriks m = new Matriks(n, n + 1);
                    // m.readFromCli(n, c);
                    // RegresiLinear.multiRegresi(m.getMatriks(), n);
                } else if (inputChoice == 2) {
                    Scanner myPath = new Scanner(System.in);
                    System.out.println("Input your file path : ");
                    String path = myPath.nextLine();
                    double[][] input = ReadFile.readFileRegresi(path);
                    RegresiLinear.multiRegresi(input, input.length);
                }
            } else if (choice == 7) {
                System.out.println("makasih cok");
                process = false;
            }
        }
        objScan.close();
    }
}