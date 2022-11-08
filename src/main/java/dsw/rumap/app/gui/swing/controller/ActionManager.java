package dsw.rumap.app.gui.swing.controller;

import lombok.Getter;

@Getter
public class ActionManager {

    private InfoAction infoAction;
    private NewAction newAction;
<<<<<<
=======
    private DeleteAction deleteAction;
>>>>>>
    private RenameAutorAction renameAutor;

    private RenameAction renameNode;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        this.infoAction = new InfoAction();
        this.newAction = new NewAction();
        this.renameAutor = new RenameAutorAction();
        this.renameNode = new RenameAction();
        this.deleteAction = new DeleteAction();
    }
}
