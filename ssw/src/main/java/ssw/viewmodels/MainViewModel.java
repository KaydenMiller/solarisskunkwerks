/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssw.viewmodels;

import common.megamek.MegaMekToMechConverter;
import components.Mech;
import components.MegaMechBattlemech;
import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Kayden
 */
public class MainViewModel {
    public Mech ImportMechFromMTF(Component context) {
        String filepath = "";
        
        JFileChooser fileChooser = new JFileChooser();
        FileFilter mtfFilter = new FileNameExtensionFilter("MegaMek (*.mtf)", "mtf");
        fileChooser.addChoosableFileFilter(mtfFilter);
        fileChooser.setFileFilter(mtfFilter);

        int retval = fileChooser.showOpenDialog(context);
        
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            filepath = file.getPath();
        }
        MegaMechBattlemech megaMek = MegaMechBattlemech.parseFromFile(filepath);

        Mech mech = MegaMekToMechConverter.ConvertToSSWMech(megaMek);

        return mech;
    }
}
