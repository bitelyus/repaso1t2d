package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase de Ayuda para mostrar trabjar con salidas/entradas por pantalla
 *
 * @author bitelyus @ www.miguelkiko.com
 */
public class Ch {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Muestra el menu prinpal del programa
     */
    public static void menuPrincipal() {
        String cadena = "";
        cadena += "\nMENU PRINCIPAL | PRE-EXAMEN 1T - 2ºDAM\n";
        cadena += "======================================\n\n";
        cadena += "1. ACTIVIDAD 1: Contar caracteres de un fichero .txt\n";
        cadena += "2. ACTIVIDAD 2: Contar ficheros .java de directorio recursivamente\n";
        cadena += "3. ACTIVIDAD 3: Crear o Leer Receta [XML - JSON]\n";
        cadena += "4. ACTIVIDAD 4: Crear Libro de Recetas [XML - JSON]\n\n";
        cadena += "0. SALIR :)\n";
        System.out.println(cadena);
    }

    /**
     * Método para pedir una opción de un menu por pantalla
     *
     * @param tope El límite tope de opciónes del menu
     * @return El número seleccionado
     */
    public static int pedirOpcion(int tope) {
        int opcion = 0;

        boolean salir = false;
        String aux = "";
        do {
            System.out.print("?> DÍME QUE QUIERES HACER, AMO: ");
            try {
                aux = br.readLine();
                if (!aux.isEmpty()) {
                    char opcionchar;
                    opcionchar = aux.charAt(0);
                    if (Character.isDigit(opcionchar)) {
                        opcion = Integer.parseInt(aux);
                        if (opcion >= 0 & opcion <= tope) {
                            salir = true;
                        } else {
                            System.out.println("!> Perdona?...");
                        }
                    } else {
                        System.out.println("!> Ajammm... #:@!");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!salir);
        //scaner.close();
        return opcion;
    }

    public static boolean pedirOpcionSN() {
        String aux = "";
        boolean salir = false;
        do {
            try {
                System.out.print("?> Añadir más? [s/n]: ");
                aux = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Ch.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (aux!="") {
                if (aux.equalsIgnoreCase("s") || aux.equalsIgnoreCase("n")) {
                    salir = true;
                }
            }
        } while (!salir);
        if (aux.equalsIgnoreCase("s")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Método para comprobar si un String tiene números o no
     *
     * @param cadena El string a comprobar
     * @return True si no tiene numero y False si contiene algun número
     */
    public static boolean esString(String cadena) {
        boolean escadena;
        escadena = true;
        boolean salir = false;
        char opcionchar;
        do {
            if (!cadena.isEmpty()) {
                for (int i = 0; i < cadena.length(); i++) {
                    opcionchar = cadena.charAt(i);
                    if (Character.isDigit(opcionchar)) {
                        escadena = false;
                        //System.out.println("ES DÍGIDO!");
                    }
                }
                salir = true;
            }
        } while (!salir);
        return escadena;
    }

    /**
     * Método Auxiliar para mostrar un mensaje por pantalla
     *
     * @param cadena El mensaje a mandar
     */
    public static void lcd(String cadena) {
        System.out.println(cadena);
    }

    /**
     * Método para pausar el fluzo del programa hasta pulsar intro
     */
    public static void pausa() {
        System.out.print("\nPulse Intro para continuar...");
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(Ch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
