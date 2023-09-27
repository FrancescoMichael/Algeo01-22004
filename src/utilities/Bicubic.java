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

    public double[][] Bicubic(double[][] x, double[][] y) {
        // mengalikan X  yang sudah di inverse dengan Y
        Matriks m = new Matriks(16,16);
        // m.readFromVariable(inverseX(x));
        m.readFromVariable(x);
        m.inverseMatrixOBE(16);

        // m.readFromVariable(y);
        
        double[][] jawa = m.multiplyMatrix(m.getMatriks(), y);
        return jawa;
    }
    

    // asumsikan input valid
    // public static void Bicubic(double [][] y, double [][] X, int n, int o){ 
    //     Matriks m = new Matriks(n,o);
    // }

    // }
}
