package dsw.rumap.app.gui.swing.view;



import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class MindMapView extends JPanel implements ISubscriber {

    private MindMap model;

    public MindMapView(MindMap model){
        this.model=model;
        int width = MainFrame.getInstance().getProjectView().getWidth();
        int h = MainFrame.getInstance().getProjectView().getHeight();
        this.setPreferredSize(new Dimension(width - 5,h - 40));

        //this.model.addSubscriber(this);

    }


    @Override
    public void update(Object notification) {
        SwingUtilities.updateComponentTreeUI(this);
    }
}
