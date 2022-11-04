package dsw.rumap.app.gui.swing.tree;

import dsw.rumap.app.gui.swing.tree.model.MapTreeModel;
import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.rumap.app.gui.swing.tree.view.MapTreeView;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;

import javax.swing.*;
import java.util.Random;

public class MapTreeImpl implements MapTree {

    private MapTreeView mapTreeView;
    private MapTreeModel mapTreeModel;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeNode root = new MapTreeNode(projectExplorer);
        mapTreeModel = new MapTreeModel(root);
        mapTreeView = new MapTreeView(mapTreeModel);
        return mapTreeView;
    }

    @Override
    public void addChild(MapTreeNode parent) {
        if(parent == null || !(parent.getMapNode() instanceof MapNodeC))
            return;

        MapNode child = createChild(parent.getMapNode());
        parent.add(new MapTreeNode(child));
        ((MapNodeC) parent.getMapNode()).add(child);
        mapTreeView.expandPath(mapTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(mapTreeView);
    }

    private MapNode createChild(MapNode parent){
        if(parent instanceof ProjectExplorer)
            return new Project("Project" + new Random().nextInt(100), parent);

        else if(parent instanceof Project)
            return new MindMap("MindMap " + new Random().nextInt(100), parent);

        else if(parent instanceof MindMap)
            return new Element("Element " + new Random().nextInt(100), parent);

        return null;
    }

    @Override
    public MapTreeNode getSelectedNode() {
        return (MapTreeNode) mapTreeView.getLastSelectedPathComponent();
    }
}
