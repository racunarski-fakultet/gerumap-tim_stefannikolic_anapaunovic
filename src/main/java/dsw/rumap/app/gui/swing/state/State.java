package dsw.rumap.app.gui.swing.state;

import dsw.rumap.app.gui.swing.view.MindMapView;

public interface State {

    void execute();
    void stateMousePressed(int x, int y, MindMapView mapView);
    void stateMouseReleased();
    void stateMouseDragged();
}
