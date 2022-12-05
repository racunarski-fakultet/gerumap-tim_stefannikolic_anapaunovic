package dsw.rumap.app.gui.swing.view.painters;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.RelationElement;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;
import dsw.rumap.app.observer.IPublisher;
import dsw.rumap.app.observer.ISubscriber;
import lombok.Getter;

import javax.management.Notification;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Getter
public abstract class ElementPainter {

    protected Element element;
    protected Shape shape;
    protected List<ISubscriber> subscribers;

    public ElementPainter(Element element) {
        this.element = element;
        subscribers = new ArrayList<>();
    }

    public abstract void draw(Graphics2D g);
    public boolean elementAt(Point pos) { return shape.contains(pos); }

}
