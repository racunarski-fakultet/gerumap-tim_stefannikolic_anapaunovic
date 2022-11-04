package dsw.rumap.app.maprepository;

import dsw.rumap.app.core.MapRepository;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;

public class MapReposImpl implements MapRepository {

    private ProjectExplorer projectExplorer;

    public MapReposImpl(){
        projectExplorer = new ProjectExplorer("My Project Explorer");
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public void addChild(MapNode child) {
        // Treba da prima i parent??
        //this.projectExplorer.add(child);
    }


}
