package be.dog.D.steven.GUI;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class PersonFileFilter extends FileFilter {
    // FileFilter implements 2 methods
    // Set Filter
    @Override
    public boolean accept(File file) {

        // Get name to pass to Utils
        String name = file.getName();
        // Getting extension using small Utils class
        String extension = Utils.getFileExtension(name);

        // Show directories
        if(file.isDirectory()){
            return true;
        }

        // Catch null
        if(extension == null){
            return false;
        }

        // Return if (.)per
        return extension.equals("per");
    }

    //Set FilterName
    @Override
    public String getDescription() {
        return "Person database file (*.per)";
    }
}
