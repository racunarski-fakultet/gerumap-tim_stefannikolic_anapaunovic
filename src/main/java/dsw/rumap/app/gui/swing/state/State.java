package dsw.rumap.app.gui.swing.state;

public interface State {

    void execute();
    void stateMousePressed();
    void stateMouseReleased();
    void stateMouseDragged();
}
