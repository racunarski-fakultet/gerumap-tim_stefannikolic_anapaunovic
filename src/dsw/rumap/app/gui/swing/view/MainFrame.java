package dsw.rumap.app.gui.swing.view;

import dsw.rumap.app.gui.swing.controller.ActionManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance;
    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;

    private MainFrame(){}

    public static MainFrame getInstance(){
        if(instance == null) {
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

    private void initialise(){
        actionManager = new ActionManager();
        initialiseGUI();
    }

    private void initialiseGUI(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/2, screenHeight/2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("RuMap");
        menu = new MyMenuBar();
        setJMenuBar(menu);

        toolBar = new MyToolbar();
        this.add(toolBar, BorderLayout.NORTH);

        JPanel desktop = new JPanel();

        JScrollPane scPane = new JScrollPane();
        scPane.setMinimumSize(new Dimension(200, 150));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scPane, desktop);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        splitPane.setDividerLocation(250);
        splitPane.setOneTouchExpandable(true);
    }

    public ActionManager getActionManager(){
        return actionManager;
    }
}
