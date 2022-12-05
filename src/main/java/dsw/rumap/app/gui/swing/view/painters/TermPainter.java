package dsw.rumap.app.gui.swing.view.painters;

import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class TermPainter extends ElementPainter{
    public TermPainter(Element element, MindMapView map) {

        super(element,map);
        shape = new Ellipse2D.Float(((TermElement)element).getPosition().getFist(),((TermElement)element).getPosition().getSecond(),30,30);
        map.addPainter(this);
        this.notify(this);

    }

    @Override
    public void draw(Graphics2D g) {
        //g.setPaint(element.getColor());
        g.setPaint(Color.CYAN);
        //g.setStroke(new BasicStroke(element.getStroke()));
        g.setStroke(new BasicStroke());
        //g.draw(shape);
        g.draw(shape);
        //g.setPaint(element.getColor());
        //g.setPaint(Color.CYAN);
        //g.fill(shape);
    }


}
