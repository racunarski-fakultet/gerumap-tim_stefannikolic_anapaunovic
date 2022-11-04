package dsw.rumap.app.gui.swing.controller;

import lombok.Getter;

@Getter
public class ActionManager {

    private InfoAction infoAction;
    private NewAction newAction;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        infoAction = new InfoAction();
        newAction = new NewAction();
    }
}
