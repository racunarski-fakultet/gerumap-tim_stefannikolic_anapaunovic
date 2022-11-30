package dsw.rumap.app.gui.swing.view.painters;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.RelationElement;

import java.awt.*;

public class RelationPainter extends ElementPainter{


    public RelationPainter(Element element) {
        super(element);
    }

    @Override
    public void draw(Graphics2D g) {

        g.setPaint(element.getColor());
        g.setStroke(new BasicStroke(element.getStroke()));
        g.draw(shape);
        g.setPaint(element.getColor());
        g.fill(shape);
    }
}
