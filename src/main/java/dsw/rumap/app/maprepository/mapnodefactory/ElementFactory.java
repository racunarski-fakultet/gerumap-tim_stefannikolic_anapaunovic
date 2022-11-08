package dsw.rumap.app.maprepository.mapnodefactory;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;

public class ElementFactory extends MapNodeFactory{
    @Override
    MapNode createChild(MapNodeC parent) {
        Integer nameNumber = (((MindMap) parent).getChildren().size()+1);
        while(parent.checkName("MindMap" + nameNumber) == false){
            nameNumber++;
        }
        MapNode newNode = new Element("Element" + nameNumber, parent);
        return newNode;
    }
}
