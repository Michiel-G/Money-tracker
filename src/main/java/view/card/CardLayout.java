package view.card;

import javax.swing.*;
import java.awt.*;

public class CardLayout extends JPanel {
    private JPanel comboBoxPane = new JPanel(); //use FlowLayout
    private String comboBoxItems[] = { "Patat", "Peer" };


    public CardLayout() {
        comboBoxPane.setLayout(new BoxLayout(comboBoxPane, BoxLayout.Y_AXIS));
    }
    JComboBox cb = new JComboBox(comboBoxItems);
}
