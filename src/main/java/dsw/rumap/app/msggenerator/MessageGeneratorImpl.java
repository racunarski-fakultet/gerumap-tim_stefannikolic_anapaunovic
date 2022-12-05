package dsw.rumap.app.msggenerator;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.core.MessageGenerator;
import dsw.rumap.app.observer.ISubscriber;

import javax.swing.*;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorImpl implements MessageGenerator {

    protected List<ISubscriber> subscribers;
    private Message message;

    public MessageGeneratorImpl(){
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void createMessage(Problem event) {


        if(event.equals(Problem.NODE_CANNOT_BE_DELETED))
            message = new Message(MessageType.ERROR, event);

        else if(event.equals(Problem.NODE_IS_NOT_SELECTED))
            message = new Message(MessageType.ERROR, event);

        else if(event.equals(Problem.NAME_CANNOT_BE_EMPTY))
            message = new Message(MessageType.WARNING, event);

        else if(event.equals(Problem.NAME_ALREADY_EXISTS))
            message = new Message(MessageType.INFORMATION, event);

        else if(event.equals(Problem.NODE_CANNOT_HAVE_CHILDREN))
            message = new Message(MessageType.ERROR,event);

        else if(event.equals(Problem.SELECTED_NODE_IS_NOT_PROJECT))
            message = new Message(MessageType.ERROR,event);

        this.notify(message);
    }

    @Override
    public void subscribe(ISubscriber sub) {
        if(!subscribers.contains(sub))
            this.subscribers.add(sub);
    }

    @Override
    public void unsubscribe(ISubscriber sub) {
        if(subscribers.contains(sub))subscribers.remove(sub);
    }

    @Override
    public void notify(Object notification) {
        for (ISubscriber iSubscriber : subscribers) {
            iSubscriber.update(notification);
        }
    }
}
