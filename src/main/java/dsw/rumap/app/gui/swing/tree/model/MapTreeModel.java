package dsw.rumap.app.gui.swing.tree.model;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class MapTreeModel extends DefaultTreeModel {

    public MapTreeModel(TreeNode root) {
        super(root);
    }

    @Override
    public void setRoot(TreeNode root) {
        super.setRoot(root);
    }

    @Override
    public MapTreeNode getRoot(){
        MapTreeNode root = (MapTreeNode) super.getRoot();
        return root;
    }
}
