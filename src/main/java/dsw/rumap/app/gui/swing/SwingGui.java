package dsw.rumap.app.gui.swing;

import com.sun.tools.javac.Main;
import dsw.rumap.app.core.Gui;
import dsw.rumap.app.gui.swing.view.MainFrame;

public class SwingGui implements Gui {
    @Override
    public void start() {
        MainFrame.getInstance().setVisible(true);
    }

    @Override
    public void enableUndoAction(boolean bool) {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(bool);
    }

    @Override
    public void enableRedoAction(boolean bool) {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(bool);
    }
}
