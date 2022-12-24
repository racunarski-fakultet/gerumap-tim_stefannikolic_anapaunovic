package dsw.rumap.app.gui.swing.view;

import javax.swing.*;

public class MindMapToolBar extends JToolBar {
    public MindMapToolBar() {
        super(VERTICAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getAddTermAction());
        //addSeparator();
        add(MainFrame.getInstance().getActionManager().getAddRelationAction());
        //addSeparator();
        add(MainFrame.getInstance().getActionManager().getSelectElementAction());
        //addSeparator();
        add(MainFrame.getInstance().getActionManager().getMoveElementAction());
        //addSeparator();
        add(MainFrame.getInstance().getActionManager().getDeleteElementAction());
        //addSeparator();
        add(MainFrame.getInstance().getActionManager().getZoomInAction());
        //addSeparator();
        add(MainFrame.getInstance().getActionManager().getZoomOutAction());
        //addSeparator();
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        //addSeparator();
        add(MainFrame.getInstance().getActionManager().getRedoAction());
    }
}
