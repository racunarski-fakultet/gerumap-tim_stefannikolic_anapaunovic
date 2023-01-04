package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.observer.ISubscriber;
import dsw.rumap.app.observer.notification.MyNotification;
import dsw.rumap.app.observer.notification.NotificationType;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;

@Getter
@Setter
public class Project extends MapNodeC implements ISubscriber {

    private String author;
    private String filePath;
    private boolean changed;

    public Project(String name, MapNode parent) {
        super(name, parent);
        this.author = new String("[Insert author name]");
        changed = true;
        type = "Project";
    }

    public Project(MapNode parent){
        this("Project" + ((MapNodeC)parent).makeNameForChild(), parent);
    }

    @Override
    public Integer makeNameForChild() {
        Integer nameNumber = this.getChildren().size() + 1;
        while (!this.checkName("MindMap" + nameNumber)) {
            nameNumber++;
        }
        return nameNumber;
    }

    @Override
    public void add(MapNode child) {
        if(child instanceof MindMap &&
                !(this.getChildren().contains(child))){
            this.getChildren().add(child);
            this.notify(new MyNotification(NotificationType.MAP_ADDED,this.getChildren().size()-1));
            changed = true;
            child.subscribe(this);
            for (MapNode element :
                    ((MindMap) child).getChildren()) {
                element.subscribe(this);
            }
        }
    }

    @Override
    public void delete(MapNode child) {
        if(child instanceof MindMap &&
                this.getChildren().contains(child)){
            this.notify(new MyNotification(NotificationType.MAP_DELETED,this.getChildren().indexOf(child)));
            changed = true;
            child.unsubscribe(this);
            for (MapNode element :
                    ((MindMap) child).getChildren()) {
                element.unsubscribe(this);
            }
            this.getChildren().remove(child);
        }
    }
    
    public void setAuthor(String author) {
        this.author = author;
        this.notify(new MyNotification(NotificationType.UPDATE_AUTOR));
        changed = true;
    }
    @Override
    public void setName(String name) {
        super.setName(name);
        this.notify(new MyNotification(NotificationType.UPDATE_NAME));
        changed = true;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
        changed = true;
    }

    @Override
    public void update(Object notification) {
        this.changed = true;
        if(notification instanceof MyNotification &&
                ((MyNotification) notification).getType() == NotificationType.ELEMENT_ADDED)
            ((MapNode)((MyNotification) notification).getInformation()).subscribe(this);
    }

    public void setUpLoadedProject(){
        for (MapNode child :
                this.getChildren()) {
            child.subscribe(this);
            for (MapNode element :
                    ((MindMap) child).getChildren()) {
                element.subscribe(this);
            }
        }
        this.subscribers = new ArrayList<>();
    }
}
