package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Project extends MapNodeC {

    private String autor;

    public Project(String name, MapNode parent) {
        super(name, parent);
        this.autor = new String("Unesite naziv autora");
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


    public void setAutor(String autor) {
        this.autor = autor;
        this.notify(this);
    }
    @Override
    public void setName(String name) {
        super.setName(name);
        this.notify(this);
    }
}
