package dsw.rumap.app.gui.swing.view.painters;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class TermPainter extends ElementPainter{
    public TermPainter(Element element) {
        super(element);
    }


    @Override
    public void draw(Graphics2D g) {
        //g.setPaint(element.getColor());
        g.setPaint(Color.CYAN);
        g.setStroke(new BasicStroke(element.getStroke()));
        //g.draw(shape);
        g.draw(new Ellipse2D.Float(0, 0, 230, 230));
        //g.setPaint(element.getColor());
        g.setPaint(Color.CYAN);
        //g.fill(shape);
    }
}
