package dsw.rumap.app.logger;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.core.AppFramework;
import dsw.rumap.app.core.Logger;
import dsw.rumap.app.msggenerator.Message;
import dsw.rumap.app.msggenerator.MessageType;
import dsw.rumap.app.msggenerator.Problem;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger extends AbstractLogger {
    public ConsoleLogger() {
        AppCore.getInstance().getMsgGenerator().addSubscriber(this);
    }

    @Override
    public void printMessage(Message message) {
        super.printMessage(message);
        System.out.println("["+ type + "] ["+ dtf.format(message.getTimeStamp()) + "] "+ print);

    }

    @Override
    public void closeFileLogger() {
        return;
    }
}
