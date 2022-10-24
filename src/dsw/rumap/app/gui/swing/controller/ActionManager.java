package dsw.rumap.app.gui.swing.controller;

import lombok.Getter;

import java.awt.*;
@Getter
public class ActionManager {

    private RenameAction renameAction;
    private NewProjectAction newProjectAction;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        renameAction = new RenameAction();
        newProjectAction = new NewProjectAction();
    }
}
