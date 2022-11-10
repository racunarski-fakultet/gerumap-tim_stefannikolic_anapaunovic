package dsw.rumap.app.maprepository;

import dsw.rumap.app.core.MapRepository;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;
import dsw.rumap.app.maprepository.mapnodefactory.ElementFactory;
import dsw.rumap.app.maprepository.mapnodefactory.MapNodeFactory;
import dsw.rumap.app.maprepository.mapnodefactory.MindMapFactory;
import dsw.rumap.app.maprepository.mapnodefactory.ProjectFactory;
import lombok.Getter;

public class MapReposImpl implements MapRepository {

    private MapNodeFactory mapNodeFactory;
    private ProjectExplorer projectExplorer;

    public MapReposImpl(){
        projectExplorer = new ProjectExplorer("My Project Explorer");
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public void addChild(MapNode parent, MapNode child) {
        if(parent instanceof MapNodeC)
            ((MapNodeC) parent).add(child);
    }

    @Override
    public void removeChild(MapNode parent, MapNode child) {
        if(parent instanceof MapNodeC)
            ((MapNodeC) parent).delete(child);

    }

    @Override
    public void setAutor(String autor, MapNode proj) {
        if(proj instanceof Project)
            ((Project)proj).setAutor(autor);
    }

    @Override
    public boolean changeName(String name, MapNode node, MapNode parent) {
        if(parent == null)
            node.setName(name);
        else if(parent instanceof MapNodeC){
            if(((MapNodeC) parent).checkName(name) == true){
                node.setName(name);
            }
            else return false;
        }
        return true;
    }


    @Override
    public MapNodeFactory getMapNodeFactory(MapNode parent){
        if(!(parent instanceof MapNodeC)) return null;
        if(parent instanceof ProjectExplorer)
            return new ProjectFactory();

        else if(parent instanceof Project)
            return new MindMapFactory();

        else if(parent instanceof MindMap)
            return new ElementFactory();
        return null;
    }


}
