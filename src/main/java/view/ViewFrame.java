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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        this.setLayout(new BorderLayout());


        // Pass the controller to the ButtonPanel
        buttons = new RegistrationButtonPanel(personController);
        panel = new PersonPanel();
        ticketPanel = new TicketPanel(ticketController, personController);

        personController.getAllPersons().forEach((person -> {
          panel.addPerson(person);
        }));

        // tech for creating tabs, add components here
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Create ticket", ticketPanel);
        tabbedPane.addTab("Create people", buttons);
        tabbedPane.addTab("Get total bill", new JLabel("Content for Tab 3", SwingConstants.CENTER));

        // set tab as
        this.add(tabbedPane, BorderLayout.CENTER);
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
