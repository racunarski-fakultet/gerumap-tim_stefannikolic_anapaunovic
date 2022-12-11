package dsw.rumap.app.gui.swing.view;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class MapScroll extends JScrollPane {

    private MindMapView map;

    public MapScroll(MindMapView map) {
        super(map);
        this.map = map;
    }

    public MindMapView getMap() {
        return map;
    }
}
