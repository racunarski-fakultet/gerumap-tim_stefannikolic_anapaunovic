package dsw.rumap.app.maprepository.commands;

import dsw.rumap.app.AppCore;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private int currentCommand;
    private List<Command> commands;

    public CommandManager() {
        currentCommand = 0;
        commands = new ArrayList<>();
    }

    public void addCommand(Command command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        this.doCommand();
    }

    public void doCommand(){
        if(currentCommand < commands.size()) {
            commands.get(currentCommand++).doCommand();
            AppCore.getInstance().getGui().enableUndoAction(true);
        }
        if(currentCommand == commands.size())
            AppCore.getInstance().getGui().enableRedoAction(false);
    }

    public void undoCommand(){
        if(currentCommand > 0) {
            commands.get(--currentCommand).undoCommand();
            AppCore.getInstance().getGui().enableRedoAction(true);
        }
        if(currentCommand == 0)
            AppCore.getInstance().getGui().enableUndoAction(false);
    }

    public void permamentUndoCommand(){
        commands.get(--currentCommand).undoCommand();
        commands.remove(currentCommand);
    }
}
