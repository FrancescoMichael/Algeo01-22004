import java.util.Scanner;

public class printSPL {
    public static boolean solCheck(double [][]mat, int n, int m){
        // true jika tidak ada solusi, false jika ada solusi
        boolean ans = true;

        // cek baris terakhir
        int i = 0;
        while(i < m-1 && ans){
            if(mat[n-1][i] != 0){
                ans = false;
            }
            i++;
        }

        return ans;
    }

    public static void printSolusi(double [][]mat, int n, int m){
        // matriks mat berukuran n x m;
        // 3 kasus :
        // 1. No solution : baris terakhir 0 semua kecuali mat[n][m]
        // 2. Single solution : baris terakhir tersisa 1 utama
        // 3. Parametric solution : baris terakhir 0 semua

        double [] sol = new double[m-1]; // menyimpan solusi
        boolean [] valSol = new boolean[m-1]; // apakah valuenya ada atau belum
        String [] strSol = new String[m-1]; // solusi dalam bentuk string
        String [] paramLetter = {"p", "q", "r", "s", "t", "u", "v", "w"};

        // insiasi tiap array
        for(int i = 0; i < m-1; i++){
                sol[i] = 0;
            }
        for(int i = 0; i < m-1; i++){
            valSol[i] = false;
        }
        for(int i = 0; i < m-1; i++){
            strSol[i] = " ";
        }

        // Kasus 1
        // No solution
        if (solCheck(mat, n, m) && mat[n-1][m-1] != 0){
            // Kasus 1
            System.out.println("Tidak ada solusi.");
        }else if(solCheck(mat, n, m) && mat[n-1][m-1] == 0){
            // Kasus 3
            System.out.println("Solusi yang diberikan berada dalam bentuk parametrik.");

            int indParam = 0; //  menggunakan huruf p terlebih dahulu

            for(int i = n-2; i >= 0; i--){
                // skip baris terakhir karena sudah pasti bernilai 0 semua
                int j = 0, oneLeading = -1;
                while(j < m-1){
                    if(mat[i][j] == 1 && oneLeading == -1){
                        // mencari indeks 1 utama tiap baris
                        oneLeading = j;
                    }

                    if(mat[i][j] != 0){
                        if(valSol[j]){
                            // cek apakah solusinya angka atau tidak
                            mat[i][m-1] -= (mat[i][j]*sol[j]);
                        }else{
                            // berarti parametrik
                            // cek apakah solusi parametrik sudah ada atau belum
                            if(strSol[j] == " "){
                                // belum ada solusi parametrik sama sekali
                                strSol[j] = paramLetter[indParam];
                                indParam++;
                            }else{
                                // sudah ada solusi

                            }
                        }
                    }
                    j++;
                }

                // sol[oneLeading] = mat[i][m-1];
                // valSol[oneLeading] = true;
                strSol[oneLeading] = String.valueOf(mat[i][m-1]);;

                j = oneLeading + 1;
                while(j < m-1){
                    if(mat[i][j] != 0){
                        if(strSol[j] == "0.0"){
                            mat[i][j] = 0;
                        }
                        if(mat[i][j] > 0){
                            strSol[oneLeading] += "-";
                            strSol[oneLeading] += "(";
                            if(mat[i][j] != 1){ 
                                strSol[oneLeading] += String.valueOf(mat[i][j]);
                            }        
                            strSol[oneLeading] += strSol[j];
                            strSol[oneLeading] += ")";
                        }else if(mat[i][j] < 0){
                            strSol[oneLeading] += "+";
                            strSol[oneLeading] += "(";
                            mat[i][j]*=(-1);
                            if(mat[i][j] != 1){
                                strSol[oneLeading] += String.valueOf(mat[i][j]);
                            }
                            strSol[oneLeading] += strSol[j];
                            strSol[oneLeading] += ")";
                        }
                    }
                    j++;
                }
            }
            for(int i = 0; i < m-1; i++){
                System.out.print(strSol[i]);
                System.out.print(" ");
            }
        }else{ 
            // Kasus 2
            for(int i = n-1; i >= 0; i--){
                int j = 0, oneLeading = -1;
                // mencari 1 utama 
                // mencari konstanta mulai 1 utama hingga kolom ke m-1
                while(j < m-1){
                    if(mat[i][j] == 1 && oneLeading == -1){
                        oneLeading = j;
                    }
                    if(mat[i][j] != 0){
                        mat[i][m-1] -= (mat[i][j]*sol[j]);
                    }
                    j++;
                }
                // System.out.println(oneLeading);
                sol[oneLeading] = mat[i][m-1];
            }
            for(int i = 0; i < m-1; i++){
                System.out.print(sol[i]);
                System.out.print(" ");
            }
        }
    }

    public static void main(String[] args){
        Scanner obj = new Scanner(System.in);
        int n = obj.nextInt();
        int m = obj.nextInt();
        double [][] mat = new double [n][m]; 

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                mat[i][j] = obj.nextDouble();
            }
        }

        printSolusi(mat, n, m);

        obj.close();
    }
}
