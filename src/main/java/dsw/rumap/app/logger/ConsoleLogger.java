package dsw.rumap.app.logger;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.msggenerator.Message;

public class ConsoleLogger extends AbstractLogger {
    public ConsoleLogger() {
        AppCore.getInstance().getMsgGenerator().subscribe(this);
    }

    @Override
    public void printMessage(Message message) {
        super.printMessage(message);
        System.out.println("["+ type + "] ["+ dtf.format(message.getTimeStamp()) + "] "+ print);

    }

    @Override
    public void closeFileLogger() {
    }
}
