package view.panels;

import controller.PersonController;
import controller.TicketController;
import person.Person;
import ticket.EvenSplitTicket;
import ticket.TicketType;
import ticket.UnevenSplitTicket;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TicketPanel extends JPanel {
    private TicketController ticketController;
    private PersonController personController;
    private TicketType ticketType;
    private boolean isEvenTicket;
    private int totalPrice;
    private List<Person> allPersons;

    TicketType[] ticketTypeArray = TicketType.values();
    private JComboBox ticketTypeComboBox;
    private JComboBox ticketOwnerComboBox;
    private JRadioButton evenTicketButton;
    private JRadioButton unevenTicketButton;
    private JFormattedTextField totalPriceTextField;
    private JLabel totalPriceTextFieldLabel;
    private JButton addPerson;
    private JButton createTicket;

    public TicketPanel(TicketController ticketController, PersonController personController) {
        JLabel label = new JLabel("Add a ticket");

        this.evenTicketButton = new JRadioButton("Even ticket");
        this.unevenTicketButton = new JRadioButton("Uneven ticket");
        this.evenTicketButton.setActionCommand("Even");
        this.unevenTicketButton.setActionCommand("Uneven");
        this.totalPriceTextField = new JFormattedTextField();
        this.totalPriceTextFieldLabel = new JLabel("Enter a price");
        this.ticketOwnerComboBox = new JComboBox();
        this.createTicket = new JButton("Create ticket");
        this.addPerson = new JButton("Add a person");

        this.ticketController = ticketController;
        this.personController = personController;

        // TODO: remove this inner (anonymous) class and move it to another class "NumberInputVerifier".
        totalPriceTextField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = ((JTextField) input).getText();
                try {
                    int parsedInt = Integer.parseInt(text);
                    if (parsedInt < 0) return false;
                    totalPrice = parsedInt;
                } catch (NumberFormatException e) {
                    return false;
                }
                return true;
            }
        });

        totalPriceTextFieldLabel.setLabelFor(totalPriceTextField);

        JLabel totalPriceTextFieldLabel = new JLabel("Enter a total price");
        totalPriceTextFieldLabel.setLabelFor(totalPriceTextField);

        ButtonGroup evenUnevenTicketGroup = new ButtonGroup();
        evenUnevenTicketGroup.add(evenTicketButton);
        evenUnevenTicketGroup.add(unevenTicketButton);

        // TODO: remove this initializer person, this is a dummy value
        personController.addPerson(new Person("Bertje Blink"));

        this.allPersons = personController.getAllPersons();
        this.ticketOwnerComboBox = new JComboBox(allPersons.stream().map(Person::getName).toArray());
        this.ticketTypeComboBox = new JComboBox(ticketTypeArray);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(evenTicketButton);
        this.add(unevenTicketButton);
        this.add(ticketOwnerComboBox);
        this.add(totalPriceTextFieldLabel);
        this.add(totalPriceTextField);
        this.add(addPerson);
        this.add(createTicket);

        addCreateTicketButtonListener();
        addTicketTypeComboBoxListener();
        addEvenUnevenRadioButtonListener();
    }

    public void addEvenUnevenRadioButtonListener() {
        this.ticketTypeComboBox.addActionListener(listener -> {
            if (listener.getActionCommand().toUpperCase().equals("EVEN")) {
                // Add an even ticket
                isEvenTicket = true;
            } else if (listener.getActionCommand().toUpperCase().equals("UNEVEN")) {
                // Add an uneven ticket
                isEvenTicket = false;
            }
        });
    }

    public void addTicketTypeComboBoxListener() {
        this.ticketTypeComboBox.addActionListener(listener -> {
            ticketType = ticketTypeArray[ticketTypeComboBox.getSelectedIndex()];
        });
    }

    public void addPersonButtonListener() {
        this.addPerson.addActionListener(listener -> {
            if (isEvenTicket) {

            }
        });
    }

    // TODO: fix the total price integer when moving the input verifier, it is ugly code right now :(
    public void addCreateTicketButtonListener() {
        this.createTicket.addActionListener(listener -> {
            if (isEvenTicket) {
                ticketController.addTicket(new EvenSplitTicket(ticketType, totalPrice, allPersons.get(ticketOwnerComboBox.getSelectedIndex()), new ArrayList<>()));
            } else {
                ticketController.addTicket(new UnevenSplitTicket(ticketType, totalPrice, allPersons.get(ticketOwnerComboBox.getSelectedIndex()), new HashMap<>()));
            }
        });
    }
}
