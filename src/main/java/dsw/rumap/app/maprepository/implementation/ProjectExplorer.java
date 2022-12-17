package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.observer.ISubscriber;

import java.util.List;

public class ProjectExplorer extends MapNodeC {

    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public Integer makeNameForChild() {
        Integer nameNumber = this.getChildren().size() + 1;
        while (!this.checkName("Project" + nameNumber)) {
            nameNumber++;
        }
        return nameNumber;
    }

    @Override
    public void add(MapNode child) {
        if(child instanceof Project &&
                !(this.getChildren().contains(child))){
            this.getChildren().add(child);
            //this.notify(this);
        }
    }

    @Override
    public void delete(MapNode child) {
        if(child instanceof Project &&
                this.getChildren().contains(child)){
            this.getChildren().remove(child);
            this.notify(this);
        }
        this.notify(this);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

}
