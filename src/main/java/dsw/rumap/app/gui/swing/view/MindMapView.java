package dsw.rumap.app.gui.swing.view;



import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.controller.mapactions.MindMapMouseController;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.gui.swing.view.painters.RelationPainter;
import dsw.rumap.app.gui.swing.view.painters.TermPainter;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.MapSelectionModel;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.elements.Pair;
import dsw.rumap.app.maprepository.implementation.elements.RelationElement;
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
    List<ISubscriber> subscribers;
    private int oldVscValue;
    private int oldHscValue;
    private double pcScale;
    private boolean setPcScale;
    int step;


    public MindMapView(MindMap model) {
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        subscribers = new ArrayList<>();
        this.model = model;
        this.mapSelectionModel = new MapSelectionModel();
        mapSelectionModel.subscribe(this);
        int width = MainFrame.getInstance().getProjectView().getWidth();
        int height = MainFrame.getInstance().getProjectView().getHeight();
        //this.setPreferredSize(new Dimension(3000,1200));
        this.setMinimumSize(new Dimension(5000, 3500));
        //this.setMaximumSize(new Dimension(3000,1200));
        painters = new ArrayList<>();
        MindMapMouseController mmmC = new MindMapMouseController(this);
        this.addMouseListener(mmmC);
        this.addMouseMotionListener(mmmC);
        this.model.subscribe(this);
        selectionRec = new Rectangle2D.Float(0, 0, 0, 0);
        affineTransform = new AffineTransform();
        oldVscValue = 0;
        oldHscValue = 0;
        step = 5;
        MainFrame.getInstance().subscribe(this);
        setPcScale = true;
        for (MapNode mnElement :
                this.model.getChildren()) {
            Element element = (Element) mnElement;
            ElementPainter newPainter;
            if(element instanceof TermElement){
                newPainter = new TermPainter(element);
            }
            else newPainter = new RelationPainter(element);
            painters.add(newPainter);
            mnElement.subscribe(this);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        List<ElementPainter> terms = new ArrayList<>();
        if (setPcScale) {
            affineTransform = g2d.getTransform();
            System.out.println(affineTransform.getScaleX());
            pcScale = affineTransform.getScaleX();
            step /= pcScale;
            setPcScale = false;
        }
        g2d.setTransform(affineTransform);

        MainFrame.getInstance().getProjectView().getCurrentMapScrollPane().getHorizontalScrollBar().setMaximum(((int)(20*Math.pow(affineTransform.getScaleX(), 5))+ Math.max(100, getFPoints().getFirst())) / step);
        MainFrame.getInstance().getProjectView().getCurrentMapScrollPane().getVerticalScrollBar().setMaximum(((int)(20*Math.pow(affineTransform.getScaleX(), 5))+ Math.max(100, getFPoints().getSecond())) / step);
        System.out.println("H scroll: "+MainFrame.getInstance().getProjectView().getCurrentMapScrollPane().getHorizontalScrollBar().getMaximum()*5);
        System.out.println("V scroll: "+MainFrame.getInstance().getProjectView().getCurrentMapScrollPane().getVerticalScrollBar().getMaximum()*5);
        System.out.println("razlika x: "+ (MainFrame.getInstance().getProjectView().getCurrentMapScrollPane().getHorizontalScrollBar().getMaximum()*5 - getFPoints().getFirst()));
        System.out.println("max x: "+getFPoints().getFirst());
        System.out.println("max y: "+getFPoints().getSecond());


        float[] dash1 = {2f, 0f, 2f};
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
            if (ep instanceof TermPainter)
                terms.add(ep);
            else ep.draw(g2d);
        }

        for (ElementPainter ep :
                terms) {
            ep.draw(g2d);
        }
        System.out.println("trans: "+affineTransform.getTranslateX()+":"+affineTransform.getTranslateY());
        System.out.println("scale"+ affineTransform.getScaleX());
    }

    @Override
    public void update(Object notification) {

        NotificationType notificationType;
        Object info;

        if (notification instanceof MyNotification) {
            notificationType = ((MyNotification) notification).getType();
            info = ((MyNotification) notification).getInformation();
            if (notificationType.equals(NotificationType.MAP_DELETED)) {
                model.unsubscribe(this);
                MainFrame.getInstance().unsubscribe(this);
            }
            else if(notificationType.equals(NotificationType.ELEMENT_DELETED)){
                ElementPainter painterToRemove = null;
                for (ElementPainter ep :
                        painters) {
                    if (ep.getElement().equals(info)){
                        painterToRemove = ep;
                        break;
                    }
                }
                if(painterToRemove != null){
                    painters.remove(painterToRemove);
                    ((MapNode) (info)).unsubscribe(this);
                }
            }
            else if(notificationType.equals(NotificationType.ELEMENT_ADDED)){
                ElementPainter newPainter;
                if(info instanceof TermElement){
                    newPainter = new TermPainter(((Element) (info)));
                }
                else newPainter = new RelationPainter(((Element) (info)));
                painters.add(newPainter);
                ((MapNode) (info)).subscribe(this);
            }
        }
        this.repaint();
        SwingUtilities.updateComponentTreeUI(this);
    }

    public List<ElementPainter> getPainters() {
        return painters;
    }

    public MapSelectionModel getMapSelectionModel() {
        return mapSelectionModel;
    }

    public MindMap getModel() {
        return this.model;
    }

    public Rectangle2D getSelectionRec() {
        return selectionRec;
    }

    public void setSelectionRec(int x, int y, int w, int h) {
        selectionRec.setRect(x, y, w, h);
        this.repaint();
    }

    private void setUpTransformation() {

        affineTransform.scale(scaleFactor, scaleFactor);

        if(Math.abs(affineTransform.getScaleX()-pcScale) < 0.01){
            AffineTransform newTransform = new AffineTransform();
            newTransform.translate(affineTransform.getTranslateX(), affineTransform.getTranslateY());
            newTransform.scale(pcScale, pcScale);
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

    public void zoomIn() {
        scaleFactor = 1.05;

        if (affineTransform.getScaleX() >= 1.9) {
            AppCore.getInstance().getMsgGenerator().createMessage(Problem.ZOOM_IS_AT_MAX);
            return;
        }
        setUpTransformation();
    }

    public void zoomOut() {
        scaleFactor = 0.95238;

        if (affineTransform.getScaleX() <= 0.2 * pcScale) {
            AppCore.getInstance().getMsgGenerator().createMessage(Problem.ZOOM_IS_AT_MIN);
            return;
        }
        setUpTransformation();
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if (((JScrollBar) e.getSource()).getOrientation() == 0) {
            double prevX = affineTransform.getTranslateX() + (oldHscValue - e.getValue()) * step * (Math.pow(affineTransform.getScaleX(), 5));
            affineTransform.translate((oldHscValue - e.getValue()) * step * Math.pow(affineTransform.getScaleX(), 5), 0);
            affineTransform.translate((prevX - affineTransform.getTranslateX()), 0);
            oldHscValue = e.getValue();
        } else {
            double prevY = affineTransform.getTranslateY() + (oldVscValue - e.getValue()) * step * Math.pow(affineTransform.getScaleX(), 5);
            affineTransform.translate(0, (oldVscValue - e.getValue()) * step * Math.pow(affineTransform.getScaleX(), 5));
            affineTransform.translate(0, (prevY - affineTransform.getTranslateY()));
            oldVscValue = e.getValue();
        }

        if (MainFrame.getInstance().getProjectView().getCurrentMapScrollPane().getVerticalScrollBar().getValue() == 0 && MainFrame.getInstance().getProjectView().getCurrentMapScrollPane().getHorizontalScrollBar().getValue() == 0) {
            AffineTransform at = new AffineTransform();
            at.scale(affineTransform.getScaleX(), affineTransform.getScaleY());
            affineTransform.setTransform(at);
        }

        revalidate();
        repaint();
    }

    @Override
    public void subscribe(ISubscriber sub) {
        if(!subscribers.contains(sub))
            this.subscribers.add(sub);
    }

    @Override
    public void unsubscribe(ISubscriber sub) {
        if(subscribers.contains(sub))
            this.subscribers.remove(sub);
    }

    @Override
    public void notify(Object notification) {
        for (ISubscriber sub :
                subscribers) {
            if(sub != null)
                sub.update(notification);
        }
    }

    public Pair<Integer, Integer> getTranslate() {
        return new Pair<Integer, Integer>((int) (affineTransform.getTranslateX()), (int) (affineTransform.getTranslateY()));
    }

    public double getScale() {
        return affineTransform.getScaleX();
    }

    public double getPcScale() {
        return pcScale;
    }

    private Pair<Integer, Integer> getFPoints() {
        int x = Integer.MIN_VALUE;
        int y = Integer.MIN_VALUE;
        for (ElementPainter ep :
                painters) {
            if (ep instanceof TermPainter) {
                TermElement te = (TermElement) ep.getElement();
                if (te.getPosition().getFirst() > x)
                    x = te.getPosition().getFirst();
                if (te.getPosition().getSecond() > y)
                    y = te.getPosition().getSecond();
            }
        }
        return new Pair<>(x, y);
    }

    public int correctMouseX(int x){
        x -= getTranslate().getFirst()/pcScale;
        x *= getPcScale()/getScale();
        return x;
    }

    public int correctMouseY(int y){
        y -= getTranslate().getSecond()/pcScale;
        y *= getPcScale()/getScale();
        return y;
    }

    public void centralizeTerm(){
        if(mapSelectionModel.getSelected().size() != 1 || mapSelectionModel.getSelected().get(0) instanceof RelationElement){
            AppCore.getInstance().getMsgGenerator().createMessage(Problem.SELECT_EXACTLY_ONE_TERM_TO_CENTRALIZE);
            return;
        }
        TermElement centralElement = (TermElement) mapSelectionModel.getSelected().get(0);
        centralElement.setStroke(centralElement.getStroke()+5);
        centralElement.setPosition(this.getWidth(), this.getHeight());
        List<TermElement> toElements = new ArrayList<>();
        for (MapNode mapNode :
                this.model.getChildren()) {
            if(mapNode instanceof TermElement) continue;
            RelationElement relationElement = (RelationElement) mapNode;
            if(relationElement.getFromTerm() != centralElement) continue;
            toElements.add(relationElement.getToTerm());
        }
        for(int i=0; i<toElements.size(); i++){
            TermElement termElement = toElements.get(i);
            termElement.setPosition((int)(centralElement.getPosition().getFirst()+100*Math.sin(i*2*Math.PI/toElements.size())), (int) (centralElement.getPosition().getSecond()+100*Math.cos(i*2*Math.PI/toElements.size())));
        }

    }
}

