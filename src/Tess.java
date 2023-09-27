import utilities.Matriks;

public class Tess {
    public static void main(String[] args) {
        System.out.println("asd");
        Matriks m = new Matriks(4,5);

        m.displayMatrix(4);
        m.readFromCLI(4);
        m.displayMatrix(4);

        // for (int i = 0; i < 4; i++) {
        //     for (int j = 0; j < 5; j++) {
        //         System.out.print(m.getMatriks()[i][j] + " ");
        //         System.out.println(m.Bicubic(x[i][j], y[i][j]));
        //     }
        //     System.out.println();
        // }
        // System.out.println(m.Bicubic(x[16][16], y[16][16]));

        m.displayMatrix(4);
    }
}
