package dsw.rumap.app.gui.swing.controller.mapactions;

import com.sun.tools.javac.Main;
import dsw.rumap.app.gui.swing.controller.AbstractRumapActions;
import dsw.rumap.app.gui.swing.view.MainFrame;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
@Getter
public class MapOptionsAction extends AbstractRumapActions {

    private JTextField strokeTF;

    public MapOptionsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Options");
        putValue(SHORT_DESCRIPTION, "Options");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog = new JDialog(MainFrame.getInstance(), "Options", true);
        dialog.setLocation((int)MainFrame.getInstance().getLocation().getX()+MainFrame.getInstance().getWidth()/6, (int)MainFrame.getInstance().getLocation().getY()+MainFrame.getInstance().getHeight()/6);
        dialog.setMinimumSize(new Dimension(650, 490));
        dialog.setMaximumSize(new Dimension(650, 490));
        dialog.setLayout(new BorderLayout(5, 15));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        dialog.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        dialog.add(southPanel, BorderLayout.SOUTH);

        Button okBtn = new Button("OK");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stroke = MainFrame.getInstance().getActionManager().getMapOptionsAction().getStrokeTF().getText();
                Integer strokeInt = Integer.parseInt(stroke);
            }
        });
        Button cancelBtn = new Button("Cancel");

        southPanel.add(okBtn);
        southPanel.add(cancelBtn);

        JPanel strokePanel = new JPanel();
        JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel chooserPanel = new JPanel();

        JLabel strokeLabel = new JLabel("Stroke size:");
        strokeLabel.setFont(new Font("Serif", Font.BOLD, 25));
        strokePanel.add(strokeLabel);

        JTextField strokeTextField = new JTextField();
        strokeTextField.setHorizontalAlignment(0);
        strokeTextField.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        strokeTextField.setPreferredSize(new Dimension(30,30));
        strokePanel.add(strokeTextField);

        JLabel colorLabel = new JLabel("Choose color:");
        colorLabel.setFont(new Font("Serif", Font.BOLD, 25));
        colorPanel.add(colorLabel);

        JColorChooser colorChooser = new JColorChooser();
        chooserPanel.add(colorChooser);

        centerPanel.add(strokePanel);
        centerPanel.add(colorPanel);
        centerPanel.add(chooserPanel);

        dialog.setVisible(true);

    }
}
