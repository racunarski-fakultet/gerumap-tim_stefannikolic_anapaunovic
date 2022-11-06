package dsw.rumap.app.maprepository.composite;

import dsw.rumap.app.observer.IPublisher;
import dsw.rumap.app.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class MapNode implements IPublisher {
    private String name;
    private MapNode parent;

    protected transient List<ISubscriber> subscribers;

    public MapNode(String name, MapNode parent){
        this.name = name;
        this.parent = parent;
        this.subscribers=new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj){
        if(obj != null && obj instanceof MapNode){
            MapNode object = (MapNode) obj;
            return this.getName().equals(object.getName());
        }
        return false;
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(!subscribers.contains(sub))
            this.subscribers.add(sub);

    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(subscribers.contains(sub))subscribers.remove(sub);
    }


    @Override
    public void notify(Object notification) {
        for (ISubscriber iSubscriber : subscribers) {
            iSubscriber.update(notification);
        }
    }

}
