package dsw.rumap.app.gui.swing;

import dsw.rumap.app.core.Gui;
import dsw.rumap.app.gui.swing.view.MainFrame;

public class SwingGui implements Gui {
    @Override
    public void start() {
        MainFrame.getInstance().setVisible(true);
    }
}
