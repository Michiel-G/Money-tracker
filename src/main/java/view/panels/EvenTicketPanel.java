package view.panels;

import controller.PersonController;
import controller.TicketController;
import person.Person;
import ticket.EvenSplitTicket;
import ticket.TicketType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EvenTicketPanel extends JPanel {
    private TicketController ticketController;
    private PersonController personController;
    private TicketType ticketType;
    private boolean isEvenTicket;
    private int totalPrice;
    private List<Person> allPersons;

    TicketType[] ticketTypeArray = TicketType.values();
    private JComboBox ticketTypeComboBox;
    private JComboBox ticketOwnerComboBox;
    private JScrollPane peopleScrollPane;
    private JList<Object> allPeopleJlist;
    private JFormattedTextField totalPriceTextField;
    private JLabel totalPriceTextFieldLabel;
    private JButton createTicket;

    public EvenTicketPanel(TicketController ticketController, PersonController personController) {
        JLabel label = new JLabel("Add a even ticket");

        this.totalPriceTextField = new JFormattedTextField();
        this.totalPriceTextFieldLabel = new JLabel("Enter a price");
        this.ticketOwnerComboBox = new JComboBox();
        this.createTicket = new JButton("Create ticket");
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

        // TODO: remove this initializer person, this is a dummy value
        personController.addPerson(new Person("Bertje Blink"));
        personController.addPerson(new Person(" Blink"));

        this.allPersons = personController.getAllPeople();
        this.allPeopleJlist = new JList<>(allPersons.stream().map(Person::getName).toArray());
        this.allPeopleJlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.peopleScrollPane = new JScrollPane(allPeopleJlist);
        this.ticketOwnerComboBox = new JComboBox(allPersons.stream().map(Person::getName).toArray());
        this.ticketTypeComboBox = new JComboBox(ticketTypeArray);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new JLabel("Select the person who paid the ticket"));
        this.add(ticketOwnerComboBox);
        this.add(totalPriceTextFieldLabel);
        this.add(totalPriceTextField);
        this.add(new JLabel("Select people who need to pay back (use ctrl+click to select multiple)"));
        this.add(peopleScrollPane);
        this.add(new JLabel("Select the type of ticket"));
        this.add(ticketTypeComboBox);
        this.add(createTicket);

        addCreateTicketButtonListener();
        addTicketTypeComboBoxListener();
    }

    public void updatePeople(){
        this.allPersons = personController.getAllPeople();
        this.ticketOwnerComboBox.removeAllItems();
        for (Person person : allPersons) {
            this.ticketOwnerComboBox.addItem(person.getName());
        }
        this.allPeopleJlist=new JList<>(allPersons.stream().map(Person::getName).toArray());
        this.allPeopleJlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.peopleScrollPane.setViewportView(allPeopleJlist);
        this.peopleScrollPane.revalidate();
        this.peopleScrollPane.repaint();
    }

    public void addTicketTypeComboBoxListener() {
        this.ticketTypeComboBox.addActionListener(listener -> {
            ticketType = ticketTypeArray[ticketTypeComboBox.getSelectedIndex()];
        });
    }

    // TODO: fix the total price integer when moving the input verifier, it is ugly code right now :(
    public void addCreateTicketButtonListener() {
        this.createTicket.addActionListener(listener -> {
            List<Object> namesPeople = allPeopleJlist.getSelectedValuesList();
            List<String> actualNames = new ArrayList<>();
            for (Object namesPerson : namesPeople) {
                actualNames.add(namesPerson.toString());
            }
            ticketController.addTicket(new EvenSplitTicket(
                    ticketType,
                    totalPrice,
                    allPersons.get(ticketOwnerComboBox.getSelectedIndex()),
                    personController.peopleByNames(actualNames)));

        });
    }
}
