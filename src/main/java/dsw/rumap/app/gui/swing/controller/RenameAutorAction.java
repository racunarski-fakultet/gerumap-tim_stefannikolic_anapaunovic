package dsw.rumap.app.gui.swing.controller;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.core.AppFramework;
import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RenameAutorAction extends AbstractRumapActions {

    public RenameAutorAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/new.png"));
        putValue(NAME, "RenameAutor");
        putValue(SHORT_DESCRIPTION, "RenameAutor");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(MainFrame.getInstance().getMapTree().getSelectedNode() != null){
            MapTreeNode selected = (MapTreeNode) MainFrame.getInstance().getMapTree().getSelectedNode();

            if(selected.getMapNode() instanceof Project){

                String autor = JOptionPane.showInputDialog("Unesite ime autora");
                if(!autor.isEmpty())
                    AppCore.getInstance().getMapRepository().setAutor(autor,selected.getMapNode());

                //((Project) selected.getMapNode()).setAutor(autor);

            }

        }

    }
}
