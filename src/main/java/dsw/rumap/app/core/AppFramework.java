package dsw.rumap.app.core;

import dsw.rumap.app.logger.ConsoleLogger;
import dsw.rumap.app.logger.FileLogger;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract public class AppFramework {

    protected Gui gui;
    protected MapRepository mapRepository;
    protected MessageGenerator msgGenerator;
    protected Logger consoleLogger;
    protected Logger fileLogger;
    protected Serializer serializer;

    public abstract void run();

    public void initialise(Gui gui, MapRepository mapRepository, MessageGenerator msgGenerator, Serializer serializer) {
        this.gui = gui;
        this.mapRepository = mapRepository;
        this.msgGenerator = msgGenerator;
        this.serializer = serializer;

    }
    public void initialiseLogger(Logger fileLogger, Logger consoleLogger){
        this.fileLogger=fileLogger;
        this.consoleLogger=consoleLogger;

    }
}
