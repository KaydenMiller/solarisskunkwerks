/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssw.viewmodels;

import components.Mech;
import components.MegaMechBattlemech;
import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Kayde
 */
public class MainViewModel {
    public Mech ImportMechFromMTF(Component context) {
        String filepath = "";
        
        JFileChooser fileChooser = new JFileChooser();
        int retval = fileChooser.showOpenDialog(context);
        
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            filepath = file.getPath();
        }
        
        Mech mech = new Mech();
        
        MegaMechBattlemech.parseFromFile(filepath);
        return mech;
    }
}
