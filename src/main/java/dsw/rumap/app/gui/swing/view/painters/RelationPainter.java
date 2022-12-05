package dsw.rumap.app.gui.swing.view.painters;

import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.RelationElement;
import dsw.rumap.app.maprepository.implementation.elements.TmpRelationElement;

import java.awt.*;
import java.awt.geom.Line2D;

public class RelationPainter extends ElementPainter{


    public RelationPainter(Element element) {
        super(element);
        setBounds();
    }

    private void setBounds(){
        TmpRelationElement relationElement = (TmpRelationElement) element;
        shape = new Line2D.Float(relationElement.getStart().getFirst(), relationElement.getStart().getSecond(), relationElement.getEnd().getFirst(), relationElement.getEnd().getSecond());
    }

    @Override
    public void draw(Graphics2D g) {
        setBounds();
        g.setPaint(element.getColor());
        g.setStroke(new BasicStroke(element.getStroke()));
        g.draw(shape);
        g.setPaint(element.getColor());
        g.fill(shape);
    }
}
