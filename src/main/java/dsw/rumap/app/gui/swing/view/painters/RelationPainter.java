package dsw.rumap.app.gui.swing.view.painters;


import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.RelationElement;

import java.awt.*;
import java.awt.geom.Line2D;

public class RelationPainter extends ElementPainter{

    private RelationElement relationElement;

    public RelationPainter(Element element) {
        super(element);
        relationElement = (RelationElement) element;
        setBounds();
    }

    private void setBounds(){
        shape = new Line2D.Float(relationElement.getStart().getFirst(), relationElement.getStart().getSecond(), relationElement.getEnd().getFirst(), relationElement.getEnd().getSecond());
    }

    @Override
    public void draw(Graphics2D g) {

        if(relationElement.getToTerm() == null)
            setBounds();
        else {
            relationElement.getStart().setFirst(relationElement.getFromTerm().getPosition().getFirst() + relationElement.getFromTerm().getSize().getFirst()/2);
            relationElement.getStart().setSecond(relationElement.getFromTerm().getPosition().getSecond()+15);
            relationElement.getEnd().setFirst(relationElement.getToTerm().getPosition().getFirst() + relationElement.getToTerm().getSize().getFirst()/2);
            relationElement.getEnd().setSecond(relationElement.getToTerm().getPosition().getSecond()+15);
        }

        setBounds();
        g.setPaint(element.getColor());
        g.setStroke(new BasicStroke(element.getStroke()));
        g.draw(shape);
        g.setPaint(element.getColor());
        g.fill(shape);
    }
}
