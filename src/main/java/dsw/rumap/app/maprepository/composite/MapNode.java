package dsw.rumap.app.maprepository.composite;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MapNode {
    private String name;
    private MapNode parent;

    public MapNode(String name, MapNode parent){
        this.name = name;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj){
        if(obj != null && obj instanceof MapNode){
            MapNode object = (MapNode) obj;
            return this.getName().equals(object.getName());
        }
        return false;
    }
}
