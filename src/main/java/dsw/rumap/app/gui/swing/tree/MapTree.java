package dsw.rumap.app.gui.swing.tree;

import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.rumap.app.gui.swing.tree.view.MapTreeView;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;

public interface MapTree {
    MapTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(MapTreeNode parent, MapNode child);
    MapTreeNode getSelectedNode();
    void refresh();
    void removeTreeNode(MapTreeNode mapTreeNode);
    void loadProject(Project project);
    void setSelectedNode(MindMap mindMap);
}
