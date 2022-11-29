package dsw.rumap.app.gui.swing.view;

import javax.swing.*;

public class MindMapToolBar extends JToolBar {
    public MindMapToolBar() {
        super(VERTICAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getSelectElementAction());
        add(MainFrame.getInstance().getActionManager().getAddTermAction());
        add(MainFrame.getInstance().getActionManager().getAddRelationAction());
        add(MainFrame.getInstance().getActionManager().getMoveElementAction());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
    }
}
