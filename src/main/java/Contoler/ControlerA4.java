/**
 * Actividades de repaso para el examen final del 1t
 *   2º DAM - Acceso a Datos y Ficheros
 *   (C) 17-12-2016
 * =================================================
 * Controlador Actividad 3. Crear Receta XML o JSON
 */
package Contoler;

import Model.LibroRecetas;
import Model.Receta;
import View.Ch;
import View.InterfazA4;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
public class ControlerA4 {

    /**
     * Pocedimiento para que comiencen las acciones de la opción seleccionada.
     * 0. Comprobamos existencia carpeta de recetas. Si no se crea. 1. Pedimos
     * la opcion 2. La evaluamos 3. Controlamos el fluzo
     */
    public static void comenzar() {

        File recetasdir = new File("recetas/libros");
        if (!recetasdir.exists()) {
            recetasdir.mkdir();
        }

        int opcion = 0;
        boolean salir = false;

        do {
            InterfazA4.menuPrincipal();
            opcion = Ch.pedirOpcion(3);
            switch (opcion) {
                case 1:
                    ControlerA3.crearReceta();
                    break;
                case 2:
                    ControlerA3.listarRecetas();
                    break;
                case 3:
                    ControlerA4.crearLibro();
                    break;
                case 0:
                    salir = true;
                    break;
            }
        } while (!salir);

    }

    /**
     * Método crear un libro de cocina partiendo de los xml y json que tenemos
     * 1. Pedir datos del libro. Nombre y año... 2. Listar Recetar 3. Pedir
     * listado de recetas (ArrayList) 4. Volcar a LibroRecetas 5. Pedir formato
     * deseado 6. Serializar objeto con todos los datos...
     */
    public static void crearLibro() {
        
        // ZONA DE ATRIBUTOS E INCIALIZACIÓN
        String nombre = "";
        int tipo = 0;
        int año = 0;
        LibroRecetas milibro = null;
        boolean seguir = true;
        File midir = new File("recetas");
        File[] misarchivos = midir.listFiles();

        // SI HAY RECETAS CREADAS...
        if (misarchivos.length > 0) {
            String mireceta = null;

            // ENTRADA: pedir datos libro y recetas
            nombre = InterfazA4.pedirNombre();
            año = InterfazA4.pedirTiempo();
            milibro = new LibroRecetas(nombre, año);
            do {
                InterfazA4.listarArchivos();
                mireceta = InterfazA4.pedirRutaLeer();
                ControlerA4.agregarReceta(milibro, mireceta);
                seguir = Ch.pedirOpcionSN();
            } while (!seguir);

            // PROCESO: Tratar datos. Pedir Formato de Escritura
            tipo = InterfazA4.pedirOpcionXMLorJAXB();
            switch (tipo) {
                case 1:
                    ControlerA4.guardarXML(milibro);
                    break;
                case 2:
                    ControlerA4.guardarJSON(milibro);
                    break;
            }

            // SALIDA
            //Ch.lcd(receta.toString());
            Ch.lcd("i> LibroRecetas creado correctamente!");
            Ch.pausa();

            // SALIDA: mostrar el libro
            Ch.lcd(milibro.toString());
            Ch.pausa();
        } else { // SI NO HAY RECETAS...
            Ch.lcd("\ni> !No hay recetas con las que crear el libro!");
            Ch.pausa();
        }

    }

    public static void agregarReceta(LibroRecetas milibro, String ruta) {
        Receta receta = null;
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

        // 5. YA TENEMOS LA RECETA ... LA AÑADIMOS A LA ESTRUCTURA CON EL AUTOR
        Ch.lcd(receta.toString());
        Ch.pausa();
        milibro.agregarReceta(receta);;
        milibro.agrearAutor(receta.getAutor());

    }

    /**
     * Método para guardar en XML una Receta creada
     *
     * @param receta La receta con el modelo Receta que hemos creado
     */
    public static void guardarXML(LibroRecetas libro) {
        try {
            // Creamos un contexto de la clase JAXB y lo intanciamos
            // Es como el motorcito que hace que todo funciones. El contexto es DIOS
            JAXBContext context = JAXBContext.newInstance(LibroRecetas.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Lo creamos con system out para mostrarlo por pantalla
            m.marshal(libro, System.out);

            // Lo Escribimos en el archivo que queramos
            m.marshal(libro, new File("recetas/libros/" + libro.getNombre() + ".xml"));
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
    public static void guardarJSON(LibroRecetas libro) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String miLibro = gson.toJson(libro);
            FileWriter fw = null;
            fw = new FileWriter("recetas/libros/" + libro.getNombre() + ".json");
            fw.write(miLibro);
            fw.flush();
            fw.close();
            Ch.lcd(miLibro);
        } catch (Exception ex) {
            Logger.getLogger(ControlerA3.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

}
