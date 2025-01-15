package view.panels;

import controller.PersonController;
import controller.TicketController;
import person.Person;
import ticket.*;
import ticket.decorator.Currency;
import ticket.decorator.CurrencyDecorator;
import ticket.decorator.TaxDecorator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EvenTicketPanel extends JPanel {
    private TicketController ticketController;
    private PersonController personController;
    private TicketType ticketType;
    private int totalPrice;
    private List<Person> allPersons;
    private final TicketType[] ticketTypeArray = TicketType.values();
    private JComboBox ticketTypeComboBox;
    private JComboBox ticketOwnerComboBox;
    private JComboBox taxDecoratorCombobox;
    private final String[] taxList = {"", "6%", "21%"};
    private JComboBox currencyDecoratorCombobox;
    private final String[] currencyList = {"", "Euro", "Dollar", "Zlotych"};
    private JScrollPane peopleScrollPane;
    private JList<Object> allPeopleJlist;
    private JFormattedTextField totalPriceTextField;
    private JLabel totalPriceTextFieldLabel;
    private JButton createTicket;
    private TicketFactory ticketFactory;

    public EvenTicketPanel(TicketController ticketController, PersonController personController) {
        this.ticketFactory = new TicketFactory();
        this.totalPriceTextField = new JFormattedTextField();
        this.totalPriceTextFieldLabel = new JLabel("Enter a price");
        this.ticketOwnerComboBox = new JComboBox();
        this.createTicket = new JButton("Create ticket");
        this.ticketController = ticketController;
        this.personController = personController;

        this.taxDecoratorCombobox = new JComboBox(taxList);
        this.currencyDecoratorCombobox = new JComboBox(currencyList);

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

        this.add(new JLabel("Select a tax (optional)"));
        this.add(taxDecoratorCombobox);
        this.add(new JLabel("Select a currency (optional)"));
        this.add(currencyDecoratorCombobox);
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
        this.allPeopleJlist = new JList<>(allPersons.stream().map(Person::getName).toArray());
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

    public void addCreateTicketButtonListener() {
        this.createTicket.addActionListener(listener -> {
            List<Object> namesPeople = allPeopleJlist.getSelectedValuesList();
            List<String> actualNames = new ArrayList<>();
            for (Object namesPerson : namesPeople) {
                actualNames.add(namesPerson.toString());
            }

            actualNames.remove(allPersons.get(ticketOwnerComboBox.getSelectedIndex()).getName());
            Ticket evenSplitTicket =ticketFactory.createEvenSplitTicket(ticketType,
                    totalPrice,
                    allPersons.get(ticketOwnerComboBox.getSelectedIndex()),
                    personController.peopleByNames(actualNames));

            if (taxDecoratorCombobox.getSelectedItem().toString().equals("6%")) {
                evenSplitTicket = new TaxDecorator(evenSplitTicket, 1.06);
            } else if (taxDecoratorCombobox.getSelectedItem().toString().equals("21%")) {
                evenSplitTicket = new TaxDecorator(evenSplitTicket, 1.21);
            }

            if (currencyDecoratorCombobox.getSelectedItem().toString().equals("Dollar")) {
                evenSplitTicket = new CurrencyDecorator(evenSplitTicket, Currency.Dollar);
            } else if (currencyDecoratorCombobox.getSelectedItem().toString().equals("Zlotych")) {
                evenSplitTicket = new CurrencyDecorator(evenSplitTicket, Currency.Zlotych);
            }

            evenSplitTicket.setPrice(evenSplitTicket.calculateTotalPrice());
            evenSplitTicket.addDebts();
            System.out.println(evenSplitTicket.calculateTotalPrice());
            ticketController.addTicket(evenSplitTicket);
        });
    }
}
