package dsw.rumap.app.gui.swing.view;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.controller.ActionManager;
import dsw.rumap.app.gui.swing.controller.mapactions.MapOptionsAction;
import dsw.rumap.app.gui.swing.tree.MapTree;
import dsw.rumap.app.gui.swing.tree.MapTreeImpl;
import dsw.rumap.app.msggenerator.Message;
import dsw.rumap.app.msggenerator.MessageType;
import dsw.rumap.app.msggenerator.Problem;
import dsw.rumap.app.observer.IPublisher;
import dsw.rumap.app.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MainFrame extends JFrame implements ISubscriber, IPublisher {
    private static MainFrame instance;
    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar menuToolBar;
    private JToolBar mindMapToolBar;
    private MapTree mapTree;
    private JPanel eastPanel;
    private ProjectView projectView;
    private List<ISubscriber> subscriberList;

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
        AppCore.getInstance().getMsgGenerator().subscribe(this);
        initialiseGUI();
    }

    private void initialiseGUI(){
        subscriberList = new ArrayList<>();
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

        menuToolBar = new MenuToolbar();
        //menuToolBar.setVisible(false);
        this.add(menuToolBar, BorderLayout.NORTH);

        mindMapToolBar = new MindMapToolBar();
        add(mindMapToolBar, BorderLayout.EAST);

        Button optionsBtn = new Button("Options");
        optionsBtn.setPreferredSize(new Dimension(33, 33));
        optionsBtn.addActionListener(actionManager.getMapOptionsAction());
        eastPanel = new JPanel(new BorderLayout());
        eastPanel.add(mindMapToolBar, BorderLayout.NORTH);
        eastPanel.add(optionsBtn, BorderLayout.SOUTH);

        this.add(eastPanel, BorderLayout.EAST);


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

    @Override
    public void subscribe(ISubscriber sub) {
        this.subscriberList.add(sub);
    }

    @Override
    public void unsubscribe(ISubscriber sub) {
        this.subscriberList.remove(sub);
    }

    @Override
    public void notify(Object notification) {
        for (ISubscriber sub :
                subscriberList) {
            sub.update(notification);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        notify(this);
    }

    public void hideMMTB(){
        this.eastPanel.setVisible(false);
    }

    public void showMMTB(){
        this.eastPanel.setVisible(true);
    }
}
