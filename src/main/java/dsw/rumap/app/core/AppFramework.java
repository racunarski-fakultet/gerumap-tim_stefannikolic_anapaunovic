package dsw.rumap.app.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract public class AppFramework {

    protected Gui gui;
    protected MapRepository mapRepository;

    public abstract void run();

    public void initialise(Gui gui, MapRepository mapRepository) {
        this.gui = gui;
        this.mapRepository = mapRepository;
    }
}
