/**
 * Actividades de repaso para el examen final del 1t
 *   2º DAM - Acceso a Datos y Ficheros
 *   (C) 17-12-2016
 * ==================================================
 *
 * Actividad 1. Se pide realizar un programa en Java que pida por teclado la ruta
 * de un fichero de texto. El programa leerá el fichero de texto entero y contará
 * el número de caracteres distintos a un espacio (“ ”).
 *
 */
package Contoler;

import View.Ch;
import View.InterfazA1;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author bitelyus @ www.miguelkiko.com
 */
public class ControlerA1 {
    /**
     * Función para realizar las acciones de la Actividad 1
     * 1. Pide la ruta
     * 2. Comprueba que existe
     * 3. Si existe, la lee linea a linea
     * 4. Trocea la linea por espacios (" ")
     * 5. Suma el número de caracteres de cada palabra de la linea
     * 6. Muestra los resultados
     */
    public static void comenzar() {
        
        // DECLARACION DE VARIABLES E INICIALIACIÓN
        String ruta = "";   // La ruta del fichero
        File archivo = null;// El fichero en si
        int contador=0;     // Contador de caracteres de palabras

        // ENTRADA
        
        do {
            ruta = InterfazA1.pedirRuta();
            archivo = new File(ruta);
            if (archivo.exists()) {
                Ch.lcd("i> Leyendo archivo " + ruta);
            } else {
                Ch.lcd("!> PSS! No existes el fichero de texto >:(");
            }
        } while (!archivo.exists());
        
        // PROCESO
        
        try {
            
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String cadena = "";
            String[] cadenasplit = null;

            while ((cadena=br.readLine())!=null) {
            	cadenasplit = cadena.split(" ");
                for (String palabra : cadenasplit) {
                    contador+=palabra.length();
                }
            }
            JOptionPane.showMessageDialog(null,"LOS DATOS HAN SIDO CARGADOS DEL FICHERO '"+archivo+"'","INFO", JOptionPane.INFORMATION_MESSAGE);
            br.close();
	} catch (IOException | NumberFormatException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "ERROR LEYENDO \'"+archivo+"\' -> " + ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
	}
        
        // SALIDA
        
        Ch.lcd("\ni> EL SUMATORIO DE LOS CARACTERES DISTINTOS A ' ' (Espacio) DEL FICHERO SON: " + contador);
        Ch.pausa();
    }
}
