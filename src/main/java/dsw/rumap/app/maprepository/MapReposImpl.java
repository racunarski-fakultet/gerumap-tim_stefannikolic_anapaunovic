package dsw.rumap.app.maprepository;

import dsw.rumap.app.core.MapRepository;
import dsw.rumap.app.maprepository.commands.CommandManager;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;
import dsw.rumap.app.maprepository.mapnodefactory.*;

public class MapReposImpl implements MapRepository {

    private FactoryUtil factoryUtil;
    private ProjectExplorer projectExplorer;
    private CommandManager commandManager;

    public MapReposImpl(){
        projectExplorer = new ProjectExplorer("My Project Explorer");
        factoryUtil = new FactoryUtil();
        commandManager = new CommandManager();
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public void addChild(MapNode parent, MapNode child) {
        if(parent instanceof MapNodeC) {
            ((MapNodeC) parent).add(child);
        }
    }

    @Override
    public void removeChild(MapNode parent, MapNode child) {
        if(parent instanceof MapNodeC)
            ((MapNodeC) parent).delete(child);

    }

    @Override
    public void setAuthor(String author, MapNode proj) {
        if(proj instanceof Project)
            ((Project)proj).setAuthor(author);
    }

    @Override
    public boolean changeName(String name, MapNode node, MapNode parent) {
        if(parent == null)
            node.setName(name);
        else if(parent instanceof MapNodeC){
            if(!((MapNodeC) parent).checkName(name)){
                return false;
            }
            else node.setName(name);
        }
        return true;
    }

    @Override
    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    @Override
    public MapNodeFactory getMapNodeFactory(MapNode parent){
        if(!(parent instanceof MapNodeC)) return null;
        else return factoryUtil.getMapNodeFactory(parent);
    }


}
