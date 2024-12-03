package view.panels;

import javax.swing.*;

public class RegistrationButtonPanel extends JPanel {
    private JButton checkIn;

    public RegistrationButtonPanel()
    {
        JLabel label = new JLabel("Registration buttons");
        this.checkIn = new JButton("Add person");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(this.checkIn);
    }
}
