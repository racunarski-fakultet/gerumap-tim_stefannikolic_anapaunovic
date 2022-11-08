package dsw.rumap.app.gui.swing.view;

import com.sun.tools.javac.Main;

import javax.swing.*;

public class MyToolbar extends JToolBar {

    public MyToolbar(){
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getNewAction());
        add(MainFrame.getInstance().getActionManager().getInfoAction());
        add(MainFrame.getInstance().getActionManager().getRenameAutor());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());

    }
}
