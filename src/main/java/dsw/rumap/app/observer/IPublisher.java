package dsw.rumap.app.observer;


public interface IPublisher {

    public void subscribe(ISubscriber sub);
    public void unsubscribe(ISubscriber sub);
    public void notify(Object notification);

}
