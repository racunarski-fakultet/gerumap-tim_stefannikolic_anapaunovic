package dsw.rumap.app.gui.swing.controller;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;
import dsw.rumap.app.msggenerator.Problem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractRumapActions{

    public DeleteAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getMapTree().getSelectedNode() != null) {

            MapTreeNode selected = MainFrame.getInstance().getMapTree().getSelectedNode();

            if(selected.getMapNode() instanceof ProjectExplorer){
                AppCore.getInstance().getMsgGenerator().createMessage(Problem.NODE_CANNOT_BE_DELETED);
                return;
            }

            MapTreeNode parent = (MapTreeNode) selected.getParent();
            AppCore.getInstance().getMapRepository().removeChild(parent.getMapNode(), selected.getMapNode());
            MainFrame.getInstance().getMapTree().removeTreeNode(selected);
        }
        else
            AppCore.getInstance().getMsgGenerator().createMessage(Problem.NODE_IS_NOT_SELECTED);

    }
}
