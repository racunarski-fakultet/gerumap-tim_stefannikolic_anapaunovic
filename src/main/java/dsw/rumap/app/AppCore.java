package dsw.rumap.app;

import dsw.rumap.app.core.*;
import dsw.rumap.app.gui.swing.SwingGui;
import dsw.rumap.app.logger.ConsoleLogger;
import dsw.rumap.app.logger.FileLogger;
import dsw.rumap.app.maprepository.MapReposImpl;
import dsw.rumap.app.msggenerator.MessageGeneratorImpl;
import dsw.rumap.app.serializer.GsonSerializer;

public class AppCore extends AppFramework {
    private static AppCore instance;
    private AppCore(){}

    public static AppCore getInstance(){
        if(instance==null){
            instance = new AppCore();
        }
        return instance;
    }

    public void run(){
        this.gui.start();
    }

    public static void main(String[] args){
        Gui gui = new SwingGui();
        MapRepository mapRepository = new MapReposImpl();
        MessageGenerator msgGenerator = new MessageGeneratorImpl();
        Serializer serializer = new GsonSerializer();
        AppFramework appCore = AppCore.getInstance();
        appCore.initialise(gui, mapRepository, msgGenerator, serializer);

        Logger consoleLogger = new ConsoleLogger();
        Logger fileLogger = new FileLogger();
        appCore.initialiseLogger(fileLogger, consoleLogger);

        appCore.run();
    }
}
