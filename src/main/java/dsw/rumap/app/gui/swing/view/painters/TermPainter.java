package dsw.rumap.app.gui.swing.view.painters;

import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class TermPainter extends ElementPainter{
    public TermPainter(Element element) {
        super(element);
        TermElement termElement = (TermElement) element;
        //shape = new Ellipse2D.Float(5,5,230,260);
        shape = new Ellipse2D.Float(termElement.getPosition().getFirst(), termElement.getPosition().getSecond(), termElement.getSize().getFirst(), termElement.getSize().getSecond());
    }


    @Override
    public void draw(Graphics2D g) {
        //g.setPaint(element.getColor());
        g.setPaint(Color.CYAN);
        g.setStroke(new BasicStroke(element.getStroke()));
        g.draw(shape);
        //g.setPaint(element.getColor());
        g.setPaint(Color.CYAN);
        //g.fill(shape);
    }
}
