package dsw.rumap.app.core;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;

public interface MapRepository {
    ProjectExplorer getProjectExplorer();
    public void addChild(MapNode parent, MapNode child);
    public void removeChild(MapNode parent, MapNode child);
    public MapNode createChild(MapNode parent);
    public void setAutor(String autor, MapNode proj);

    public void ChangeName(String name, MapNode node, MapNode parent);
}
