package dsw.rumap.app.gui.swing.controller;

import com.sun.tools.javac.Main;
import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.mapnodefactory.MapNodeFactory;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;
import dsw.rumap.app.msggenerator.Problem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class NewAction extends AbstractRumapActions{

    public NewAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/new.png"));
        putValue(NAME, "New");
        putValue(SHORT_DESCRIPTION, "New");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(MainFrame.getInstance().getMapTree().getSelectedNode() == null){
            AppCore.getInstance().getMsgGenerator().createMessage(Problem.NODE_IS_NOT_SELECTED);
            return;
        }
        if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof MindMap) return;

        MapTreeNode selected = MainFrame.getInstance().getMapTree().getSelectedNode();
        MapNodeFactory mapNodeFactory = AppCore.getInstance().getMapRepository().getMapNodeFactory(selected.getMapNode());

        if(mapNodeFactory == null) {
            AppCore.getInstance().getMsgGenerator().createMessage(Problem.NODE_CANNOT_HAVE_CHILDREN);
            return;
        }

        if(selected.getMapNode() instanceof ProjectExplorer || selected.getMapNode() instanceof Project){
            MapNode child = mapNodeFactory.orderChild(selected.getMapNode());
            AppCore.getInstance().getMapRepository().addChild(selected.getMapNode(),child);
            MainFrame.getInstance().getMapTree().addChild(selected, child);
        }

    }
}
