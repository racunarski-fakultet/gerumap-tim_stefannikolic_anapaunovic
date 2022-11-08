package dsw.rumap.app.logger;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.core.AppFramework;
import dsw.rumap.app.core.Logger;
import dsw.rumap.app.msggenerator.Message;
import dsw.rumap.app.msggenerator.MessageType;

import java.sql.Timestamp;

public class ConsoleLogger implements Logger {
    public ConsoleLogger() {
        AppCore.getInstance().getMsgGenerator().addSubscriber(this);

    }

    @Override
    public void update(Object notification) {
        System.out.println("desi se");
        this.printMessage((Message)notification);
    }

    @Override
    public void printMessage(Message message) {
        System.out.println("desi se");

        String m = message.getMessage();
        String m1 = new String();
        MessageType type = message.getType();

        if(m.equals("NODE_IS_NOT_SELECTED")){
            m1 = "Morate selektovati cvor";
        }
        else if(m.equals("NODE_CANNOT_BE_DELETED")){
            m1 = "ProjectExplorer ne moze biti obrisan";
        }
        else if(m.equals("NAME_CANNOT_BE_EMPTY")){
            m1 = "Morate uneti ime u polje";
        }
        else if(m.equals("NAME_ALREADY_EXISTS")){
            m1 = "Uneseno ime vec postoji";
        }

        System.out.println("["+ type + "] ["+  message.getTimeStamp().getDayOfMonth() + " " +
                message.getTimeStamp().getMonth() + " " + message.getTimeStamp().getYear() + " " +
                message.getTimeStamp().getHour() + ":" + message.getTimeStamp().getMinute() + "] "+ m1);

    }
}
