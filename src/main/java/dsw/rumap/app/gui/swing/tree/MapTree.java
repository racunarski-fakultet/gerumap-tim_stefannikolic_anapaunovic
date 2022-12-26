package dsw.rumap.app.gui.swing.tree;

import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.rumap.app.gui.swing.tree.view.MapTreeView;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;

public interface MapTree {
    public MapTreeView generateTree(ProjectExplorer projectExplorer);
    public void addChild(MapTreeNode parent, MapNode child);
    public MapTreeNode getSelectedNode();
    public void refresh();
    public void removeTreeNode(MapTreeNode mapTreeNode);
    public void loadProject(Project project);
}
