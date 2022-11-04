package dsw.rumap.app.gui.swing.tree.model;

import dsw.rumap.app.maprepository.composite.MapNode;
import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.DefaultMutableTreeNode;

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
}
