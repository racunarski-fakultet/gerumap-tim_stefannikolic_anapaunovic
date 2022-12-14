package dsw.rumap.app.gui.swing.view;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.state.StateManager;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.implementation.ProjectExplorer;
import dsw.rumap.app.observer.ISubscriber;
import dsw.rumap.app.observer.notification.MyNotification;
import dsw.rumap.app.observer.notification.NotificationType;
import lombok.Getter;
import lombok.Setter;


import javax.swing.*;
import java.util.HashMap;

@Getter
@Setter
public class ProjectView extends JPanel implements ISubscriber {

    private JLabel label;
    private JLabel author;
    private Project model;
    private JTabbedPane tabbedPane;
    //private MapScrollPane mapScrollPane;
    private NotificationType notificationType;
    private HashMap<Integer,MindMapView> mapViews;
    private StateManager stateManager;
    private MindMapView currentMindMapView;


    public ProjectView(){
        label = new JLabel("Selektujte projekat");
        author = new JLabel("");
        tabbedPane = new JTabbedPane();
        //mapScrollPane = new MapScrollPane();
        BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(box);
        this.add(Box.createVerticalStrut(5));
        this.add(label);
        this.add(Box.createVerticalStrut(3));
        this.add(author);
        this.add(Box.createVerticalStrut(5));
        this.add(tabbedPane);
        AppCore.getInstance().getMapRepository().getProjectExplorer().subscribe(this);
        mapViews = new HashMap<>();
        stateManager = new StateManager();
        currentMindMapView = null;
    }

    @Override
    public void update(Object notification) {

        if(notification instanceof ProjectExplorer)
            this.clean();

        else if(notification instanceof Project)
            fillView((Project) notification);

        else if(notification instanceof MyNotification){
            notificationType = ((MyNotification) notification).getType();
            Object info =  ((MyNotification) notification).getInformation();

            if(notificationType.equals(NotificationType.UPDATE_AUTOR)) {
                this.author.setText("Author: " + model.getAuthor());
            }
            else if(notificationType.equals(NotificationType.UPDATE_NAME)){
                this.label.setText(model.getName());
            }
            else if(notificationType.equals(NotificationType.MAP_ADDED)){
                MindMapView newMindMapView = new MindMapView(((MindMap) model.getChildren().get((int)info)));
                tabbedPane.addTab(model.getChildren().get((int)info).getName(), new MapScrollPane(newMindMapView));
                model.getChildren().get((int)info).subscribe(this);
                mapViews.put(((MindMap) model.getChildren().get((int)info)).getKey(), newMindMapView);
            }
            else if(notificationType.equals(NotificationType.MAP_DELETED)){
                tabbedPane.remove((int) info);
                mapViews.remove((int) info);
            }
            else if(notificationType.equals(NotificationType.UPDATE_MAP_NAME)){
                tabbedPane.setTitleAt((int)info, this.model.getChildren().get((int)info).getName());
            }
        }
    }

    public void setModel(Project model){

        if(this.model == model)
            return;

        if(this.model != null){
            this.model.unsubscribe(this);
            for(MapNode node: this.model.getChildren()){
                node.unsubscribe(this);
            }
        }
        this.model = model;
        this.model.subscribe(this);
        this.fillView(model);
    }

    private void fillView(Project model) {

        this.label.setText(model.getName());
        this.author.setText("Author: " + model.getAuthor());

        Integer totalTabs = tabbedPane.getTabCount();
        Integer tabCounter = 0;
        for (MapNode mapNode : model.getChildren()) {

            MindMap mindMap = (MindMap) mapNode;
            mindMap.subscribe(this);
            Integer key = mindMap.getKey();

            if (tabCounter < totalTabs) {
                tabbedPane.setTitleAt(tabCounter, mindMap.getName());
                if(mapViews.containsKey(key)){
                    tabbedPane.setComponentAt(tabCounter, new MapScrollPane(mapViews.get(key)));
                }
                else {
                    tabbedPane.setComponentAt(tabCounter, new MapScrollPane(createMindMapView(mindMap)));
                }
                tabCounter++;
            }
            else {
                if(mapViews.containsKey(key)){
                    tabbedPane.addTab(mindMap.getName(), new MapScrollPane(mapViews.get(key)));
                }
                else {
                    tabbedPane.addTab(mindMap.getName(), new MapScrollPane(createMindMapView(mindMap)));
                }
            }
        }

        Integer deleteInd = tabCounter;
        while (tabCounter < totalTabs) {
            tabbedPane.remove(deleteInd);
            tabCounter++;
        }

        SwingUtilities.updateComponentTreeUI(this);
    }

    private MindMapView createMindMapView(MindMap mindMap){
        MindMapView mindMapView = new MindMapView(mindMap);
        mapViews.put(mindMap.getKey(), mindMapView);
        return mindMapView;
    }

    private void clean(){
        this.label.setText("[Select Project]");
        this.author.setText("");
        this.tabbedPane.removeAll();
    }

    public MindMapView getCurrentMindMapView() {
        return (MindMapView) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView();
    }

    public void startAddRelationState(){this.stateManager.setAddRelationState();}

    public void startAddTermState(){this.stateManager.setAddTermState();}

    public void startDeleteElementState(){this.stateManager.setDeleteElementState();}

    public void startSelectElementState(){this.stateManager.setSelectElementState();}

    public void startMoveElementState(){this.stateManager.setMoveElementState();}

    public void executeRequest(){
        this.stateManager.getCurrentState().execute();
    }

    public void medMousePressed(int x, int y, MindMapView mindMapView){ this.stateManager.getCurrentState().stateMousePressed(x, y, mindMapView);}

    public void medMouseDragged(int x, int y, MindMapView mindMapView){ this.stateManager.getCurrentState().stateMouseDragged(x, y, mindMapView);}

    public void medMouseReleased(int x, int y, MindMapView mindMapView){ this.stateManager.getCurrentState().stateMouseReleased(x, y, mindMapView);}
}
