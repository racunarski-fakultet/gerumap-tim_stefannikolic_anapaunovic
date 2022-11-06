package dsw.rumap.app.observer;


public interface IPublisher {

    public void addSubscriber(ISubscriber sub);
    public void removeSubscriber(ISubscriber sub);
    public void notify(Object notification);

}
