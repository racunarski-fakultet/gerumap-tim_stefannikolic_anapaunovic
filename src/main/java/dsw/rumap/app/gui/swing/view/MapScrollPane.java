package dsw.rumap.app.gui.swing.view;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
@Getter
public class MapScrollPane extends JScrollPane {

    private MindMapView mindMapView;

    public MapScrollPane(MindMapView mindMapView) {
        super(mindMapView);
        this.mindMapView = mindMapView;
        this.verticalScrollBar.addAdjustmentListener(mindMapView);
        this.horizontalScrollBar.addAdjustmentListener(mindMapView);
    }
}
