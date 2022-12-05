package dsw.rumap.app.gui.swing.view.painters;

import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.RelationElement;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;
import dsw.rumap.app.observer.IPublisher;
import dsw.rumap.app.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public abstract class ElementPainter implements IPublisher {

    protected Element element;
    protected Shape shape;
    protected List<ISubscriber> subscribers;
    protected MindMapView map;

    public ElementPainter(Element element, MindMapView map) {
        this.element = element;
        this.subscribers = new ArrayList<>();
        this.map = map;

    }

    public abstract void draw(Graphics2D g);
    public boolean elementAt(Point pos) { return shape.contains(pos); }


    @Override
    public void addSubscriber(ISubscriber sub) {
        if(!subscribers.contains(sub))
            this.subscribers.add(sub);

    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(subscribers.contains(sub))subscribers.remove(sub);
    }


    @Override
    public void notify(Object notification) {
        if(subscribers.isEmpty())
            return;
        for (ISubscriber iSubscriber : subscribers) {
            iSubscriber.update(notification);
        }
    }

}
