package dsw.rumap.app.gui.swing.controller;

import com.sun.tools.javac.Main;
import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class NewAction extends AbstractRumapActions{

    public NewAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/new.png"));
        putValue(NAME, "New");
        putValue(SHORT_DESCRIPTION, "New");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(MainFrame.getInstance().getMapTree().getSelectedNode() != null){
            MapTreeNode selected = (MapTreeNode) MainFrame.getInstance().getMapTree().getSelectedNode();
            MapNode child = AppCore.getInstance().getMapRepository().createChild(selected.getMapNode());
            MainFrame.getInstance().getMapTree().addChild(selected, child);

        }
        else AppCore.getInstance().getMsgGenerator().createMessage("NODE_IS_NOT_SELECTED");
        return;
    }
}
