package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;

import java.util.List;

public class Project extends MapNodeC {

    public Project(String name, MapNode parent) {
        super(name, parent);
    }

    @Override
    protected void add(MapNode child) {
        if(child instanceof MindMap &&
                !(this.getChildren().contains(child)))
            this.getChildren().add(child);
        return;
    }

    @Override
    protected void delete(MapNode child) {
        if(child instanceof MindMap &&
                this.getChildren().contains(child))
            this.getChildren().remove(child);
        return;
    }
}
