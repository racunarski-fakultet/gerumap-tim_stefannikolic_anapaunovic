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

    public MapNodeC(String name, MapNode parent, List<MapNode> children) {
        super(name, parent);
        this.children = children;
    }

    public MapNode getChildByName(MapNode name){
        for (MapNode child : this.getChildren()) {
            if(name.equals(child.getName()))
                return child;
        }
        return null;
    }

    public abstract void add(MapNode child);

    public abstract void delete(MapNode child);

    public abstract boolean CheckName(String name);


}
