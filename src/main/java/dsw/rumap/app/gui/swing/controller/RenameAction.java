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
        putValue(SMALL_ICON, loadIcon("/images/rename.png"));
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MapTreeNode parent;
        MapNode parentNode;

        if(MainFrame.getInstance().getMapTree().getSelectedNode() != null){
            MapTreeNode selected = (MapTreeNode) MainFrame.getInstance().getMapTree().getSelectedNode();
            System.out.println(selected.getMapNode().getName());
            if(selected.getMapNode() instanceof ProjectExplorer){
                 parentNode = null;
            }
            else{
                parent = (MapTreeNode) selected.getParent();
                parentNode = parent.getMapNode();
            }

            String name = JOptionPane.showInputDialog("Unesite ime");
            if(name != null && !name.isEmpty()){
                if(AppCore.getInstance().getMapRepository().changeName(name,selected.getMapNode(), parentNode) == false)
                    AppCore.getInstance().getMsgGenerator().createMessage("NAME_ALREADY_EXISTS");
            }
            else {
                AppCore.getInstance().getMsgGenerator().createMessage("NAME_CANNOT_BE_EMPTY");
                return;
            }

            MainFrame.getInstance().getMapTree().refresh();
        }
        else AppCore.getInstance().getMsgGenerator().createMessage("NODE_IS_NOT_SELECTED");
        //return;
    }
}
