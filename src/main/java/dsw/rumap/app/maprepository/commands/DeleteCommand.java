package dsw.rumap.app.maprepository.commands;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.maprepository.implementation.elements.RelationElement;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;

import java.util.List;

public class DeleteCommand implements Command{

    private MapNode parent;
    private MapNode child;
    private List<RelationElement> relations;

    public DeleteCommand(MapNode parent, MapNode child, List<RelationElement> relations) {
        this.parent = parent;
        this.child = child;
        this.relations = relations;
    }

    @Override
    public void doCommand() {
        ((MapNodeC) parent).delete(child);
        for (RelationElement re :
                relations) {
            ((MapNodeC) parent).delete(re);
        }
    }

    @Override
    public void undoCommand() {
        ((MapNodeC) parent).add(child);
        for (RelationElement re :
                relations) {
            ((MapNodeC) parent).add(re);
        }
    }
}
