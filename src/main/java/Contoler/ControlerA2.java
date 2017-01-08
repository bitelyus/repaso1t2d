/**
 * Actividades de repaso para el examen final del 1t
 *   2º DAM - Acceso a Datos y Ficheros
 *   (C) 17-12-2016
 * =================================================
 * Actividad 2. Se pide realizar un programa en Java que pida por teclado la
 * ruta de un directorio. Tras haberlo pedido el programa recorrerá recursivamente
 * directorios y ficheros y, contará y mostrará por pantalla el nombre de todos
 * los ficheros .java dentro del directorio y subdirectorios de la ruta establecida.
 */
package Contoler;

import View.Ch;
import View.InterfazA2;
import java.io.File;

/**
 *
 * @author bitelyus @ www.miguelkiko.com
 */
public class ControlerA2 {
    
    // ZONA DE ATRIBUTOS

    public static int contador;   // Nuestro contador de archivos .java
    public static String salida;  // La cadena de salida de datos para el final
    
    
    // ZONA DE MÉTODOS
    
    /**
     * Método para que comienze la función de la Actividad 2
     */
    public static void comenzar() {

        // DECLARACION DE VARIABLES E INICIALIACIÓN
        String ruta = "";   // La ruta del directorio
        File dir = null;// El fichero en si
        contador=0;
        salida="";
        
        // ENTRADA
        do {
            ruta = InterfazA2.pedirRuta();
            dir = new File(ruta);
            if (dir.exists()) {
                Ch.lcd("i> Leyendo directorio " + ruta);
            } else {
                Ch.lcd("!> PSS! No existe el directorio >:(");
            }
        } while (!dir.exists());

        // PROCESO 
        try {
            listar(dir);
        } catch (Exception ex) {
            Ch.lcd(ex.getMessage());
        }
        
        // SALIDA
        salida+="\ni> EL NÚMERO TOTAL DE ARHCIVOS .java ES DE: " + contador + "\n";
        Ch.lcd(salida);
    }

    /**
     * Método recursivo que devuelve y cuenta los .java de un directorio
     * @param dir El directorio de que hay que leer recursivamente
     */
    public static void listar(File dir) {
        File[] ficheros = dir.listFiles();
        for (File fichero : ficheros ) {
            if (fichero.getName().endsWith("java") && !fichero.isDirectory()) {
                salida+="f> " + fichero.getName()+"\n";
                contador++;
            }
            if (fichero.isDirectory()) {
                listar(fichero);
            } 
        }
    }
    
    
    
}
