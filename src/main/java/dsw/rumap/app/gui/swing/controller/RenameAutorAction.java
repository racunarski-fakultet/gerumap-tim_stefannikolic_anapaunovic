package dsw.rumap.app.gui.swing.controller;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.msggenerator.Problem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RenameAutorAction extends AbstractRumapActions {

    public RenameAutorAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/autor.png"));
        putValue(NAME, "Autor");
        putValue(SHORT_DESCRIPTION, "Change Autor");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MapTreeNode selected = MainFrame.getInstance().getMapTree().getSelectedNode();
        if(selected == null)
            AppCore.getInstance().getMsgGenerator().createMessage(Problem.NODE_IS_NOT_SELECTED);

        else if(selected.getMapNode() instanceof Project){
            String autor = JOptionPane.showInputDialog("Unesite ime autora");
            if(autor == null)
                return;

            if(!autor.isEmpty())
                AppCore.getInstance().getMapRepository().setAuthor(autor,selected.getMapNode());
            else {
                AppCore.getInstance().getMsgGenerator().createMessage(Problem.NAME_CANNOT_BE_EMPTY);
                return;
            };

        }

        else AppCore.getInstance().getMsgGenerator().createMessage(Problem.SELECTED_NODE_IS_NOT_PROJECT);
    }
}
