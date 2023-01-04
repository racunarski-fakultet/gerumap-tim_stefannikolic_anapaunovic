package dsw.rumap.app.gui.swing.controller.mapactions;

import dsw.rumap.app.gui.swing.ScreenImage;
import dsw.rumap.app.gui.swing.controller.AbstractRumapActions;
import dsw.rumap.app.gui.swing.view.MainFrame;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class SaveMapJPGAction extends AbstractRumapActions {

    public SaveMapJPGAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/capture.png"));
        putValue(NAME, "PrintMap");
        putValue(SHORT_DESCRIPTION, "Print Map");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        BufferedImage ss = new BufferedImage(MainFrame.getInstance().getProjectView().getCurrentMindMapView().getWidth()+500, MainFrame.getInstance().getProjectView().getCurrentMindMapView().getHeight()+500, BufferedImage.TYPE_INT_RGB);
        BufferedImage bimage = ScreenImage.createImage(MainFrame.getInstance().getProjectView().getCurrentMapScrollPane());
        MainFrame.getInstance().getProjectView().getCurrentMindMapView().print(ss.getGraphics());
        String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter ScreenShot name:", "ScreenShot Name Input", JOptionPane.INFORMATION_MESSAGE);
        if(name.isEmpty()){
            //todo error
            return;
        }

        ScreenImage.writeImage(ss, "src/main/resources/ScreenShots/" + name + ".jpg");
        JOptionPane.showMessageDialog(MainFrame.getInstance(), "ScreenShot captured in ScreenShots directory");
    }
}
