package dsw.rumap.app.gui.swing.controller;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RenameAction extends AbstractRumapActions {

    public RenameAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/new.png"));
        putValue(NAME, "RenameNode");
        putValue(SHORT_DESCRIPTION, "RenameNode");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MapTreeNode parent;
        MapNode parentNode;

        if(MainFrame.getInstance().getMapTree().getSelectedNode() != null){
            MapTreeNode selected = (MapTreeNode) MainFrame.getInstance().getMapTree().getSelectedNode();
            if(selected.getMapNode() instanceof ProjectExplorer){
                 parentNode = null;
            }
            else{
                parent = (MapTreeNode) selected.getParent();
                parentNode = parent.getMapNode();
            }


            String name = JOptionPane.showInputDialog("Unesite ime");
            if(name != null && !name.isEmpty()){
                AppCore.getInstance().getMapRepository().ChangeName(name,selected.getMapNode(), parentNode);
            }

        }

    }
}
