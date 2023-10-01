import java.util.Scanner;

public class Main {
    public static void menu() {
        System.out.println("MENU\r\n" +
                "1. Sistem Persamaaan Linier\r\n" +
                "2. Determinan\r\n" +
                "3. Matriks balikan\r\n" +
                "4. Interpolasi Polinom\r\n" +
                "5. Interpolasi Bicubic Spline\r\n" +
                "6. Regresi linier berganda\r\n" +
                "0. Keluar\r\n" +
                "silahkan masukkan pilihan anda" +
                "");
    }

    public static void subMenu() {
        System.out.println("MENU\r\n" + //
                "1. Metode eliminasi Gauss\r\n" + //
                "2. Metode eliminasi Gauss-Jordan\r\n" + //
                "3. Metode matriks balikan\r\n" + //
                "4. Kaidah Cramer\n" +
                "Lainnya untuk kembali ke menu utama");
    }

    public static void subMenu2() {
        System.out.println("MENU\r\n" + //
                "1. Metode eliminasi Gauss\r\n" + //
                "2. Metode matriks balikan\n" +
                "Lainnya untuk kembali ke menu utama");
    }

    public static void inputMethod() {
        System.out.println("Metode input\r\n" + //
                "1. Melalui cli\r\n" + //
                "2. Melalui file TXT\n" +
                "Lainnya untuk kembali ke menu utama");
    }

    public static void main(String[] args) {
        boolean process = true;
        Scanner scan = new Scanner(System.in);
        while (process) {
            menu();
            int choice = scan.nextInt();
            if (choice == 1) {
                subMenu();
                int subChoice = scan.nextInt();
                inputMethod();
                int inputChoice = scan.nextInt();
                utilities.Spl.mainProses(subChoice, inputChoice);
            } else if (choice == 2) {
                subMenu2();
                int subChoice = scan.nextInt();
                inputMethod();
                int inputChoice = scan.nextInt();
                utilities.Determinan.mainProses(subChoice, inputChoice);
            } else if (choice == 3) {
                subMenu2();
                int subChoice = scan.nextInt();
                inputMethod();
                int inputChoice = scan.nextInt();
                utilities.MatriksBalikan.mainProses(subChoice, inputChoice);
                // subMenu2();
                // int subChoice = scan.nextInt();
                // if (subChoice == 1) {
                // inputMethod();
                // int inputChoice = scan.nextInt();
                // if (inputChoice == 1) {

                // }
                // if (inputChoice == 2) {

                // }
                // } else if (subChoice == 2) {
                // inputMethod();
                // int inputChoice = scan.nextInt();
                // if (inputChoice == 1) {

                // }
                // if (inputChoice == 2) {

                // }
                // }
            } else if (choice == 4) {
                inputMethod();
                int inputChoice = scan.nextInt();
                utilities.Interpolasi.mainProses(inputChoice);
            } else if (choice == 5) {
                inputMethod();
                int inputChoice = scan.nextInt();
                if (inputChoice == 1) {

                }
                if (inputChoice == 2) {

                }
            } else if (choice == 6) {
                inputMethod();
                int inputChoice = scan.nextInt();
                utilities.RegresiBerganda.mainProses(inputChoice);
            } else if (choice == 0) {
                process = false;
                System.out.println("Terima kasih sudah menggunakan");
            }

        }
    }
}
