package dsw.rumap.app.gui.swing.controller;

import dsw.rumap.app.gui.swing.controller.mapactions.*;
import lombok.Getter;

@Getter
public class ActionManager {

    private InfoAction infoAction;
    private NewAction newAction;
    private DeleteAction deleteAction;
    private RenameAutorAction renameAutor;
    private RenameAction renameNode;
    private AddRelationAction addRelationAction;
    private AddTermAction addTermAction;
    private DeleteElementAction deleteElementAction;
    private MoveElementAction moveElementAction;
    private SelectElementAction selectElementAction;
    private MapOptionsAction mapOptionsAction;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        this.infoAction = new InfoAction();
        this.newAction = new NewAction();
        this.renameAutor = new RenameAutorAction();
        this.renameNode = new RenameAction();
        this.deleteAction = new DeleteAction();
        this.addRelationAction = new AddRelationAction();
        this.addTermAction = new AddTermAction();
        this.deleteElementAction = new DeleteElementAction();
        this.moveElementAction = new MoveElementAction();
        this.selectElementAction = new SelectElementAction();
        this.mapOptionsAction = new MapOptionsAction();
    }
}
