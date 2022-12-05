package dsw.rumap.app.maprepository.mapnodefactory;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;
import lombok.Getter;

public class FactoryUtil {
    private static MapNodeFactory mindMapFactory = new MindMapFactory();
    private static MapNodeFactory projectFactory = new ProjectFactory();

    public MapNodeFactory getMapNodeFactory(MapNode parent){
        if(parent instanceof ProjectExplorer)
            return projectFactory;

        else return mindMapFactory;
    }


}
