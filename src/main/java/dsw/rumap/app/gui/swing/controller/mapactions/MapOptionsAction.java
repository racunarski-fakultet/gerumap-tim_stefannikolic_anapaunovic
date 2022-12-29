package dsw.rumap.app.gui.swing.controller.mapactions;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.controller.AbstractRumapActions;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.maprepository.commands.ChangeAppearanceCommand;
import dsw.rumap.app.maprepository.commands.Command;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.Pair;
import dsw.rumap.app.msggenerator.Problem;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;

@Getter
public class MapOptionsAction extends AbstractRumapActions {

    private JDialog dialog;
    private JTextField strokeTextField;
    private JColorChooser colorChooser;

    public MapOptionsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Options");
        putValue(SHORT_DESCRIPTION, "Options");


        dialog = new JDialog(MainFrame.getInstance(), "Options", true);
        //dialog.setLocation((int)MainFrame.getInstance().getLocation().getX()+MainFrame.getInstance().getWidth()/6, (int)MainFrame.getInstance().getLocation().getY()+MainFrame.getInstance().getHeight()/6);
        dialog.setLocationRelativeTo(null);
        dialog.setMinimumSize(new Dimension(650, 490));
        dialog.setMaximumSize(new Dimension(650, 490));
        dialog.setLayout(new BorderLayout(5, 15));
        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        dialog.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        dialog.add(southPanel, BorderLayout.SOUTH);

        Button okBtn = new Button("OK");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stroke = getStrokeTextField().getText();
                Color color = getColorChooser().getColor();
                try {
                    Integer strokeInt = Integer.parseInt(stroke);
                    if(MainFrame.getInstance().getProjectView() == null || MainFrame.getInstance().getProjectView().getCurrentMindMapView() == null){
                        AppCore.getInstance().getMsgGenerator().createMessage(Problem.ELEMENTS_ARE_NOT_SELECTED);
                        return;
                    }
                    Command newCommand = new ChangeAppearanceCommand(color.getRGB(), strokeInt, MainFrame.getInstance().getProjectView().getCurrentMindMapView().getMapSelectionModel().getSelected());
                    MainFrame.getInstance().getProjectView().getCurrentMindMapView().getModel().getCommandManager().addCommand(newCommand);
                    strokeTextField.setText("3");
                    colorChooser.setColor(Color.CYAN);
                }
                catch (NumberFormatException n){
                    AppCore.getInstance().getMsgGenerator().createMessage(Problem.STROKE_HAS_TO_BE_NUMBER);
                }
                close();
            }
        });
        Button cancelBtn = new Button("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });

        southPanel.add(okBtn);
        southPanel.add(cancelBtn);

        JPanel strokePanel = new JPanel();
        JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel chooserPanel = new JPanel();

        JLabel strokeLabel = new JLabel("Stroke size:");
        strokeLabel.setFont(new Font("Serif", Font.BOLD, 25));
        strokePanel.add(strokeLabel);

        strokeTextField = new JTextField();
        strokeTextField.setHorizontalAlignment(0);
        strokeTextField.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        strokeTextField.setPreferredSize(new Dimension(30,30));
        strokeTextField.setText("3");
        strokePanel.add(strokeTextField);

        JLabel colorLabel = new JLabel("Choose color:");
        colorLabel.setFont(new Font("Serif", Font.BOLD, 25));
        colorPanel.add(colorLabel);

        colorChooser = new JColorChooser();
        colorChooser.setColor(Color.CYAN);
        chooserPanel.add(colorChooser);

        centerPanel.add(strokePanel);
        centerPanel.add(colorPanel);
        centerPanel.add(chooserPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        open();
    }

    public void open(){
        dialog.setVisible(true);
    }

    public void close(){
        dialog.setVisible(false);
    }
}
