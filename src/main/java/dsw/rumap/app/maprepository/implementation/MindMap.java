package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.observer.notification.MyNotification;
import dsw.rumap.app.observer.notification.NotificationType;

import javax.swing.*;
import java.util.List;

public class MindMap extends MapNodeC {

    private boolean template;

    public MindMap(String name, MapNode parent) {
        super(name, parent);
    }

    public MindMap(MapNode parent){
        this("MindMap" + ((MapNodeC)parent).makeNameForChild(), parent);
    }

    @Override
    public Integer makeNameForChild() {
        Integer nameNumber = this.getChildren().size() + 1;
        while (this.checkName("Element" + nameNumber) == false) {
            nameNumber++;
        }
        return nameNumber;
    }

    @Override
    public void add(MapNode child) {
        if(child != null && child instanceof Element &&
                !(this.getChildren().contains(child))){
            this.getChildren().add(child);
            this.notify(this);
        }
    }

    @Override
    public void delete(MapNode child) {
        if(child != null && child instanceof Element &&
                this.getChildren().contains(child)){
            this.getChildren().remove(child);
            this.notify(this);
        }
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        this.notify(new MyNotification(NotificationType.UPDATE_MAP_NAME,((MapNodeC)this.getParent()).getChildren().indexOf(this)));
    }
}
