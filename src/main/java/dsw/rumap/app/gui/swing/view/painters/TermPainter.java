package dsw.rumap.app.gui.swing.view.painters;

import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class TermPainter extends ElementPainter{

    private TermElement termElement;
    public TermPainter(Element element) {
        super(element);
        termElement = (TermElement) element;
        shape = new Ellipse2D.Float(termElement.getPosition().getFirst(), termElement.getPosition().getSecond(), termElement.getSize().getFirst(), termElement.getSize().getSecond());
    }

    @Override
    public void draw(Graphics2D g) {
        ((Ellipse2D)shape).setFrame(termElement.getPosition().getFirst(), termElement.getPosition().getSecond(), termElement.getSize().getFirst(), termElement.getSize().getSecond());

        if(MainFrame.getInstance().getProjectView().getCurrentMindMapView().getMapSelectionModel().isSelected(this.getElement())){
            g.setStroke(new BasicStroke(element.getStroke()+2));
            g.setPaint(Color.RED);
        }
        else {
            g.setPaint(Color.BLACK);
            g.setStroke(new BasicStroke(element.getStroke()));
        }
        g.draw(shape);
        g.setPaint(element.getColor());
        g.fill(shape);
        g.setPaint(Color.WHITE);
        g.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        g.drawString(element.getName(), ((TermElement)element).getPosition().getFirst()+((TermElement)element).getSize().getFirst()/2-element.getName().length()*3-6,
                ((TermElement)element).getPosition().getSecond()+((TermElement)element).getSize().getSecond()/2+5);
    }


}
