package dsw.rumap.app.gui.swing.view;



import dsw.rumap.app.gui.swing.controller.mapactions.MindMapMouseController;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.maprepository.implementation.elements.MapSelectionModel;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class MindMapView extends JPanel implements ISubscriber {

    private MindMap model;
    private MapSelectionModel mapSelectionModel;
    private List<ElementPainter> painters;
    private Rectangle2D selection;
    private Rectangle2D lastRactangle;


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
        lastRactangle = new Rectangle2D.Float(0,0,0,0);
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
        if(selection != null)
            g2d.draw(selection);
        for (ElementPainter ep :
                painters) {
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

    public Rectangle2D getSelectionR(int x, int y, int h, int w) {
        selection = new Rectangle2D.Float(x,y,h,w);
        return selection;
    }
    public void setCoordinates(int x,int y, int w, int h){
        if(selection != null){
            selection.setRect(x,y,w,h);
            this.repaint();
        }

    }
    public void setLastCoordinates(int x, int y) {
        if(selection != null)
            this.lastRactangle.setRect(x,y,selection.getHeight(),selection.getWidth());
    }
}
