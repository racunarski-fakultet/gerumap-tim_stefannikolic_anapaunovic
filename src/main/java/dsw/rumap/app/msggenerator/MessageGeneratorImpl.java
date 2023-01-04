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

        switch (event){
            case SELECTED_NODE_IS_NOT_PROJECT:
            case NODE_CANNOT_HAVE_CHILDREN:
            case STROKE_HAS_TO_BE_NUMBER:
            case ELEMENTS_ARE_NOT_SELECTED:
            case NODE_IS_NOT_SELECTED:
            case SELECT_EXACTLY_ONE_TERM_TO_CENTRALIZE:
            case NODE_CANNOT_BE_DELETED:
                message = new Message(MessageType.ERROR, event);
                break;
            case NAME_ALREADY_EXISTS:
            case ZOOM_IS_AT_MAX:
            case ZOOM_IS_AT_MIN:
                message = new Message(MessageType.INFORMATION, event);
                break;
            case NAME_CANNOT_BE_EMPTY:
                message = new Message(MessageType.WARNING, event);
                break;
            default: return;
        }
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
