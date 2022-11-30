package dsw.rumap.app.gui.swing.view;



import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MindMapView extends JPanel implements ISubscriber {

    private MindMap model;
    private List<ElementPainter> painters;

    public MindMapView(MindMap model){
        this.model=model;
        int width = MainFrame.getInstance().getProjectView().getWidth();
        int h = MainFrame.getInstance().getProjectView().getHeight();
        this.setPreferredSize(new Dimension(width - 5,h - 40));
        painters = new ArrayList<>();

        //this.model.addSubscriber(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        for (ElementPainter ep :
                painters) {
            ep.draw(g2d);
        }
    }

    @Override
    public void update(Object notification) {
        SwingUtilities.updateComponentTreeUI(this);
    }
}
