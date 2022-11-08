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
    private boolean create;


    public ProjectView(){
        label = new JLabel("Selektujte projekat");
        autor = new JLabel(" ");
        tabbedPane = new JTabbedPane();
        this.setLayout(new BorderLayout());
        this.add(label,BorderLayout.NORTH);
        this.create = true;
    }

    public void setModel(Project model){

        this.fillView(model);

    }

    @Override
    public void update(Object notification) {

        this.fillView((Project)notification);

    }

    public void fillView(Project model){

        this.model = model;
        this.model.addSubscriber(this);
        this.removeAll();

        this.setLayout(new BorderLayout());
        this.label.setText(model.getName());
        this.autor.setText("Naziv autora: " + model.getAutor());
        this.add(label,BorderLayout.NORTH);
        this.add(autor,BorderLayout.CENTER);
        this.tabbedPane.removeAll();

        for(MapNode node: model.getChildren()){
            MindMapView mapView = new MindMapView();
            tabbedPane.addTab(node.getName(),mapView);

        }


        this.add(tabbedPane, BorderLayout.SOUTH);

        SwingUtilities.updateComponentTreeUI(this);

    }
}
