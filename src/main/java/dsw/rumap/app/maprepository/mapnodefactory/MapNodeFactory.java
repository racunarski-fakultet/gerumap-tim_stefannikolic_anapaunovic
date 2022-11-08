package dsw.rumap.app.maprepository.mapnodefactory;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;

public abstract class MapNodeFactory {
    public MapNode orderChild(MapNode parent){
        MapNode newChild = this.createChild((MapNodeC) parent);
        return newChild;
    }

    abstract MapNode createChild(MapNodeC parent);
}
