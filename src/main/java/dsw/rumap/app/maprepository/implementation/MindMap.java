package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.observer.ISubscriber;

import javax.swing.*;
import java.util.List;

public class MindMap extends MapNodeC {

    private boolean template;

    public MindMap(String name, MapNode parent) {
        super(name, parent);
    }

    @Override
    public void add(MapNode child) {
        if(child != null && child instanceof Element &&
                !(this.getChildren().contains(child))){
            this.getChildren().add(child);
            this.notify(this);
        }

        return;
    }

    @Override
    public void delete(MapNode child) {
        if(child != null && child instanceof Element &&
                this.getChildren().contains(child)){
            this.getChildren().remove(child);
            this.notify(this);
        }

        return;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        this.notify(this);
    }

}
