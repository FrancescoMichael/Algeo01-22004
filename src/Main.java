import java.util.Scanner;

import utilities.Matriks;

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
                        System.out.println("Silahkan masukkan ukuran matriks anda ");
                        int n = objScan.nextInt();
                        System.out.println(n);
                        Matriks m = new Matriks(n, n + 1);
                        m.readSPLFromCLI(n);
                        double[][] result = m.cramerMethod(n);
                        // sementara display matrix hasilnya dl aja
                        m.displayDataMatrix(result);
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
            } else if (choice == 3) {
                methodInput();
                int inputChoice = objScan.nextInt();
                if (inputChoice == 1) {

                } else if (inputChoice == 2) {

                }
            } else if (choice == 7) {
                System.out.println("makasih cok");
                process = false;
            }
        }
        objScan.close();
    }
}