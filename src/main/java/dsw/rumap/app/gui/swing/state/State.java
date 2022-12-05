package dsw.rumap.app.gui.swing.state;

import dsw.rumap.app.gui.swing.view.MindMapView;

public interface State {

    void execute();
    void stateMousePressed(int x, int y, MindMapView mindMapView);
    void stateMouseDragged(int x, int y, MindMapView mindMapView);
    void stateMouseReleased(int x, int y, MindMapView mindMapView);

}
