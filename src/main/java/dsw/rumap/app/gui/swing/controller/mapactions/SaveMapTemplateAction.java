package dsw.rumap.app.gui.swing.controller.mapactions;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.ScreenImage;
import dsw.rumap.app.gui.swing.controller.AbstractRumapActions;
import dsw.rumap.app.gui.swing.view.MainFrame;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class SaveMapTemplateAction extends AbstractRumapActions {

    JFileChooser jfc;

    public SaveMapTemplateAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/move.png"));
        putValue(NAME, "SaveMapAsTemplate");
        putValue(SHORT_DESCRIPTION, "Save Map As Template");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
//        jfc = new JFileChooser();
//        if(jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
//            AppCore.getInstance().getSerializer().saveMindMapTemplate(MainFrame.getInstance().getProjectView().getCurrentMindMapView().getModel());
//        }
        AppCore.getInstance().getSerializer().saveMindMapTemplate(MainFrame.getInstance().getProjectView().getCurrentMindMapView().getModel());
        JOptionPane.showMessageDialog(MainFrame.getInstance(), "Template Successfully Saved In Template Gallery", "Template Saved", JOptionPane.INFORMATION_MESSAGE);
    }
}
