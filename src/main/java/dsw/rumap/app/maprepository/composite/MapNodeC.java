package dsw.rumap.app.maprepository.composite;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public abstract class MapNodeC extends MapNode{
    private List<MapNode> children;

    public MapNodeC(String name, MapNode parent) {
        super(name, parent);
        this.children = new ArrayList<MapNode>();
    }

    public MapNodeC(MapNode parent){
        super(parent);
    }

    public MapNodeC(String name, MapNode parent, List<MapNode> children) {
        super(name, parent);
        this.children = children;
    }

    public abstract Integer makeNameForChild();

    public MapNode getChildByName(MapNode name){
        for (MapNode child : this.getChildren()) {
            if(name.equals(child.getName()))
                return child;
        }
        return null;
    }

    public boolean checkName(String name) {
        for (MapNode n: this.getChildren()){
            if(n.getName().equals(name))
                return false;
        }
        return true;
    }

    public abstract void add(MapNode child);

    public abstract void delete(MapNode child);

}
