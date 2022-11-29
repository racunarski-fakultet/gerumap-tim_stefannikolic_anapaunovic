package dsw.rumap.app.gui.swing.view;

import com.sun.tools.javac.Main;

import javax.swing.*;

public class MenuToolbar extends JToolBar {

    public MenuToolbar(){
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getNewAction());
        add(MainFrame.getInstance().getActionManager().getRenameNode());
        add(MainFrame.getInstance().getActionManager().getRenameAutor());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        add(MainFrame.getInstance().getActionManager().getInfoAction());

    }
}
