package dsw.rumap.app.gui.swing.view;

import javax.swing.*;

public class MyToolbar extends JToolBar {

    public MyToolbar(){
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getRenameAction());
        add(MainFrame.getInstance().getActionManager().getNewProjectAction());

    }
}
