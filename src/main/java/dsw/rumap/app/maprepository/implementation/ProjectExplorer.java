package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;

import java.util.List;

public class ProjectExplorer extends MapNodeC {

    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void add(MapNode child) {
        if(child != null && child instanceof Project &&
                !(this.getChildren().contains(child)))
            this.getChildren().add(child);
        return;
    }

    @Override
    public void delete(MapNode child) {
        if(child != null && child instanceof Project &&
                !(this.getChildren().contains(child)))
            this.getChildren().remove(child);
        return;
    }
}
