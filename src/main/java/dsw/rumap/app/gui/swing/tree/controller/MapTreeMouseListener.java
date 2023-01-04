package dsw.rumap.app.gui.swing.tree.controller;

import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapTreeMouseListener implements MouseListener {



    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            if(MainFrame.getInstance().getMapTree().getSelectedNode() == null) return;
            MapTreeNode treeNode = MainFrame.getInstance().getMapTree().getSelectedNode();
            MapNode node = treeNode.getMapNode();

            if(node instanceof Project){
                MainFrame.getInstance().getProjectView().setModel((Project)node);
            }
            else if(node instanceof MindMap){
                MainFrame.getInstance().getProjectView().setModel((Project)node.getParent());
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
