package dsw.rumap.app.gui.swing.tree.view;

import dsw.rumap.app.gui.swing.tree.controller.MapTreeCellEditor;
import dsw.rumap.app.gui.swing.tree.controller.MapTreeSelectionListener;
import dsw.rumap.app.gui.swing.tree.model.MapTreeModel;
import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;

import javax.swing.*;

public class MapTreeView extends JTree {

    public MapTreeView(MapTreeModel root) {
        this.setModel(root);
        MapTreeCellRenderer mapTreeRenderer = new MapTreeCellRenderer();
        this.setCellEditor(new MapTreeCellEditor(this, mapTreeRenderer));
        this.setCellRenderer(mapTreeRenderer);
        this.addTreeSelectionListener(new MapTreeSelectionListener());
        this.setEditable(true);
    }
}
