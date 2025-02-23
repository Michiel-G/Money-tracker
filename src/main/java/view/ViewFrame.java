package view;


import controller.PersonController;
import controller.TicketController;
import person.Person;
import view.panels.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ViewFrame extends JFrame implements PropertyChangeListener {
    RegistrationButtonPanel buttons;
    PersonPanel panel;
    EvenTicketPanel evenTicketPanel;
    UnevenTicketPanel unevenTicketPanel;
    PersonController personController;
    TicketController ticketController;
    TotalBillPanel totalBillPanel;

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
        evenTicketPanel = new EvenTicketPanel(ticketController, personController);
        unevenTicketPanel = new UnevenTicketPanel(ticketController, personController);

        personController.getAllPeople().forEach((person -> {
          panel.addPerson(person);
        }));

        // tech for creating tabs, add components here
        JTabbedPane ticketTabbedPane = new JTabbedPane();
        ticketTabbedPane.addTab("Even ticket", evenTicketPanel);
        ticketTabbedPane.addTab("Uneven ticket", unevenTicketPanel);

        this.totalBillPanel = new TotalBillPanel(ticketController, personController);

        // tech for creating tabs, add components here
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Create ticket", ticketTabbedPane);
        tabbedPane.addTab("Create people", buttons);
        tabbedPane.addTab("Get total bill", totalBillPanel);
        // when tab changes
        tabbedPane.addChangeListener(e -> {
            evenTicketPanel.updatePeople();
            unevenTicketPanel.updatePeople();
            totalBillPanel.update();
        });
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
