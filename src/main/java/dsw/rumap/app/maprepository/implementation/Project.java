package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.observer.notification.MyNotification;
import dsw.rumap.app.observer.notification.NotificationType;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class Project extends MapNodeC {

    private String author;
    private String filePath;
    private boolean changed;

    public Project(String name, MapNode parent) {
        super(name, parent);
        this.author = new String("[Insert author name]");
        changed = true;
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
        }
    }

    @Override
    public void delete(MapNode child) {
        if(child instanceof MindMap &&
                this.getChildren().contains(child)){
            this.notify(new MyNotification(NotificationType.MAP_DELETED,this.getChildren().indexOf(child)));
            this.getChildren().remove(child);
            changed = true;
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
}
