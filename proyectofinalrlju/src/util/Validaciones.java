package util;

public class Validaciones {
    public static boolean esKilometrajeValido(int km) {
        return km >= 0;
    }

    public static boolean esImporteValido(double importe) {
        return importe > 0;
    }
}