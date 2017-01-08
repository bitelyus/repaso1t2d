/**
 * Actividades de repaso para el examen final del 1t
 *   2º DAM - Acceso a Datos y Ficheros
 *   (C) 17-12-2016
 * =================================================
 * Controlador Actividad 3. Crear Receta XML o JSON
 */
package Contoler;

import Model.Receta;
import View.Ch;
import View.InterfazA3;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author bitelyus @ www.miguelkiko.com
 */
public class ControlerA3 {

    /**
     * Pocedimiento para que comiencen las acciones de la opción seleccionada.
     * 0. Comprobamos existencia carpeta de recetas. Si no se crea. 1. Pedimos
     * la opcion 2. La evaluamos 3. Controlamos el fluzo
     */
    public static void comenzar() {

        File recetasdir = new File("recetas");
        if (!recetasdir.exists()) {
            recetasdir.mkdir();
        }

        int opcion = 0;
        boolean salir = false;

        do {
            InterfazA3.menuPrincipal();
            opcion = Ch.pedirOpcion(2);
            switch (opcion) {
                case 1:
                    ControlerA3.crearReceta();
                    break;
                case 2:
                    ControlerA3.listarRecetas();
                    break;
                case 0:
                    salir = true;
                    break;
            }
        } while (!salir);

    }

    /**
     * Procedimiento para serializar una receta 1. Pendimos los datos básicos
     * para crear el objeto 2. Creamos el modelo receta 3. Usamos sus metodos
     * agregrar para añadir ingredientes y pasos 4. La mostramos por pantalla
     */
    public static void crearReceta() {

        // ZONA DE ATRÍBUTOS
        String nombre, autor, ingrediente, paso, opcion;
        int tiempo;
        boolean seguir = false;
        Receta receta = null;
        int tipo = 0; // 1 - XML  2 - JSON

        // ENTRANDA
        nombre = InterfazA3.pedirNombre();
        autor = InterfazA3.pedirAutor();
        tiempo = InterfazA3.pedirTiempo();
        receta = new Receta(nombre, autor, tiempo);

        // PROCESO
        do {
            ingrediente = InterfazA3.pedirIngrediente();
            receta.agregarIngrediente(ingrediente);
            seguir = Ch.pedirOpcionSN();
        } while (!seguir);

        seguir = false;
        do {
            paso = InterfazA3.pedirPaso();
            receta.agregarPaso(paso);
            seguir = Ch.pedirOpcionSN();
        } while (!seguir);

        tipo = InterfazA3.pedirOpcionXMLorJAXB();
        switch (tipo) {
            case 1:
                ControlerA3.guardarXML(receta);
                break;
            case 2:
                ControlerA3.guardarJSON(receta);
                break;
        }

        // SALIDA
        //Ch.lcd(receta.toString());
        Ch.pausa();
    }

    /**
     * Método para guardar en XML una Receta creada
     *
     * @param receta La receta con el modelo Receta que hemos creado
     */
    public static void guardarXML(Receta receta) {
        try {
            // Creamos un contexto de la clase JAXB y lo intanciamos
            // Es como el motorcito que hace que todo funciones. El contexto es DIOS
            JAXBContext context = JAXBContext.newInstance(Receta.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Lo creamos con system out para mostrarlo por pantalla
            m.marshal(receta, System.out);

            // Lo Escribimos en el archivo que queramos
            m.marshal(receta, new File("recetas/" + receta.getNombre() + ".xml"));
            Ch.lcd("i> SE HA ESCRITO EL ARCHIVO CORRECTAMENTE");
        } catch (Exception ex) {
            Ch.lcd(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Método que recibo un objeto Receta, crea un json y lo guarda en archivo
     *
     * @param receta La receta que vamos a serializar con Gson
     */
    public static void guardarJSON(Receta receta) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String miReceta = gson.toJson(receta);
            FileWriter fw = null;
            fw = new FileWriter("recetas/" + receta.getNombre() + ".json");
            fw.write(miReceta);
            fw.flush();
            fw.close();
            Ch.lcd(miReceta);
        } catch (Exception ex) {
            Logger.getLogger(ControlerA3.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    /**
     * Procedimiento para leer una receta del directorio de recetas. 1. Listar
     * archivos disponibles. 2. Pedir ruta archivo. 3. Evaluar Extensión.
     * 4.Deserializar. 5. Mostrar resultado por pantalla (receta.toString)
     */
    public static void listarRecetas() {

        // VARIABLES NECESARIAS E INICIALIZACION
        Boolean salir = false;
        String aux = "";
        String ruta = "";
        int i;
        BufferedReader br;
        Receta receta = null;
        // 1. VAMOS A LISTAR LOS ARCHIVOS CON EXTENSIÓN XML Y JSON DISPONIBLES
        InterfazA3.listarArchivos();

        // 2. AHORA PEDIMOS LA RUTA DEL ARCHIVO XML QUE VAMOS A CREAR
        salir = false;
        ruta = InterfazA3.pedirRutaLeer();

        // 3. EVALUAMOS EXTENSION PARA USAR JAXB O GSON .... 4. DESARIALIZAMOS
        if (ruta.endsWith("json")) {
            try {
                Gson gson = new Gson();
                receta = gson.fromJson(new FileReader("recetas/" + ruta), Receta.class);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (ruta.endsWith("xml")) {
            // VAMOS Y LEEEMOS EL ARCHIVO...
            try {
                // Creamos un contexto de la clase JAXB y lo intanciamos
                // Es como el motorcito que hace que todo funciones. El contexto es DIOS
                JAXBContext context = JAXBContext.newInstance(Receta.class
                );
                Marshaller m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                // Obtenemos las variables obtenidas del XML creado anteriormente
                Unmarshaller um = context.createUnmarshaller();
                receta = (Receta) um.unmarshal(new FileReader("recetas/" + ruta));
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        
        // 5. MOSTRAMOS POR PANTALLA
        Ch.lcd(receta.toString());
        Ch.pausa();
    }

}
