package dsw.rumap.app.core;

import dsw.rumap.app.msggenerator.MessageType;
import dsw.rumap.app.msggenerator.Problem;
import dsw.rumap.app.observer.IPublisher;

public interface MessageGenerator extends IPublisher {

    public void createMessage(Problem event);
}
