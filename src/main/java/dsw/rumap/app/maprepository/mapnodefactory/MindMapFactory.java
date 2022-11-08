package dsw.rumap.app.maprepository.mapnodefactory;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;

public class MindMapFactory extends MapNodeFactory{

    @Override
    MapNode createChild(MapNodeC parent) {
        Integer nameNumber = (((Project) parent).getChildren().size()+1);
        while(parent.checkName("MindMap" + nameNumber) == false){
            nameNumber++;
        }
        MapNode newNode = new MindMap("MindMap" + nameNumber, parent);
        return newNode;
    }
}
