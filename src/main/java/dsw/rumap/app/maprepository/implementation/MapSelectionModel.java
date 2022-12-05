package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.observer.IPublisher;
import dsw.rumap.app.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class MapSelectionModel implements IPublisher {
    private List<Element> selected;
    protected List<ISubscriber> subscribers;

    public MapSelectionModel() {
        this.selected = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public void add(Element e){
        if(!selected.contains(e)){
            selected.add(e);
            this.notify(this);
        }
        else {
            selected.remove(e);
            this.notify(this);
        }

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
        if(subscribers.isEmpty())
            return;
        for (ISubscriber iSubscriber : subscribers) {
            iSubscriber.update(notification);
        }
    }
}
