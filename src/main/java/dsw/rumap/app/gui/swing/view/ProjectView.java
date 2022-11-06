package dsw.rumap.app.gui.swing.view;

import dsw.rumap.app.maprepository.composite.MapNode;
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


    public ProjectView(){
        label = new JLabel();
        autor = new JLabel();
        tabbedPane = new JTabbedPane();
        this.setLayout(new BorderLayout());
        this.add(label,BorderLayout.NORTH);
        this.add(tabbedPane,BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void setModel(Project model){

        this.model = model;
        this.model.addSubscriber(this);
        this.removeAll();

        this.setLayout(new BorderLayout());
        this.label.setText(model.getName());
        this.add(label,BorderLayout.NORTH);
        this.tabbedPane.removeAll();

        for(MapNode node: model.getChildren()){
            MindMapView mapView = new MindMapView();
            tabbedPane.addTab(node.getName(),mapView);
        }

        this.add(tabbedPane, BorderLayout.CENTER);

        SwingUtilities.updateComponentTreeUI(this);

    }

    @Override
    public void update(Object notification) {

        this.removeAll();
        this.setLayout(new BorderLayout());
        //this.model = model;
        this.label.setText(model.getName());
        this.add(label,BorderLayout.NORTH);
        this.tabbedPane.removeAll();

        for(MapNode node: model.getChildren()){
            MindMapView mapView = new MindMapView();
            tabbedPane.addTab(node.getName(),mapView);
        }

        this.add(tabbedPane, BorderLayout.CENTER);

        SwingUtilities.updateComponentTreeUI(this);

    }
}
