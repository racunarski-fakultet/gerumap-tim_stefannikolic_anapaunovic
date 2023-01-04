package dsw.rumap.app.maprepository.commands;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;

public class AddElementCommand implements Command{

    private MapNode parent;
    private MapNode child;

    public AddElementCommand(MapNode parent, MapNode child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void doCommand() {
        ((MapNodeC) parent).add(child);
    }

    @Override
    public void undoCommand() {
        ((MapNodeC) parent).delete(child);
    }
}
