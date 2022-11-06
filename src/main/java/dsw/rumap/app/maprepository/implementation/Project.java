package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;

import java.util.List;

public class Project extends MapNodeC {

    public Project(String name, MapNode parent) {
        super(name, parent);
    }

    @Override
    public void add(MapNode child) {
        if(child != null && child instanceof MindMap &&
                !(this.getChildren().contains(child))){
            this.getChildren().add(child);
            this.notify(this);
        }

        return;
    }

    @Override
    public void delete(MapNode child) {
        if(child != null && child instanceof MindMap &&
                this.getChildren().contains(child)){
            this.getChildren().remove(child);
            this.notify(this);
        }

        return;
    }
}
