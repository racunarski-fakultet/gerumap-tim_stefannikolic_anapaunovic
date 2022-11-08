package dsw.rumap.app.gui.swing.view;



import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class MindMapView extends JPanel implements ISubscriber {

    private MindMap model;

    public MindMapView(){
        int width = MainFrame.getInstance().getDesktop().getWidth();
        int h = MainFrame.getInstance().getDesktop().getHeight();
        this.setPreferredSize(new Dimension(width - 5,h - 40));

        //this.model.addSubscriber(this);

    }

    //kasnije - da u konstruktor stavimo setovanje modela i da dodamo subscribovanje na model

    @Override
    public void update(Object notification) {

        SwingUtilities.updateComponentTreeUI(this);
    }
}
