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
        //this.setLayout(new BorderLayout());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(label);
        this.add(autor);
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

        //this.removeAll();

        //this.setLayout(new BorderLayout());
        this.label.setText(model.getName());
        this.autor.setText("Naziv autora: " + model.getAutor());
        //this.add(label,BorderLayout.NORTH);
        //this.add(autor,BorderLayout.CENTER);
        //this.tabbedPane.removeAll();

        totalTabs = tabbedPane.getTabCount();
        i = 0;
        int hasCh = 0;
        System.out.println( "total " + totalTabs);

        for(MapNode node: model.getChildren()){
            hasCh = 1;
            System.out.println("udje");
            if(i < totalTabs){
                tabbedPane.setTitleAt(i,node.getName());
                tabbedPane.setComponentAt(i,new MindMapView((MindMap) node));
                i++;
                System.out.println("stari");
            }
            else {
                MindMapView mapView = new MindMapView((MindMap) node);
                tabbedPane.addTab(node.getName(),mapView);
                System.out.println("novi");
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

        System.out.println("i" + i);

        //this.add(tabbedPane, BorderLayout.SOUTH);
        SwingUtilities.updateComponentTreeUI(this);

    }
}
