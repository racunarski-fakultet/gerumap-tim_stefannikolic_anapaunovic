package dsw.rumap.app.core;

import dsw.rumap.app.msggenerator.Message;
import dsw.rumap.app.observer.ISubscriber;

public interface Logger extends ISubscriber {

    public void printMessage(Message message);
    public void closeFileLogger();
}
