package dsw.rumap.app.gui.swing.view.painters;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.RelationElement;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;

import java.awt.*;

public abstract class ElementPainter {

    protected Element element;
    protected Shape shape;

    public ElementPainter(Element element) {
        this.element = element;
    }

    public abstract void draw(Graphics2D g);
    public boolean elementAt(Point pos) { return shape.contains(pos); }
}
