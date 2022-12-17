package dsw.rumap.app.gui.swing.tree.controller;

import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import org.w3c.dom.events.MouseEvent;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseListener;

public class MapTreeSelectionListener implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath treepath = e.getPath();
        MapTreeNode treeNodeSelected = (MapTreeNode) treepath.getLastPathComponent();
        //System.out.println("Selected node: " + treeNodeSelected);
        //System.out.println("Path to selected node: " + treepath);


    }
}
