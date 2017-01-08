/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contoler;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author bitelyus @ <www.miguelkiko.com>
 */
public class MiFiltro implements FilenameFilter {
    
    // ZONA DE ATRIBUTOS 
    
    String extension;
    
    // ZONA DE CONSTRUCTORES
    
    public MiFiltro(String filtro) {
        this.extension = filtro;
    }

    // ZONA DE SOBREESCRITURA METODOS DE LA INTERFACE
    
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }
    
    
}
