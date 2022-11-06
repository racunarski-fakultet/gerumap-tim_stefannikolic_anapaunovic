package dsw.rumap.app.gui.swing.view;



import dsw.rumap.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class MindMapView extends JPanel implements ISubscriber {

    public MindMapView(){
        int width = MainFrame.getInstance().getDesktop().getWidth();
        int h = MainFrame.getInstance().getDesktop().getHeight();
        this.setPreferredSize(new Dimension(width - 5,h - 20));

    }

    //kasnije - da u konstruktor stavimo setovanje modela i da dodamo subscribovanje na model

    @Override
    public void update(Object notification) {

        SwingUtilities.updateComponentTreeUI(this);
    }
}
