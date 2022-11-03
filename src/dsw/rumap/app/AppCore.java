package dsw.rumap.app;

import dsw.rumap.app.core.AppFramework;
import dsw.rumap.app.core.Gui;
import dsw.rumap.app.core.MapRepository;
import dsw.rumap.app.gui.swing.SwingGui;
import dsw.rumap.app.maprepository.MapReposImpl;

public class AppCore extends AppFramework {
    private static AppCore instance;
    private AppCore(){

    }

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
        AppFramework appCore = AppCore.getInstance();
        appCore.initialise(gui, mapRepository);
        appCore.run();
    }
}
