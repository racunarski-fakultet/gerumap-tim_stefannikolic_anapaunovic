package dsw.rumap.app.maprepository.implementation.elements;

import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.observer.IPublisher;
import dsw.rumap.app.observer.ISubscriber;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class MapSelectionModel implements IPublisher {

    private List<Element> selected;
    private List<ISubscriber> subscribers;

    public MapSelectionModel() {
        this.selected = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public void select(Element element){
        if(selected.contains(element)) return;
        selected.add(element);
        this.notify(null);
    }

    public void unselect(Element element){
        if(selected.contains(element)) {
            selected.remove(element);
            this.notify(null);
        }
    }

    public boolean isSelected(Element element){
        if(selected.contains(element))
            return true;
        return false;
    }

    public List<Element> getSelected() {
        return selected;
    }

    public void clear(){
        selected.clear();
        this.notify(this);
    }

    @Override
    public void subscribe(ISubscriber sub) {
        this.subscribers.add(sub);
    }

    @Override
    public void unsubscribe(ISubscriber sub) {
        this.subscribers.remove(sub);
    }

    @Override
    public void notify(Object notification) {
        for (ISubscriber sub :
                subscribers) {
            sub.update(notification);
        }
    }
}
