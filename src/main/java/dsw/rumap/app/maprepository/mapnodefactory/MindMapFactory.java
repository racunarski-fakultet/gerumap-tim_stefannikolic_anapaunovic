package dsw.rumap.app.maprepository.mapnodefactory;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;

public class MindMapFactory extends MapNodeFactory{

    @Override
    MapNode createChild(MapNodeC parent) {
        MapNode newNode = new MindMap(parent);
        return newNode;
    }
}
