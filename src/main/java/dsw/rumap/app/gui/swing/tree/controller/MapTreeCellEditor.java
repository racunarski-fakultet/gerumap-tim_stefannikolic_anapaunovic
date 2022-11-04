package dsw.rumap.app.gui.swing.tree.controller;

import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MapTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedOn;
    private JTextField edit;

    public MapTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        //return super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
        clickedOn = value;
        edit = new JTextField(clickedOn.toString());
        edit.addActionListener(this);
        return edit;
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if(event instanceof MouseEvent && ((MouseEvent)event).getClickCount() == 3)
            return true;
        return false;
    }

    public void actionPreformed(ActionEvent e){
        if(!(clickedOn instanceof MapTreeNode))
            return;

        MapTreeNode node = (MapTreeNode) clickedOn;
        node.setName(e.getActionCommand());
    }
}
