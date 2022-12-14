package dsw.rumap.app.gui.swing.view;



import dsw.rumap.app.gui.swing.controller.mapactions.MindMapMouseController;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.gui.swing.view.painters.TermPainter;
import dsw.rumap.app.maprepository.implementation.elements.MapSelectionModel;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.elements.Pair;
import dsw.rumap.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class MindMapView extends JPanel implements ISubscriber, AdjustmentListener {

    private final MindMap model;
    private MapSelectionModel mapSelectionModel;
    private List<ElementPainter> painters;
    private Rectangle2D selectionRec;
    private AffineTransform affineTransform;
    private double scaleFactor;
    private Pair<Double, Double> translate;


    public MindMapView(MindMap model){
        this.model=model;
        this.mapSelectionModel = new MapSelectionModel();
        mapSelectionModel.subscribe(this);
        int width = MainFrame.getInstance().getProjectView().getWidth();
        int height = MainFrame.getInstance().getProjectView().getHeight();
        this.setPreferredSize(new Dimension(4000,3000));
        painters = new ArrayList<>();
        MindMapMouseController mmmC = new MindMapMouseController(this);
        this.addMouseListener(mmmC);
        this.addMouseMotionListener(mmmC);
        this.model.subscribe(this);
        selectionRec = new Rectangle2D.Float(0,0,0,0);
        affineTransform = new AffineTransform();
        scaleFactor = 1;
        translate = new Pair<Double, Double>((double) 0, (double) 0);
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

        float[] dash1 = { 2f, 0f, 2f };
        BasicStroke bs1 = new BasicStroke(1,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND,
                1.0f,
                dash1,
                2f);
        g2d.setStroke(bs1);
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
        System.out.println("draw");
    }

    private void drawPainter(ElementPainter ep, Graphics2D g2d){
        //g2d.scale(scaleFactor, scaleFactor);
        g2d.setTransform(affineTransform);
        System.out.println("drawPainter");
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

    private void setUpTransformation(){
        //affineTransform.translate(translate.getFirst(), translate.getSecond());
        //affineTransform.setToScale(scaleFactor, scaleFactor);
        affineTransform.scale(scaleFactor, scaleFactor);
        System.out.println("setUp");
        this.repaint();
    }

    public void zoomIn(){
        scaleFactor = 1.2;
        System.out.println("zoomIn");

        if(scaleFactor > (double) 2){
            scaleFactor = 2;
            System.out.println("preko 2");
            //todo greska
            return;
        }
        setUpTransformation();
    }

    public void zoomOut(){
        System.out.println("zoomOut");
        scaleFactor = 0.8;

        if(scaleFactor < 0.2){
            scaleFactor = 0.2;
            //todo greska
            return;
        }
        setUpTransformation();
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
//        if(((JScrollBar)e.getSource()).getOrientation() == 0)
//            translate.setFirst((double)(e.getValue()));
//        else translate.setSecond((double)(e.getValue()));

//        setUpTransformation();
    }
}
