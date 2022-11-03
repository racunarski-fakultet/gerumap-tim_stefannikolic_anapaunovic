package dsw.rumap.app.maprepository;

import dsw.rumap.app.core.MapRepository;
import dsw.rumap.app.maprepository.implementation.ProjectExp;

public class MapReposImpl implements MapRepository {

    private ProjectExp projectExp;

    public MapReposImpl(){
        projectExp = new ProjectExp("Project Expo");
    }

    @Override
    public ProjectExp getProjectExp() {
        return projectExp;
    }


}
