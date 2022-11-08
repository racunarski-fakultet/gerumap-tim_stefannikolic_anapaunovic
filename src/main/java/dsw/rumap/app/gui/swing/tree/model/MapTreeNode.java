package dsw.rumap.app.gui.swing.tree.model;

import dsw.rumap.app.gui.swing.tree.MapTreeImpl;
import dsw.rumap.app.gui.swing.tree.view.MapTreeView;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Getter
@Setter
public class MapTreeNode extends DefaultMutableTreeNode {

    public MapNode mapNode;

    public MapTreeNode(MapNode mapNode) {
        this.mapNode = mapNode;
    }

    @Override
    public String toString() {
        return this.mapNode.getName();
    }

    public void setName(String name){
        this.mapNode.setName(name);
    }

    @Override
    public void add(MutableTreeNode newChild) {
        super.add(newChild);
        MapTreeView mapTreeView = ((MapTreeImpl) MainFrame.getInstance().getMapTree()).getMapTreeView();
        mapTreeView.expandPath(mapTreeView.getSelectionPath());
    }

    @Override
    public void removeFromParent() {
        super.removeFromParent();
    }

}
