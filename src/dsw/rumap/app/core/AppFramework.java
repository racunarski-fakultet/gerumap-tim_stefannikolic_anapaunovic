package dsw.rumap.app.core;

abstract public class AppFramework {

    protected Gui gui;

    public abstract void run();

    public void initialise(Gui gui) {
        this.gui = gui;
    }
}
