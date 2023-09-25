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

    public static void readFile() {
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
}
