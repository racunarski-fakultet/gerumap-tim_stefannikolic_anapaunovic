package dsw.rumap.app.gui.swing.controller;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.msggenerator.Problem;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveProjectAction extends AbstractRumapActions {

    public SaveProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/info.png"));
        putValue(NAME, "Save Project");
        putValue(SHORT_DESCRIPTION, "Save Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getMapTree().getSelectedNode() == null || !(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project)){
            AppCore.getInstance().getMsgGenerator().createMessage(Problem.SELECTED_NODE_IS_NOT_PROJECT);
            return;
        }

        Project project = ((Project) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode());
        JFileChooser jfc = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files (*.json)", "json");
        jfc.addChoosableFileFilter(filter);
        File projectFile = null;

        if (!project.isChanged()) {
            return;
        }

        if(project.getFilePath() == null || project.getFilePath().isEmpty()){
            if(jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
                projectFile = jfc.getSelectedFile();
                if(!FilenameUtils.getExtension(projectFile.getName()).equalsIgnoreCase("json"))
                    projectFile = new File(projectFile.getParentFile(), FilenameUtils.getBaseName(projectFile.getName()) + ".json");
                project.setFilePath(projectFile.getPath());
            }
            else return;
        }

        AppCore.getInstance().getSerializer().saveProject(project);
        project.setChanged(false);

    }
}
