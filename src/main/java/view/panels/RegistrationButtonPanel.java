package view.panels;

import controller.PersonController;
import person.Person;

import javax.swing.*;

public class RegistrationButtonPanel extends JPanel {
    private final JButton addPerson;
    private PersonController personController;
    public static JTextArea nameTextArea = new JTextArea("");


    public RegistrationButtonPanel(PersonController personController)
    {
        JLabel label = new JLabel("Registration buttons");
        this.addPerson = new JButton("Add person");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(nameTextArea);
        this.add(this.addPerson);

        this.personController = personController;
        addCheckInButtonActionListener();
    }

    public void addCheckInButtonActionListener()
    {
        this.addPerson.addActionListener(listener ->
        {
            personController.addPerson(new Person(nameTextArea.getText()));
        });
    }
}
