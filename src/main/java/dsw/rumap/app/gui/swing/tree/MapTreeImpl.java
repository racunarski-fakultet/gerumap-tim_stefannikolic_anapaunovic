package dsw.rumap.app.gui.swing.tree;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.tree.model.MapTreeModel;
import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.rumap.app.gui.swing.tree.view.MapTreeView;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;
import lombok.Getter;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.DoubleSummaryStatistics;
import java.util.Random;
@Getter
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
    public void addChild(MapTreeNode parent, MapNode child) {
        if(parent == null || !(parent.getMapNode() instanceof MapNodeC))
            return;

        parent.add(new MapTreeNode(child));
        AppCore.getInstance().getMapRepository().addChild(parent.getMapNode(), child);
        this.refresh();
    }


    @Override
    public MapTreeNode getSelectedNode() {
        return (MapTreeNode) mapTreeView.getLastSelectedPathComponent();
    }

    @Override
    public void refresh() {
        SwingUtilities.updateComponentTreeUI(mapTreeView);
    }

    @Override
    public void removeTreeNode(MapTreeNode mapTreeNode) {
        mapTreeNode.removeFromParent();
        this.refresh();
    }


}
