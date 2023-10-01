package utilities;
import utilities.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// counting bicubic of matrix using readfile.java
public class Bicubic {
    // y = xa
    // y sudah ada
    // x sudah ada
    // a = (x^-1)*y
    
    // JARENE BOSE RA KANGGO
    // inverse matriks X dulu
    public double[][] InverseX(double[][] mtrx) {
        Matriks xInv = new Matriks(16, 16);
        Matriks m = new Matriks(16,16);
        m.readFromVariable(mtrx);
        double[][]baru = m.getMatriks();
        m.inverseMatrixOBE(16);

        return baru;
    }

    public double[][] GetY(double[][] y) {
        Matriks m = new Matriks(16,16);
        double[][]anyar = m.getMatriks();

        return anyar;
    }

    public static double[][] BicubicMase(double[][] x, double[][] y) {
        // mengalikan X  yang sudah di inverse dengan Y
        Matriks m = new Matriks(16,16);
        // m.readFromVariable(inverseX(x));
        m.readFromVariable(x);
        m.inverseMatrixOBE(16);
        m.displayMatrix(16);

        // m.readFromVariable(y);
        
        double[][] jawa = m.multiplyMatrix(m.getMatriks(), y);
        return jawa;
    }

    public static double sangarOgXYneKetemu(double[][] wongpusat, double a, double b) {
        double dadine = 0;
        Matriks m = new Matriks(16,16);
        int xxx = 0;

        double suku1 = 0, suku2 = 0;

                for(int j = 0; j < 4; j++){
                    for(int i = 0; i < 4; i++){
                        // if(a == 0 && (i+j) == 0){
                        //     suku = 1;
                        // }else{
                        //     suku = Math.pow(a, i+j);
                        // }
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
                    
                        dadine+=(suku*wongpusat[xxx][0]);
                        xxx++;
                    }
                }

        return dadine;

    }    

    // asumsikan input valid
    // public static void Bicubic(double [][] y, double [][] X, int n, int o){ 
    //     Matriks m = new Matriks(n,o);
    // }

    // }
}
