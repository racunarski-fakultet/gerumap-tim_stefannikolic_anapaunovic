package dsw.rumap.app.gui.swing.tree.view;

import dsw.rumap.app.gui.swing.tree.controller.MapTreeCellEditor;
import dsw.rumap.app.gui.swing.tree.controller.MapTreeMouseListener;
import dsw.rumap.app.gui.swing.tree.controller.MapTreeSelectionListener;
import dsw.rumap.app.gui.swing.tree.model.MapTreeModel;
import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapTreeView extends JTree{

    public MapTreeView(MapTreeModel root) {
        this.setModel(root);
        MapTreeCellRenderer mapTreeRenderer = new MapTreeCellRenderer();
        this.setCellEditor(new MapTreeCellEditor(this, mapTreeRenderer));
        this.setCellRenderer(mapTreeRenderer);
        this.addTreeSelectionListener(new MapTreeSelectionListener());
        this.setEditable(true);
        this.addMouseListener(new MapTreeMouseListener());
    }


}
