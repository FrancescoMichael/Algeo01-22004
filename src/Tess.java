import utilities.Matriks;

public class Tess {
    public static void main(String[] args) {
        System.out.println("asd");
        Matriks m = new Matriks(6);

        m.displayAugmentedMatrix(6);
        m.readSPLFromCLI(6);
        m.displayAugmentedMatrix(6);
        m.getRowEchelon(6);
        System.out.println("asd");

        m.displayAugmentedMatrix(6);
    }
}
