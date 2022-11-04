package dsw.rumap.app.gui.swing.tree.view;

import dsw.rumap.app.gui.swing.tree.model.MapTreeNode;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;
import lombok.NoArgsConstructor;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

@NoArgsConstructor
public class MapTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        URL imageURL = null;
        Icon icon;

        if(((MapTreeNode)value).getMapNode() instanceof ProjectExplorer)
            imageURL = getClass().getResource("/images/explorer.png");
        if(((MapTreeNode)value).getMapNode() instanceof Project)
            imageURL = getClass().getResource("/images/project.png");
        if(((MapTreeNode)value).getMapNode() instanceof MindMap)
            imageURL = getClass().getResource("/images/map.png");
        if(((MapTreeNode)value).getMapNode() instanceof Element)
            imageURL = getClass().getResource("/images/element.png");

        if(imageURL != null){
            icon = new ImageIcon(imageURL);
            this.setIcon(icon);
        }

        return this;
    }
}
