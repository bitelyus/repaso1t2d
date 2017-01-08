/**
 * Actividades de repaso para el examen final del 1t
 *   2º DAM - Acceso a Datos y Ficheros
 *   (C) 17-12-2016
 */
package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author bitelyus @ www.miguelkiko.com
 */

public class LibroRecetas {
    
    private String nombre;
    private int año;
    private HashSet<String> autores;
    private ArrayList<Receta> recetas;

    public LibroRecetas() {
        this.nombre = "";
        this.año = 0;
        this.autores = new HashSet<String>();
        this.recetas = new ArrayList<>();
    }

    public LibroRecetas(String nombre, int año) {
        this.nombre = nombre;
        this.año = año;
        this.autores = new HashSet<String>();
        this.recetas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public HashSet<String> getAutores() {
        return autores;
    }

    public void setAutores(HashSet<String> autores) {
        this.autores = autores;
    }
    
    public ArrayList<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(ArrayList<Receta> recetas) {
        this.recetas = recetas;
    }  
    
    public void agregarReceta(Receta receta) {
        this.recetas.add(receta);
    }
    
    public void agrearAutor(String autor) {
        this.autores.add(autor);
    }
    
    /**
     * Sobreescritra del método toStrin para formatear la salida por pantalla
     * del objeto de datos
     * @return Los datos del objeto en String
     */
    public String toString() {
        String salida = "";
        int i;
        salida+="\nLIBRO DE RECETAS: '"+this.getNombre().toUpperCase()+"'\n";
        salida+="===================";
        for (i = 0; i<this.getNombre().length();i++) {
            salida+="=";
        };
        salida+="=\n";
        salida+="AÑO PUBLICACION: "+ this.getAño()+ "\n";
        salida+="\nAUTORES:\n";
        salida+="--------\n";
        
        for (Iterator it = this.autores.iterator(); it.hasNext();) {
            String nombre = (String)it.next();
            salida+=(i+1) + ". " + nombre + "\n";
        }
        salida+="\nRECETAS:\n";
        salida+="--------\n";
        for (i = 0; i<this.recetas.size();i++) {
            salida+=(i+1) + ". " + this.recetas.get(i).toString() + "\n";
        }

        return salida;
    }
    
}
