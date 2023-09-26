package utilities;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class ReadFile {
    static double[][] datatoMatrix(String[] tempData, int rowMat, int colMat){
        double[][] ans;
        ans = new double[rowMat][colMat];
        int ind_col; // index kolom matrix
        for(int i = 0; i < rowMat; i++){
            // tiap baris
            ind_col = 0;
            String tempString = tempData[i];
            String tempIsi = "";
            for(int j = 0; j < tempData[i].length(); j++){
                if(tempString.charAt(j) == ' '){
                    ans[i][ind_col] = Double.valueOf(tempIsi);
                    tempIsi = "";
                    ind_col +=1;
                }else{
                    tempIsi += tempString.charAt(j);
                }
            }
            // karakter terakhir
            ans[i][ind_col] = Double.valueOf(tempIsi);
        }
        return ans;
    }

    public static void readFileSPL() {
        try{
            String[] tempData;
            tempData = new String[1000];

            Scanner myPath = new Scanner(System.in);
            System.out.println("Input your file path : ");
            String path = myPath.nextLine();
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            int rowMat = 0, colMat = 0;

            // baca file
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                tempData[rowMat] = data;
                rowMat++;
            }
            
            //hitung jumlah kolom
            for(int i = 0; i < tempData[0].length(); i++){
                if(tempData[0].charAt(i) == ' '){
                    colMat++;
                }
            }
            colMat++;

            // convert ke matrix
            double mtrx[][] = datatoMatrix(tempData, rowMat, colMat); 

            for(int i = 0; i < rowMat; i++){
                for(int j = 0; j < colMat; j++){
                    System.out.print(mtrx[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }

            myPath.close();
            myReader.close();
        } catch(FileNotFoundException e){
            System.out.println("Your file is wrong.");
            e.printStackTrace();
        }
    }

    public static void readFileInterpolasi(){
        try{
            String[] tempData;
            tempData = new String[1000];

            Scanner myPath = new Scanner(System.in);
            System.out.println("Input your file path : ");
            String path = myPath.nextLine();
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            int rowMat = 0, colMat = 2;

            // baca file
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                tempData[rowMat] = data;
                rowMat++;
            }

            // convert ke matrix
            // mtrx berbentuk rowMat x 2
            double mtrx[][] = datatoMatrix(tempData, rowMat, colMat); 

            // for(int i = 0; i < rowMat; i++){
            //     for(int j = 0; j < colMat; j++){
            //         System.out.print(mtrx[i][j]);
            //         System.out.print(" ");
            //     }
            //     System.out.println();
            // }

            // menggunakan bentuk Ax = B, cari matrix x

            // convert ke matrix A
            double[][] A;
            A = new double[rowMat][rowMat];
            for(int i = 0; i < rowMat; i++){
                for(int j = 0; j < rowMat; j++){
                    A[i][j] = Math.pow(mtrx[i][0], j);
                }
            }

            for(int i = 0; i < rowMat; i++){
                for(int j = 0; j < rowMat; j++){
                    System.out.print(A[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }

            // convert ke matrix B
            double[][] B;
            B = new double[rowMat][1];
            for(int i = 0; i < rowMat; i++){
                B[i][0] = mtrx[i][1];
            }
            for(int i = 0; i < rowMat; i++){
                for(int j = 0; j < 1; j++){
                    System.out.print(B[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }

            myPath.close();
            myReader.close();
        } catch(FileNotFoundException e){
            System.out.println("Your file is wrong.");
            e.printStackTrace();
        }
    }
}
