package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.observer.notification.MyNotification;
import dsw.rumap.app.observer.notification.NotificationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Project extends MapNodeC {

    private String author;

    public Project(String name, MapNode parent) {
        super(name, parent);
        this.author = new String("[Insert author name]");
    }

    public Project(MapNode parent){
        this("Project" + ((MapNodeC)parent).makeNameForChild(), parent);
    }

    @Override
    public Integer makeNameForChild() {
        Integer nameNumber = this.getChildren().size() + 1;
        while (this.checkName("MindMap" + nameNumber) == false) {
            nameNumber++;
        }
        return nameNumber;
    }

    @Override
    public void add(MapNode child) {
        if(child != null && child instanceof MindMap &&
                !(this.getChildren().contains(child))){
            this.getChildren().add(child);
            this.notify(new MyNotification(NotificationType.MAP_ADDED,this.getChildren().size()-1));
        }

        return;
    }

    @Override
    public void delete(MapNode child) {
        if(child != null && child instanceof MindMap &&
                this.getChildren().contains(child)){
            this.notify(new MyNotification(NotificationType.MAP_DELETED,this.getChildren().indexOf(child)));
            this.getChildren().remove(child);

        }
    }
    
    public void setAuthor(String author) {
        this.author = author;
        this.notify(new MyNotification(NotificationType.UPDATE_AUTOR));
    }
    @Override
    public void setName(String name) {
        super.setName(name);
        this.notify(new MyNotification(NotificationType.UPDATE_NAME));
    }


}
