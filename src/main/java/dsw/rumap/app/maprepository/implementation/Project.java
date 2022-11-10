package dsw.rumap.app.maprepository.implementation;

import com.sun.tools.javac.Main;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Project extends MapNodeC implements ISubscriber {

    private String autor;

    public Project(String name, MapNode parent) {
        super(name, parent);
        this.autor = new String("[Unesite naziv autora]");
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
            this.notify(this);
            child.addSubscriber(this);
        }

        return;
    }

    @Override
    public void delete(MapNode child) {
        if(child != null && child instanceof MindMap &&
                this.getChildren().contains(child)){
            this.getChildren().remove(child);
        }
        this.notify(this);
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

    @Override
    public void update(Object notification) {
        this.notify(this);
    }

}
