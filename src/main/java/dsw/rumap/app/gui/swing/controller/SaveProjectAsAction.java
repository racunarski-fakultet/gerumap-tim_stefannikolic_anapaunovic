package dsw.rumap.app.gui.swing.controller;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.implementation.Project;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveProjectAsAction extends AbstractRumapActions{

    public SaveProjectAsAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/info.png"));
        putValue(NAME, "Save Project As");
        putValue(SHORT_DESCRIPTION, "Save Project As");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getMapTree().getSelectedNode() == null || !(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project)){
            //todo error
            return;
        }

        Project project = ((Project) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode());
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files (*.json)", "json");
        jfc.addChoosableFileFilter(filter);
        File projectFile = null;


        if(jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
            projectFile = jfc.getSelectedFile();
            if(!FilenameUtils.getExtension(projectFile.getName()).equalsIgnoreCase("json"))
                projectFile = new File(projectFile.getParentFile(), FilenameUtils.getBaseName(projectFile.getName()) + ".json");
            project.setFilePath(projectFile.getPath());
        }
        else return;

        AppCore.getInstance().getSerializer().saveProject(project);
    }
}
