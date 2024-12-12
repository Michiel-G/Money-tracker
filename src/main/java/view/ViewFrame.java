package view;


import controller.PersonController;
import controller.TicketController;
import person.Person;
import ticket.Ticket;
import view.panels.PersonPanel;
import view.panels.RegistrationButtonPanel;
import view.panels.TicketPanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ViewFrame extends JFrame implements PropertyChangeListener {
    RegistrationButtonPanel buttons;
    PersonPanel panel;
    TicketPanel ticketPanel;
    PersonController personController;
    TicketController ticketController;

    public ViewFrame(PersonController personController, TicketController ticketController) {
        this.personController = personController;
        this.ticketController = ticketController;
    }

    public void initialize() {
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Pass the controller to the ButtonPanel
        buttons = new RegistrationButtonPanel(personController);
        panel = new PersonPanel();
        ticketPanel = new TicketPanel(ticketController, personController);

        personController.getAllPersons().forEach((person -> {
          panel.addPerson(person);
        }));

        this.add(ticketPanel);
        // TODO: clean this up
        // this.add(buttons);
        // this.add(panel);
        this.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Received event: " + evt.getPropertyName());
        if (evt.getPropertyName().equals("person")) {
            Person person = (Person) evt.getNewValue();
            System.out.println("Adding a person to the panel");
            panel.addPerson(person);
        }
    }
}
