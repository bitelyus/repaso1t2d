/**
 * Actividades de repaso para el examen final del 1t
 *   2º DAM - Acceso a Datos y Ficheros
 *   (C) 17-12-2016
 */
package View;

import Contoler.MiFiltro;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bitelyus @ www.miguelkiko.com
 */
public class InterfazA4 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Muestra el menu prinpal del programa
     */
    public static void menuPrincipal() {
        String cadena = "";
        cadena += "\nMENU PRINCIPAL | LIBRO RECETAS\n";
        cadena += "================================\n\n";
        cadena += "1. CREAR NUEVA RECETA\n";
        cadena += "2. LEER UNA RECETA\n";
        cadena += "3. CREAR LIBRO DE COCINA\n\n";
        cadena += "0. VOLVER AL MENU PRINCIPAL\n";
        System.out.println(cadena);
    }

    public static String pedirNombre() {
        String aux = "";
        do {
            System.out.print("?> NOMBRE DEL LIBRO: ");
            try {
                aux = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(InterfazA3.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while ("".equals(aux));
        return aux;
    }

    public static int pedirTiempo() {
        String aux = "";
        int tiempo = 0;
        boolean salir = false;
        char letra;
        do {
            boolean esnumero = true;
            System.out.print("?> AÑO PUBLICACION.: ");
            try {
                aux = br.readLine();
                if (aux != "") {
                    for (int i = 0; i < aux.length(); i++) {
                        letra = aux.charAt(i);
                        //Ch.lcd(""+Character.isDigit(letra));
                        if (!Character.isDigit(letra)) {
                            esnumero = false;
                        }
                    }
                }
                if (esnumero) {
                    tiempo = Integer.parseInt(aux);
                    salir=true;
                }
            } catch (IOException ex) {
                Logger.getLogger(InterfazA3.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (!salir);
        return tiempo;
    }

    
    public static int pedirOpcionXMLorJAXB() {
        String aux = "";
        int opcion = 0;
        boolean salir = false;
        System.out.println("\nFORMATO DESEADO");
        System.out.println("===============");
        System.out.println("1. FORMATO XML - Libreria Jaxb");
        System.out.println("2. FORMATO JSON - Libreria Gson\n");

        do {
            System.out.print("?> Qué formato quieres?: ");
            try {
                aux = br.readLine();
                if (!aux.isEmpty()) {
                    char opcionchar;
                    opcionchar = aux.charAt(0);
                    if (Character.isDigit(opcionchar)) {
                        opcion = Integer.parseInt(aux);
                        if (opcion > 0 & opcion <= 2) {
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

        return opcion;
    }
    
    public static String pedirRuta() {
        String aux = "";
        do {
            System.out.print("?> DÁME LA RUTA DE UN ARCHIVO..: ");
            try {
                aux = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(InterfazA3.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (aux.equals(""));
        return aux;    
    }
    
    public static String pedirRutaLeer() {
        String aux = "";
        String ruta = "";
        boolean salir = false;
        do {
            System.out.print("\n?> DÁME EL NOMBRE DEL ARCHIVO XML o JSON: ");
            try {
                aux = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(InterfazA3.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            if (!aux.isEmpty()) {
                System.out.println("i> archivo: " + aux);
                ruta = aux;
                //String desktop = System.getProperty("user.home") + "/Desktop/";
                File myFile = new File("recetas/" + ruta);
                if (myFile.isFile()) {
                    //System.out.println(myFile.getAbsolutePath());
                    salir = true;
                } else {
                    System.out.println("!> No se encontro el archivo...");
                }
            }
            //}

            //}
        } while (!salir);
        return ruta;
    }
    
    public static void listarArchivos() {
        int i = 0;
        // 1. VAMOS A LISTAR LOS ARCHIVOS CON EXTENSIÓN XML Y JSON DISPONIBLES
        File miDir = new File(System.getProperty("user.dir") + "/recetas");           // EL DIRECTORIO DE TRABAJO MAS CARPETA DE RECETAS
        Ch.lcd("i> Directorio de trabajo: " + miDir.getAbsolutePath());
        File[] archivos = miDir.listFiles(new MiFiltro("xml")); // CON FILTRO PARA XML
        Ch.lcd("\nLISTADO DE ARCHIVOS XML");
        Ch.lcd("-----------------------");
        for (i = 0; i < archivos.length; i++) {
            System.out.println((i + 1) + ". " + archivos[i].getName());
        }

        archivos = miDir.listFiles(new MiFiltro("json")); // CON FILTRO PARA XML
        System.out.println("\nLISTADO DE ARCHIVOS JSON");
        System.out.println("------------------------");
        for (i = 0; i < archivos.length; i++) {
            System.out.println((i + 1) + ". " + archivos[i].getName());
        }
    }
}
