package dsw.rumap.app.gui.swing.view;

import com.sun.nio.sctp.MessageInfo;
import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.controller.AbstractRumapActions;
import dsw.rumap.app.gui.swing.controller.ActionManager;
import dsw.rumap.app.gui.swing.controller.NewAction;
import dsw.rumap.app.gui.swing.tree.MapTree;
import dsw.rumap.app.gui.swing.tree.MapTreeImpl;
import dsw.rumap.app.maprepository.MapReposImpl;
import dsw.rumap.app.msggenerator.Message;
import dsw.rumap.app.msggenerator.MessageGeneratorImpl;
import dsw.rumap.app.msggenerator.MessageType;
import dsw.rumap.app.msggenerator.Problem;
import dsw.rumap.app.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Getter
@Setter
public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private MapTree mapTree;
    private ProjectView projectView;

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
        mapTree = new MapTreeImpl();
        AppCore.getInstance().getMsgGenerator().addSubscriber(this);
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

        JTree explorerTree = mapTree.generateTree(AppCore.getInstance().getMapRepository().getProjectExplorer());
        projectView = new ProjectView();

        JScrollPane scPane = new JScrollPane(explorerTree);
        scPane.setMinimumSize(new Dimension(200, 150));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scPane, projectView);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        splitPane.setDividerLocation(250);
        splitPane.setOneTouchExpandable(true);
    }

    public ActionManager getActionManager(){
        return actionManager;
    }

    @Override
    public void update(Object notification) {
        Problem message = ((Message)notification).getMessage();
        MessageType type = ((Message)notification).getType();

        if(type.equals(MessageType.INFORMATION)){
            JOptionPane.showMessageDialog(this,message,"Information", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(type.equals(MessageType.WARNING)){
            JOptionPane.showMessageDialog(this,message,"Warning", JOptionPane.WARNING_MESSAGE);
        }
        else if(type.equals(MessageType.ERROR)){
            JOptionPane.showMessageDialog(this,message,"Error",JOptionPane.ERROR_MESSAGE);
        }

    }
}
