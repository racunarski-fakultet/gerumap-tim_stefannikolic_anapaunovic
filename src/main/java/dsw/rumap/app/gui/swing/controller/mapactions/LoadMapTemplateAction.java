package dsw.rumap.app.gui.swing.controller.mapactions;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.ScreenImage;
import dsw.rumap.app.gui.swing.controller.AbstractRumapActions;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class LoadMapTemplateAction extends AbstractRumapActions {

    JFileChooser jfc;
    MindMap loadedMindMap;

    public LoadMapTemplateAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/move.png"));
        putValue(NAME, "LoadMapTemplate");
        putValue(SHORT_DESCRIPTION, "Load Map Template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jfc = new JFileChooser();
        jfc.addChoosableFileFilter(new FileNameExtensionFilter("JSON file (*.json)", "json"));
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setCurrentDirectory(new File("src/main/resources/templateGallery"));
        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
            File file = jfc.getSelectedFile();
            loadedMindMap = AppCore.getInstance().getSerializer().loadMindMapTemplate(file);
        }
        else return;
        if(MainFrame.getInstance().getProjectView().getCurrentMindMapView() == null){
            return;
        }
        AppCore.getInstance().getMapRepository().loadMapTemplate(loadedMindMap, MainFrame.getInstance().getProjectView().getCurrentMindMapView().getModel());
    }
}
