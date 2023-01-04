package dsw.rumap.app.gui.swing.controller;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Project;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class LoadProjectAction extends AbstractRumapActions{

    public LoadProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/info.png"));
        putValue(NAME, "Load Project");
        putValue(SHORT_DESCRIPTION, "Load Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.addChoosableFileFilter(new FileNameExtensionFilter("JSON file (*.json)", "json"));
        jfc.setAcceptAllFileFilterUsed(false);
        if(jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
            File file = jfc.getSelectedFile();
            Project project = AppCore.getInstance().getSerializer().loadProject(file);
            boolean cont = true;
            for (MapNode childProject:
                AppCore.getInstance().getMapRepository().getProjectExplorer().getChildren()) {
                if(project.getName().equals(childProject.getName())) {
                    String newName = JOptionPane.showInputDialog(MainFrame.getInstance(),
                            "Enter new name for this project:",
                            "Project with that name already exists",
                            JOptionPane.WARNING_MESSAGE);
                    if(newName == null || newName.isEmpty()){
                        cont = false;
                        //todo greska ne moze biti prazno
                    }
                    else if(!AppCore.getInstance().getMapRepository().getProjectExplorer().checkName(newName)){
                        cont = false;
                        //todo greska to ime isto postoji
                    }
                    else project.setName(newName);
                }
            }
            if(!cont) return;
            MainFrame.getInstance().getMapTree().loadProject(project);
            AppCore.getInstance().getMapRepository().loadProject(project);
        }
    }
}
