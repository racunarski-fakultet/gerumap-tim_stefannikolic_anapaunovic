package dsw.rumap.app.logger;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.core.Logger;
import dsw.rumap.app.msggenerator.Message;
import dsw.rumap.app.msggenerator.MessageType;
import dsw.rumap.app.msggenerator.Problem;

import javax.security.auth.login.AppConfigurationEntry;
import java.time.format.DateTimeFormatter;

public abstract class AbstractLogger implements Logger {

    Problem msg;
    MessageType type;
    String print;
    DateTimeFormatter dtf;

    @Override
    public void printMessage(Message message) {
        msg = message.getMessage();
        type = message.getType();
        print = new String();

        if(msg.equals(Problem.NODE_IS_NOT_SELECTED))
            print = "Morate selektovati polje.";

        else if(msg.equals(Problem.NODE_CANNOT_BE_DELETED))
            print = "ProjectExplorer ne može biti obrisan.";

        else if(msg.equals(Problem.NAME_CANNOT_BE_EMPTY))
            print = "Morate uneti ime u polje.";

        else if(msg.equals(Problem.NAME_ALREADY_EXISTS))
            print = "Unešeno ime već postoji.";

        else if(msg.equals(Problem.NODE_CANNOT_HAVE_CHILDREN))
            print = "Elementu nije moguće dodati čvor.";

        dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    }

    @Override
    public abstract void closeFileLogger();

    @Override
    public void update(Object notification) {
        printMessage((Message) notification);
    }
}
