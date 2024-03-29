package dsw.rumap.app.gui.swing.view.painters;

import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.observer.ISubscriber;
import lombok.Getter;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
@Getter
public abstract class ElementPainter {

    protected Element element;
    protected Shape shape;

    public ElementPainter(Element element) {
        this.element = element;
    }

    public abstract void draw(Graphics2D g);
    public boolean elementAt(Point pos) {
        //return shape.contains(pos);
        return shape.intersects(new Rectangle2D.Float((float) pos.getX()-2, (float) pos.getY()-2, 4, 4));
    }

}
