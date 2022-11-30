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
    private JLabel autor;
    private Project model;
    private JTabbedPane tabbedPane;
    private NotificationType notificationType;
    private HashMap<Integer,MindMapView> mapViews;
    private StateManager stateManager;
    private MindMapView currentMindMap;


    public ProjectView(){
        label = new JLabel("Selektujte projekat");
        autor = new JLabel("");
        tabbedPane = new JTabbedPane();
        BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(box);
        this.add(Box.createVerticalStrut(5));
        this.add(label);
        this.add(Box.createVerticalStrut(3));
        this.add(autor);
        this.add(Box.createVerticalStrut(5));
        this.add(tabbedPane);
        AppCore.getInstance().getMapRepository().getProjectExplorer().addSubscriber(this);
        mapViews = new HashMap<>();

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
                this.autor.setText(model.getAutor());
            }
            else if(notificationType.equals(NotificationType.UPDATE_NAME)){
                this.label.setText(model.getName());
            }
            else if(notificationType.equals(NotificationType.MAP_ADDED)){
                MindMapView mapV = new MindMapView(((MindMap) model.getChildren().get((int)info)));
                tabbedPane.addTab(model.getChildren().get((int)info).getName(),mapV);
                model.getChildren().get((int)info).addSubscriber(this);
                mapViews.put(((MindMap) model.getChildren().get((int)info)).getKey(),mapV);
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
            this.model.removeSubscriber(this);
            for(MapNode node: this.model.getChildren()){
                node.removeSubscriber(this);
            }
        }
        this.model = model;
        this.model.addSubscriber(this);
        this.fillView(model);

    }

    private void fillView(Project model) {

        this.label.setText(model.getName());
        this.autor.setText("Autor: " + model.getAutor());

        Integer totalTabs = tabbedPane.getTabCount();
        Integer tabCounter = 0;
        for (MapNode mapNode : model.getChildren()) {

            MindMap mindMap = (MindMap) mapNode;
            mindMap.addSubscriber(this);
            Integer key = mindMap.getKey();

            if (tabCounter < totalTabs) {
                tabbedPane.setTitleAt(tabCounter, mindMap.getName());
                if(mapViews.containsKey(key)){
                    tabbedPane.setComponentAt(tabCounter, mapViews.get(key));
                }
                else {
                    tabbedPane.setComponentAt(tabCounter, createMindMapView(mindMap));
                }
                tabCounter++;
            }
            else {
                if(mapViews.containsKey(key)){
                    tabbedPane.addTab(mindMap.getName(), mapViews.get(key));
                }
                else {
                    tabbedPane.addTab(mindMap.getName(), createMindMapView(mindMap));
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
        mapViews.put(mindMap.getKey(),mindMapView);
        return mindMapView;
    }

    private void clean(){
        this.label.setText("Selektujte projekat");
        this.autor.setText("");
        this.tabbedPane.removeAll();
    }

    public void startAddRelationState(){this.stateManager.setAddRelationState();}

    public void startAddTermState(){this.stateManager.setAddTermState();}

    public void startDeleteElementState(){this.stateManager.setDeleteElementState();}

    public void startSelectElementState(){this.stateManager.setSelectElementState();}

    public void startMoveElementState(){this.stateManager.setMoveElementState();}

    public void executeRequest(){
        this.stateManager.getCurrentState().execute();
    }

    public void medMousePressed(int x, int y){}

    public void medMouseReleased(){}

    public void medMouseDragged(){}
}
