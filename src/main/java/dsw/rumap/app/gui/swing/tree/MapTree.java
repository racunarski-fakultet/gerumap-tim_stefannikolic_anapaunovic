package dsw.rumap.app.gui.swing.tree;

import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.rumap.app.gui.swing.tree.view.MapTreeView;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;

public interface MapTree {
    MapTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(MapTreeNode parent);
    MapTreeNode getSelectedNode();
}
