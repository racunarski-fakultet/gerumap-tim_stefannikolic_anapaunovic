package dsw.rumap.app.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar() {
        super();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        helpMenu.setMnemonic(KeyEvent.VK_H);
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getLoadProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveProjectAsAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getRenameAutor());
        fileMenu.add(MainFrame.getInstance().getActionManager().getRenameNode());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());
        helpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());
        this.add(fileMenu);
        this.add(helpMenu);
    }
}
