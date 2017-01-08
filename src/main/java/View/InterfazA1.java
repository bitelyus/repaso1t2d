/**
 * Actividades de repaso para el examen final del 1t
 *   2º DAM - Acceso a Datos y Ficheros
 *   (C) 17-12-2016
 */
package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bitelyus @ www.miguelkiko.com
 */
public class InterfazA1 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String pedirRuta() {

        String msg = "";
        String ruta = "";
        boolean salir = false;
        msg = "?> DÁME LA RUTA DE UN FICHERO DE TEXTO (licencia.txt): ";
        System.out.print(msg);
        try {
            ruta = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(InterfazA1.class.getName()).log(Level.SEVERE, null, ex);
            Ch.lcd(ex.getMessage() + "" + ex.getStackTrace().toString());
        }

        return ruta;

    }

}
