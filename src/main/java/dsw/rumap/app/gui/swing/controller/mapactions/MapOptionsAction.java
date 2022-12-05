package dsw.rumap.app.gui.swing.controller.mapactions;

import com.sun.tools.javac.Main;
import dsw.rumap.app.gui.swing.controller.AbstractRumapActions;
import dsw.rumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MapOptionsAction extends AbstractRumapActions {

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
        dialog.setMinimumSize(new Dimension(650, 460));
        dialog.setMaximumSize(new Dimension(650, 460));
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

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

        dialog.add(strokePanel);
        dialog.add(colorPanel);
        dialog.add(chooserPanel);

        dialog.setVisible(true);

    }
}
