package dsw.rumap.app.gui.swing.view;

import dsw.rumap.app.AppCore;
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
    private Integer totalTabs;
    private Integer i;
    private Integer deleteInd;
    private NotificationType n;
    private HashMap<Integer,MindMapView> mapViews;

    private Integer key;


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

    @Override
    public void update(Object notification) {

        if(notification instanceof ProjectExplorer)
            this.clean();

        else if(notification instanceof Project)
            fillView((Project) notification);

        else if(notification instanceof MyNotification){
            n = ((MyNotification) notification).getType();
            Object info =  ((MyNotification) notification).getInformation();

            if(n.equals(NotificationType.UPDATE_AUTOR)) {
                this.autor.setText(model.getAutor());

            }
            else if(n.equals(NotificationType.UPDATE_NAME)){
                this.label.setText(model.getName());

            }
            else if(n.equals(NotificationType.MAP_ADDED)){
                MindMapView mapV = new MindMapView(((MindMap) model.getChildren().get((int)info)));
                tabbedPane.addTab(model.getChildren().get((int)info).getName(),mapV);
                model.getChildren().get((int)info).addSubscriber(this);
                mapViews.put(((MindMap) model.getChildren().get((int)info)).getNum(),mapV);
            }
            else if(n.equals(NotificationType.MAP_DELETED)){
                tabbedPane.remove((int) info);

            }
            else if(n.equals(NotificationType.UPDATE_MAP_NAME)){
                tabbedPane.setTitleAt((int)info, this.model.getChildren().get((int)info).getName());

            }

       }

    }

    private void fillView(Project model) {

        this.label.setText(model.getName());
        this.autor.setText("Autor: " + model.getAutor());

        totalTabs = tabbedPane.getTabCount();
        i = 0;
        for (MapNode node : model.getChildren()) {
            node.addSubscriber(this);
            key = ((MindMap) node).getNum();
            if (i < totalTabs) {
                tabbedPane.setTitleAt(i, node.getName());
                if(mapViews.containsKey(key)){
                    tabbedPane.setComponentAt(i, mapViews.get(key));

                }
                else {
                    MindMapView mapV = new MindMapView((MindMap) node);
                    tabbedPane.setComponentAt(i, mapV);
                    mapViews.put(key,mapV);

                }
                i++;
            } else {
                if(mapViews.containsKey(key)){
                    tabbedPane.addTab(node.getName(),mapViews.get(key));

                }
                else {
                    MindMapView mapView = new MindMapView((MindMap) node);
                    tabbedPane.addTab(node.getName(), mapView);
                    mapViews.put(key,mapView);

                }
            }
        }

        deleteInd = i;
        while (i < totalTabs) {
            tabbedPane.remove(deleteInd);
            i++;
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void clean(){
        this.label.setText("Selektujte projekat");
        this.autor.setText("");
        this.tabbedPane.removeAll();
    }
}
