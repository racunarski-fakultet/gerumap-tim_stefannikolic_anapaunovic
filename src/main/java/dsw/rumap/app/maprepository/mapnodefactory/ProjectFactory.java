package dsw.rumap.app.maprepository.mapnodefactory;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;

public class ProjectFactory extends MapNodeFactory{
    @Override
    MapNode createChild(MapNodeC parent) {
        MapNode newNode = new Project(parent);
        return newNode;
    }
}
