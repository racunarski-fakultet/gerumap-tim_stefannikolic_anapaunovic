package dsw.rumap.app.gui.swing.view;



import dsw.rumap.app.gui.swing.controller.mapactions.MindMapMouseController;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.gui.swing.view.painters.TermPainter;
import dsw.rumap.app.maprepository.implementation.elements.MapSelectionModel;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class MindMapView extends JPanel implements ISubscriber {

    private MindMap model;
    private MapSelectionModel mapSelectionModel;
    private List<ElementPainter> painters;
    private Rectangle2D selectionRec;


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
        selectionRec = new Rectangle2D.Float(0,0,0,0);
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

        if(selectionRec != null)
            g2d.draw(selectionRec);

        for (ElementPainter ep :
                painters) {
            if(ep instanceof TermPainter)
                terms.add(ep);
            else drawPainter(ep, g2d);
        }

        for (ElementPainter ep :
                terms) {
            drawPainter(ep, g2d);
        }
    }

    private void drawPainter(ElementPainter ep, Graphics2D g2d){
        Color prev;
        if(mapSelectionModel.isSelected(ep.getElement())){
            prev = ep.getElement().getColor();
            ep.getElement().setColor(Color.RED);
            ep.draw(g2d);
            ep.getElement().setColor(prev);
        }
        else ep.draw(g2d);
    }

    @Override
    public void update(Object notification) {
        this.repaint();
        SwingUtilities.updateComponentTreeUI(this);
    }

    public List<ElementPainter> getPainters(){
        return painters;
    }
    public MapSelectionModel getMapSelectionModel(){
        return mapSelectionModel;
    }
    public MindMap getModel(){
        return this.model;
    }

    public Rectangle2D getSelectionRec() {
        return selectionRec;
    }
    public void setSelectionRec(int x, int y, int w, int h){
        selectionRec.setRect(x,y,w,h);
        this.repaint();
    }
}
