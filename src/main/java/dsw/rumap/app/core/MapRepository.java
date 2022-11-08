package dsw.rumap.app.core;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;
import dsw.rumap.app.maprepository.mapnodefactory.MapNodeFactory;

public interface MapRepository {
    ProjectExplorer getProjectExplorer();
    public void addChild(MapNode parent, MapNode child);
    public void removeChild(MapNode parent, MapNode child);
    public void setAutor(String autor, MapNode proj);
    public void ChangeName(String name, MapNode node, MapNode parent);
    public MapNodeFactory getMapNodeFactory(MapNode parent);
}
