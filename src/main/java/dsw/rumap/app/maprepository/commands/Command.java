package dsw.rumap.app.maprepository.commands;

public interface Command {
    public void doCommand();
    public void undoCommand();
}
