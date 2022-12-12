package dsw.rumap.app.gui.swing.view;



import dsw.rumap.app.gui.swing.controller.mapactions.MindMapMouseController;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.gui.swing.view.painters.RelationPainter;
import dsw.rumap.app.gui.swing.view.painters.TermPainter;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.MapSelectionModel;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MindMapView extends JPanel implements ISubscriber {

    private MindMap model;
    private MapSelectionModel mapSelectionModel;
    private List<ElementPainter> painters;

    public MindMapView(MindMap model){
        this.model=model;
        this.mapSelectionModel = new MapSelectionModel();
        mapSelectionModel.subscribe(this);
        int width = MainFrame.getInstance().getProjectView().getWidth();
        int height = MainFrame.getInstance().getProjectView().getHeight();
        this.setPreferredSize(new Dimension(width - 5,height - 40));
        painters = new ArrayList<>();
        MindMapMouseController mmmC = new MindMapMouseController(this);
        this.addMouseListener(mmmC);
        this.addMouseMotionListener(mmmC);
        this.model.subscribe(this);
    }

    public void addPainter(ElementPainter painter){
        painters.add(painter);
        painter.getElement().subscribe(this);
    }

    public void removePainter(ElementPainter painter){
        painters.remove(painter);
        painter.getElement().unsubscribe(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        List<ElementPainter> terms = new ArrayList<>();

        for (ElementPainter ep :
                painters) {
            if(ep instanceof TermPainter)
                terms.add(ep);
            else ep.draw(g2d);
        }

        for (ElementPainter ep :
                terms) {
            if(mapSelectionModel.isSelected(ep.getElement())){
                Color prev = ep.getElement().getColor();
                ep.getElement().setColor(Color.RED);
                ep.draw(g2d);
                ep.getElement().setColor(prev);
            }
            else ep.draw(g2d);
        }
    }

    @Override
    public void update(Object notification) {
        this.repaint();
        SwingUtilities.updateComponentTreeUI(this);
    }

    public List<ElementPainter> getPainters(){
        return painters;
    }
    public MapSelectionModel getSelectedElements(){
        return mapSelectionModel;
    }
    public MindMap getModel(){
        return this.model;
    }
}
