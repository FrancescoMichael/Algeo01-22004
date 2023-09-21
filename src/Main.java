import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner objScan = new Scanner(System.in);

        boolean process = true;
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
            if (choice == 7) {
                System.out.println("makasih cok");
                process = false;
            }
        }
    }
}