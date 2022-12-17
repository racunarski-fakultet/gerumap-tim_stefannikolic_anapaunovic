package dsw.rumap.app.gui.swing.view;



import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.controller.mapactions.MindMapMouseController;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.gui.swing.view.painters.TermPainter;
import dsw.rumap.app.maprepository.implementation.elements.MapSelectionModel;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.elements.Pair;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;
import dsw.rumap.app.msggenerator.Problem;
import dsw.rumap.app.observer.IPublisher;
import dsw.rumap.app.observer.ISubscriber;
import dsw.rumap.app.observer.notification.MyNotification;
import dsw.rumap.app.observer.notification.NotificationType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class MindMapView extends JPanel implements ISubscriber, IPublisher, AdjustmentListener {

    private final MindMap model;
    private MapSelectionModel mapSelectionModel;
    private List<ElementPainter> painters;
    private Rectangle2D selectionRec;
    private AffineTransform affineTransform;
    private double scaleFactor;
    private Pair<Double, Double> translate;
    List<ISubscriber> subscribers;
    private int oldVscValue;
    private int oldHscValue;



    public MindMapView(MindMap model){
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        subscribers = new ArrayList<ISubscriber>();
        this.model=model;
        this.mapSelectionModel = new MapSelectionModel();
        mapSelectionModel.subscribe(this);
        int width = MainFrame.getInstance().getProjectView().getWidth();
        int height = MainFrame.getInstance().getProjectView().getHeight();
        //this.setPreferredSize(new Dimension(3000,1200));
        this.setMinimumSize(new Dimension(5000,3500));
        //this.setMaximumSize(new Dimension(3000,1200));
        painters = new ArrayList<>();
        MindMapMouseController mmmC = new MindMapMouseController(this);
        this.addMouseListener(mmmC);
        this.addMouseMotionListener(mmmC);
        this.model.subscribe(this);
        selectionRec = new Rectangle2D.Float(0,0,0,0);
        affineTransform = new AffineTransform();
        affineTransform.scale(1,1);
        scaleFactor = 1;
        oldVscValue = 0;
        oldHscValue = 0;

        translate = new Pair<Double, Double>((double) 0, (double) 0);
        MainFrame.getInstance().subscribe(this);

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

        g2d.setTransform(affineTransform);

        MainFrame.getInstance().getProjectView().getCurrentMapScrollPane().getHorizontalScrollBar().setMaximum((10+Math.max(100, getFPoints().getFirst()))/5);
        MainFrame.getInstance().getProjectView().getCurrentMapScrollPane().getVerticalScrollBar().setMaximum((10+Math.max(100, getFPoints().getSecond()))/5);


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

    }

    private void drawPainter(ElementPainter ep, Graphics2D g2d){

        ep.draw(g2d);
    }

    @Override
    public void update(Object notification) {

        NotificationType notificationType;

        if(notification instanceof MyNotification) {
            notificationType = ((MyNotification) notification).getType();
            if(notificationType.equals(NotificationType.MAP_DELETED)){
                model.unsubscribe(this);
                MainFrame.getInstance().unsubscribe(this);
            }
        }
        else {
            this.repaint();
            SwingUtilities.updateComponentTreeUI(this);
        }
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

        double prevX = affineTransform.getTranslateX();
        double prevY = affineTransform.getTranslateY();
        affineTransform.scale(scaleFactor, scaleFactor);

        if(Math.abs(affineTransform.getScaleX()-1) < 0.01){
            AffineTransform newTransform = new AffineTransform();
            newTransform.translate(affineTransform.getTranslateX(), affineTransform.getTranslateY());
            affineTransform.setTransform(newTransform);
        }

        if(MainFrame.getInstance().getProjectView().getCurrentMapScrollPane().getVerticalScrollBar().getValue() == 0 && MainFrame.getInstance().getProjectView().getCurrentMapScrollPane().getHorizontalScrollBar().getValue() == 0){
            AffineTransform at = new AffineTransform();
            at.scale(affineTransform.getScaleX(), affineTransform.getScaleY());
            affineTransform.setTransform(at);
        }

        this.notify(this);
        this.revalidate();
        this.repaint();
    }

    public void zoomIn(){
        scaleFactor = 1.05;

        if(affineTransform.getScaleX() >= 1.9){
            AppCore.getInstance().getMsgGenerator().createMessage(Problem.ZOOM_IS_AT_MAX);
            return;
        }
        setUpTransformation();
    }

    public void zoomOut(){
        scaleFactor = 0.95238;

        if(affineTransform.getScaleX() <= 0.2){
            AppCore.getInstance().getMsgGenerator().createMessage(Problem.ZOOM_IS_AT_MIN);
            return;
        }
        setUpTransformation();
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        int step = 5;
        if(((JScrollBar)e.getSource()).getOrientation() == 0) {
            double prevX = affineTransform.getTranslateX()+(oldHscValue - e.getValue())*step*affineTransform.getScaleX()*affineTransform.getScaleX();
            affineTransform.translate((oldHscValue - e.getValue())*affineTransform.getScaleX()*step*affineTransform.getScaleX(), 0);
            affineTransform.translate((prevX-affineTransform.getTranslateX()), 0);
            translate.setFirst((double)(e.getValue()));
            oldHscValue = e.getValue();
        }

        else {
            double prevY = affineTransform.getTranslateY()+(oldVscValue - e.getValue())*step*affineTransform.getScaleX()*affineTransform.getScaleX();
            affineTransform.translate(0, (oldVscValue - e.getValue())*step*affineTransform.getScaleX()*affineTransform.getScaleX());
            affineTransform.translate(0, (prevY-affineTransform.getTranslateY()));
            translate.setSecond((double)(e.getValue()));
            oldVscValue = e.getValue();
        }

        if(MainFrame.getInstance().getProjectView().getCurrentMapScrollPane().getVerticalScrollBar().getValue() == 0 && MainFrame.getInstance().getProjectView().getCurrentMapScrollPane().getHorizontalScrollBar().getValue() == 0){
            AffineTransform at = new AffineTransform();
            at.scale(affineTransform.getScaleX(), affineTransform.getScaleY());
            affineTransform.setTransform(at);
        }

        revalidate();
        repaint();
    }

    @Override
    public void subscribe(ISubscriber sub) {
        this.subscribers.add(sub);
    }

    @Override
    public void unsubscribe(ISubscriber sub) {
        this.subscribers.remove(sub);
    }

    @Override
    public void notify(Object notification) {
        for (ISubscriber sub :
                subscribers) {
            sub.update(notification);
        }
    }

    public Pair<Integer, Integer> getTranslate(){
        return new Pair<Integer, Integer>((int)(affineTransform.getTranslateX()), (int)(affineTransform.getTranslateY()));
    }

    public double getScale(){
        return affineTransform.getScaleX();
    }

    private Pair<Integer, Integer> getFPoints(){
        int x = Integer.MIN_VALUE;
        int y = Integer.MIN_VALUE;
        for (ElementPainter ep :
                painters) {
            if(ep instanceof TermPainter){
                TermElement te = (TermElement) ep.getElement();
                if(te.getPosition().getFirst() > x)
                    x = te.getPosition().getFirst();
                if(te.getPosition().getSecond() > y)
                    y = te.getPosition().getSecond();
            }
        }
        return new Pair<>(x, y);
    }
}
