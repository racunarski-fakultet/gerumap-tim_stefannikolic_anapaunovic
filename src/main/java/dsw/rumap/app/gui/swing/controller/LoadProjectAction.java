package dsw.rumap.app.gui.swing.controller;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.implementation.Project;

import javax.swing.*;
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
        if(jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
            File file = jfc.getSelectedFile();
            Project project = AppCore.getInstance().getSerializer().loadProject(file);
            MainFrame.getInstance().getMapTree().loadProject(project);
        }
    }
}
