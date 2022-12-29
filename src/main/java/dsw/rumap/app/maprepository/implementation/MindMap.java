package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.maprepository.commands.CommandManager;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.observer.notification.MyNotification;
import dsw.rumap.app.observer.notification.NotificationType;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class MindMap extends MapNodeC {

    private boolean template;
    private Integer key;
    private transient CommandManager commandManager;

    public MindMap(String name, MapNode parent) {
        super(name, parent);
        key = new Random().nextInt();
        commandManager = new CommandManager();
        type = "MindMap";
    }

    public MindMap(MapNode parent){
        this("MindMap" + ((MapNodeC)parent).makeNameForChild(), parent);
        key = new Random().nextInt();
    }

    @Override
    public Integer makeNameForChild() {
        Integer nameNumber = this.getChildren().size() + 1;
        while (!this.checkName("Element" + nameNumber)) {
            nameNumber++;
        }
        return nameNumber;
    }

    @Override
    public void add(MapNode child) {
        if(child instanceof Element &&
                !(this.getChildren().contains(child))){
            this.getChildren().add(child);
            this.notify(new MyNotification(NotificationType.ELEMENT_ADDED, child));
        }
    }

    @Override
    public void delete(MapNode child) {
        if(child instanceof Element &&
                this.getChildren().contains(child)){
            this.notify(new MyNotification(NotificationType.ELEMENT_DELETED, child));
            this.getChildren().remove(child);
        }
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        this.notify(new MyNotification(NotificationType.UPDATE_MAP_NAME,((MapNodeC)this.getParent()).getChildren().indexOf(this)));
    }
    public Integer getKey() {
        return key;
    }
}
