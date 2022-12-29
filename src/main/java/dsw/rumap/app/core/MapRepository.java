package dsw.rumap.app.core;

import dsw.rumap.app.maprepository.commands.CommandManager;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;
import dsw.rumap.app.maprepository.mapnodefactory.MapNodeFactory;

public interface MapRepository {
    ProjectExplorer getProjectExplorer();
    void addChild(MapNode parent, MapNode child);
    void removeChild(MapNode parent, MapNode child);
    void setAuthor(String author, MapNode proj);
    MapNodeFactory getMapNodeFactory(MapNode parent);
    boolean changeName(String name, MapNode node, MapNode parent);
    void loadProject(Project project);
}
