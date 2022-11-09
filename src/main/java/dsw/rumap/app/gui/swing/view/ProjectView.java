package dsw.rumap.app.gui.swing.view;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;


import javax.swing.*;
import java.awt.*;
@Getter
@Setter
public class ProjectView extends JPanel implements ISubscriber {

    private JLabel label;
    private JLabel autor;
    private Project model;
    private JTabbedPane tabbedPane;
    private Integer totalTabs;
    private Integer i;


    public ProjectView(){
        label = new JLabel("Selektujte projekat");
        autor = new JLabel(" ");
        tabbedPane = new JTabbedPane();
        BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(box);
        this.add(Box.createVerticalStrut(5));
        this.add(label);
        this.add(Box.createVerticalStrut(3));
        this.add(autor);
        this.add(Box.createVerticalStrut(5));
        this.add(tabbedPane);

    }

    public void setModel(Project model){

        this.model = model;
        this.model.addSubscriber(this);
        this.fillView();

    }

    @Override
    public void update(Object notification) {
        this.fillView();
    }

    public void fillView(){

        this.label.setText(model.getName());
        this.autor.setText("Autor: " + model.getAutor());

        totalTabs = tabbedPane.getTabCount();
        i = 0;
        int hasCh = 0;

        for(MapNode node: model.getChildren()){
            hasCh = 1;
            if(i < totalTabs){
                tabbedPane.setTitleAt(i,node.getName());
                tabbedPane.setComponentAt(i,new MindMapView((MindMap) node));
                i++;
            }
            else {
                MindMapView mapView = new MindMapView((MindMap) node);
                tabbedPane.addTab(node.getName(),mapView);
            }
        }
        int deleteInd = i;
        if(hasCh == 0){
            tabbedPane.removeAll();
        }
        else if(i < totalTabs){
            while(i < totalTabs){
                tabbedPane.remove(deleteInd);
                i++;
            }

        }
        SwingUtilities.updateComponentTreeUI(this);
    }
}
