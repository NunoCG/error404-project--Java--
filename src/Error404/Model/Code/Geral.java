package Error404.Model.Code;

import java.util.Scanner;

/**
 *
 */
public class Geral {
    static Scanner sc = new Scanner(System.in);

    /**
     *
     * @param texto
     * @return
     */
    public static int lerInt(String texto) {
        try {
            System.out.println(texto);
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Insira um valor válido");
            return lerInt(texto);
        }
    }

    /**
     *
     * @param texto
     * @return
     */
    public static double lerDouble(String texto) {
        try {
            System.out.println(texto);
            return Double.parseDouble(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Insira um valor válido");
            return lerDouble(texto);
        }
    }

    /**
     *
     * @param texto
     * @return
     */
    public static String readString(String texto) {
        System.out.println(texto);
        String text = sc.nextLine().trim();
        if (!text.equals("")) {
            return text;
        }
        return readString(texto);
    }

    /**
     *
     * @param texto
     * @param numTrue
     * @param numFalse
     * @return
     */
    public static boolean lerIntTrueFalse(String texto, int numTrue, int numFalse) {
        int num = lerInt(texto);
        if (numTrue == num) return true;
        if (numFalse == num) return false;
        System.out.println("Valor inválido!");
        return lerIntTrueFalse(texto, numTrue, numFalse);
    }

    /**
     *
     * @param texto
     * @return
     */
    public static boolean lerBoolean(String texto) {
        System.out.println(texto);
        return sc.nextBoolean();
    }
}
