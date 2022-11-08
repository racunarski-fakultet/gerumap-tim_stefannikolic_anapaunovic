package dsw.rumap.app.logger;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.core.Logger;
import dsw.rumap.app.msggenerator.Message;
import dsw.rumap.app.msggenerator.MessageType;

public class FileLogger implements Logger {

    @Override
    public void update(Object notification) {
        this.printMessage((Message)notification);
    }

    @Override
    public void printMessage(Message message) {

        AppCore.getInstance().getMsgGenerator().addSubscriber(this);

        String m = message.getMessage();
        MessageType type = message.getType();
    }
}
