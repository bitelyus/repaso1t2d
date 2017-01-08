/**
 * Actividades de repaso para el examen final del 1t
 *   2º DAM - Acceso a Datos y Ficheros
 *   (C) 17-12-2016 - Miguel Angel Camacho Sánchez
 */
package Contoler;

import View.Ch;
/**
 *
 * @author bitelyus @ www.miguelkiko.com
 */
public class Main {
    
    public static void main(String[] args) {
    
        int opcion = 0;
        boolean salir = false;
        
        do {
            Ch.menuPrincipal();
            opcion = Ch.pedirOpcion(4);
            switch (opcion) {
                case 1:
                    ControlerA1.comenzar();
                    break;
                case 2:
                    ControlerA2.comenzar();
                    break;
                case 3:
                    ControlerA3.comenzar();
                    break;
                case 4:
                    ControlerA4.comenzar();
                    break;
                case 0: 
                    salir = true;
                    break; 
            }
        } while (!salir);
        
    }
    
}
