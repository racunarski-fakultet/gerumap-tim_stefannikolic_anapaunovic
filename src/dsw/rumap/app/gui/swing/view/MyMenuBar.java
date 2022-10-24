package dsw.rumap.app.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar() {
        super();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        //fileMenu.add(MainFrame.getInstance().getActionManager().getRenameAction());
        //fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());

        this.add(fileMenu);

    }
}
