package view;


import view.panels.RegistrationButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ViewFrame extends JFrame implements PropertyChangeListener {
    RegistrationButtonPanel buttons;

    public void initialize()
    {
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Pass the controller to the ButtonPanel
        buttons = new RegistrationButtonPanel();

        this.add(buttons);
        this.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
