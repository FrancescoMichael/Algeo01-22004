import utilities.Matriks;

public class Tess {
    public static void main(String[] args) {
        System.out.println("asd");
        Matriks m = new Matriks(4,5);

        m.displayMatrix(4);
        m.readFromCLI(4);
        m.displayMatrix(4);

        System.out.println(m.getDeterminantOBE(4, 4));

        m.displayMatrix(4);
    }
}
