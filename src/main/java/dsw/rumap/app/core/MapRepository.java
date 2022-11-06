package dsw.rumap.app.core;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;

public interface MapRepository {
    ProjectExplorer getProjectExplorer();
    public void addChild(MapNode parent, MapNode child);
    public void removeChild(MapNode parent, MapNode child);
    public MapNode createChild(MapNode parent);
}
