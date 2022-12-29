package dsw.rumap.app.maprepository.composite;

import com.google.gson.annotations.Expose;
import dsw.rumap.app.observer.IPublisher;
import dsw.rumap.app.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public abstract class MapNode implements IPublisher {
    protected String type;
    private String name;
    private transient MapNode parent;

    protected transient List<ISubscriber> subscribers;

    public MapNode(String name, MapNode parent){
        this.name = name;
        this.parent = parent;
        this.subscribers = new ArrayList<>();
        type = "MapNode";
    }

    public MapNode(MapNode parent){}

    @Override
    public boolean equals(Object obj){
        if(obj instanceof MapNode){
            MapNode object = (MapNode) obj;
            return this.getName().equals(object.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public void subscribe(ISubscriber sub) {
        if(subscribers == null) return;
        if(!subscribers.contains(sub))
            this.subscribers.add(sub);

    }

    @Override
    public void unsubscribe(ISubscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notify(Object notification) {
        if(subscribers == null) return;
        if(subscribers.isEmpty())
            return;
        for (ISubscriber iSubscriber : subscribers) {
            iSubscriber.update(notification);
        }
    }



}
