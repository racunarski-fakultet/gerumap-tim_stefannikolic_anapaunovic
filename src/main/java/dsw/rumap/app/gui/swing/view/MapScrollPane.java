package dsw.rumap.app.gui.swing.view;

import dsw.rumap.app.observer.ISubscriber;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

@Getter
public class MapScrollPane extends JPanel implements ISubscriber, ComponentListener {

    private MindMapView mindMapView;
    private JScrollBar horizontalScrollBar;
    private JScrollBar verticalScrollBar;

    public MapScrollPane(MindMapView mindMapView) {
        super();
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        this.add(mindMapView, BorderLayout.CENTER);
        this.mindMapView = mindMapView;
        horizontalScrollBar = new JScrollBar(0);
        verticalScrollBar = new JScrollBar(1);
        horizontalScrollBar.addAdjustmentListener(mindMapView);
        verticalScrollBar.addAdjustmentListener(mindMapView);
        this.add(verticalScrollBar, BorderLayout.EAST);
        this.add(horizontalScrollBar, BorderLayout.SOUTH);
        horizontalScrollBar.setVisible(true);
        verticalScrollBar.setVisible(true);
        mindMapView.subscribe(this);

    }

    @Override
    public void update(Object notification) {
    }


    @Override
    public void componentResized(ComponentEvent e) {
        mindMapView.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        mindMapView.repaint();
    }

    @Override
    public void componentShown(ComponentEvent e) {
        mindMapView.repaint();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        mindMapView.repaint();
    }
}
