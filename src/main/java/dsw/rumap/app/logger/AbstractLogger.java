package dsw.rumap.app.logger;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.core.Logger;
import dsw.rumap.app.msggenerator.Message;
import dsw.rumap.app.msggenerator.MessageType;
import dsw.rumap.app.msggenerator.Problem;
import lombok.Getter;
import lombok.Setter;

import javax.security.auth.login.AppConfigurationEntry;
import java.time.format.DateTimeFormatter;
@Getter
@Setter
public abstract class AbstractLogger implements Logger {

    protected MessageType type;
    protected String print;
    protected DateTimeFormatter dtf;

    @Override
    public void printMessage(Message message) {
        type = message.getType();
        print = message.getMessage().toString();
        dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    }

    @Override
    public abstract void closeFileLogger();

    @Override
    public void update(Object notification) {
        printMessage((Message) notification);
    }
}
