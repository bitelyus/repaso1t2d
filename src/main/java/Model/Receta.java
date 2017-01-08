/**
 * Actividades de repaso para el examen final del 1t
 *   2º DAM - Acceso a Datos y Ficheros
 *   (C) 17-12-2016
 * =================================================
 * MODELO DE DATOS DE UNA RECETA. CON NOMBRE, AUTOR, TIEMPO, INGREDINTES Y PASOS
 */
package Model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author bitelyus @ www.miguelkiko.com
 */

@XmlRootElement(name = "receta")  // Le decimos que el elemento base es "Receta"
//Si quieres definir el orden en que seran escritas las etiquetas
// Opcional
@XmlType(propOrder = {"nombre", "autor", "tiempo", "ingredientes","pasos"})
public class Receta {
    
    // ZONA DE ATRIBUTOS
    
    private String nombre;
    private String autor;
    private int tiempo;
    
    @XmlElement(name = "ingrediente")
    private ArrayList<String> ingredientes;
    @XmlElement(name = "paso")
    private ArrayList<String> pasos;

    // ZONA DE CONSTRUCTORES
    
    /**
     * Constuctor vacio sin datos
     */
    public Receta() {
        this.nombre = "";
        this.autor = "";
        this.tiempo = 0;
        this.ingredientes = new ArrayList<String>();
        this.pasos = new ArrayList<String>();
    }
    
    /**
     * Constructor con datos básicos. 
     * @param nombre El nombre de la receta
     * @param autor El autor de la receta
     * @param tiempo El tiempo de elaboracion
     */
    public Receta(String nombre, String autor, int tiempo) {
        this.nombre = nombre;
        this.autor = autor;
        this.tiempo = tiempo;
        this.ingredientes = new ArrayList<String>();
        this.pasos = new ArrayList<String>();
    }
    
    // Getters & Setters
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
    @XmlTransient // Esto se pone para que no repita los datos en el xml
    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }
    
    @XmlTransient // Esto se pone para que no repita los datos en el xml
    public ArrayList<String> getPasos() {
        return pasos;
    }

    public void setPasos(ArrayList<String> pasos) {
        this.pasos = pasos;
    }

    // ZONA DE MÉTODOS
    
    /**
     * Método para agregar un ingrediente a la receta
     * @param ingrediente El ingretiende en String
     */
    public void agregarIngrediente(String ingrediente) {
        this.ingredientes.add(ingrediente);
    }
    
    /**
     * Método para agregar un paso de elaboración de la receta
     * @param paso El paso en String
     */
    public void agregarPaso(String paso) {
        this.pasos.add(paso);
    }
    
    /**
     * Sobreescritra del método toStrin para formatear la salida por pantalla
     * del objeto de datos
     * @return Los datos del objeto en String
     */
    public String toString() {
        String salida = "";
        int i;
        salida+="\nRECETA: '"+this.getNombre().toUpperCase()+"'\n";
        salida+="=========";
        for (i = 0; i<this.getNombre().length();i++) {
            salida+="=";
        };
        salida+="=\n";
        salida+="AUTOR RECETA: "+ this.getAutor() + "\n";
        salida+="TIEMPO ELAB.: "+ this.getTiempo() + "\n";
        salida+="\nINGREDIENTES:\n";
        salida+="-------------\n";
        for (i = 0; i<this.ingredientes.size();i++) {
            salida+=(i+1) + ". " + this.ingredientes.get(i) + "\n";
        }
        salida+="\nPASOS:\n";
        salida+="------\n";
        for (i = 0; i<this.pasos.size();i++) {
            salida+=(i+1) + ". " + this.pasos.get(i) + "\n";
        }

        return salida;
    }
    
}
