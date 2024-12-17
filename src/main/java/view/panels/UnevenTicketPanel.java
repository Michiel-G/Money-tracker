package view.panels;

import controller.PersonController;
import controller.TicketController;
import person.Person;
import ticket.EvenSplitTicket;
import ticket.TicketType;
import ticket.UnevenSplitTicket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnevenTicketPanel extends JPanel {
    private TicketController ticketController;
    private PersonController personController;
    private TicketType ticketType;
    private boolean isEvenTicket;
    private int totalPrice;
    private List<Person> allPersons;

    TicketType[] ticketTypeArray = TicketType.values();
    private JComboBox ticketTypeComboBox;
    private JComboBox ticketOwnerComboBox;
    private JFormattedTextField totalPriceTextField;
    private JLabel totalPriceTextFieldLabel;
    private JButton createTicket;
    private DefaultTableModel tableModel;

    public UnevenTicketPanel(TicketController ticketController, PersonController personController) {

        this.totalPriceTextField = new JFormattedTextField();
        this.totalPriceTextFieldLabel = new JLabel("Enter a price");
        this.ticketOwnerComboBox = new JComboBox();
        this.createTicket = new JButton("Create ticket");
        this.ticketController = ticketController;
        this.personController = personController;
        this.allPersons = personController.getAllPersons();

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

        Object[][] data = new Object[allPersons.size()][2];
        for (int i = 0; i < allPersons.size(); i++) {
            Person person = allPersons.get(i);
            data[i][0] = person.getName();
            data[i][1] = 0;
        }

        String[] columnNames = {"Name", "Value"};

        this.tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only the second column (index 1) is editable
                return column == 1;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // Set the type for each column: String for the first and Integer for the second
                return columnIndex == 1 ? Integer.class : String.class;
            }
        };

        // Create the JTable
        JTable table = new JTable(tableModel);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        this.ticketOwnerComboBox = new JComboBox(allPersons.stream().map(Person::getName).toArray());
        this.ticketTypeComboBox = new JComboBox(ticketTypeArray);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new JLabel("Select the person who paid the ticket"));
        this.add(ticketOwnerComboBox);
        this.add(totalPriceTextFieldLabel);
        this.add(totalPriceTextField);
        this.add(new JLabel("Select the type of ticket"));
        this.add(ticketTypeComboBox);
        this.add(new JLabel("Put the money owed by each person"));
        this.add(scrollPane);
        this.add(createTicket);


        addCreateTicketButtonListener();
        addTicketTypeComboBoxListener();
    }

    public void updatePeople(){
        this.allPersons = personController.getAllPersons();
        this.ticketOwnerComboBox.removeAllItems();
        for (Person person : allPersons) {
            this.ticketOwnerComboBox.addItem(person.getName());
        }
        this.tableModel.setRowCount(0);
        Object[][] newData = new Object[allPersons.size()][2];
        for (int i = 0; i < allPersons.size(); i++) {
            Person person = allPersons.get(i);
            newData[i][0] = person.getName();
            newData[i][1] = 0;
        }
        for (Object[] row : newData) {
            this.tableModel.addRow(row);
        }

    }

    public void addTicketTypeComboBoxListener() {
        this.ticketTypeComboBox.addActionListener(listener -> {
            ticketType = ticketTypeArray[ticketTypeComboBox.getSelectedIndex()];
        });
    }

    // TODO: fix the total price integer when moving the input verifier, it is ugly code right now :(
    public void addCreateTicketButtonListener() {
        this.createTicket.addActionListener(listener -> {
            List<String> actualNames = new ArrayList<>();
            Map<Person, Integer> peoplePriceMap = new HashMap<>();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Person person = personController.personByName((String) tableModel.getValueAt(i, 0));
                Integer value = (Integer) tableModel.getValueAt(i, 1);
                System.out.println(tableModel.getValueAt(i, 1));
                if (value!=0){
                    System.out.println("debt added for:"+ person.getName());
                    peoplePriceMap.put(person, value);
                    actualNames.add((String) tableModel.getValueAt(i, 0));
                }

            }
            ticketController.addTicket(new UnevenSplitTicket(
                    ticketType,
                    totalPrice,
                    allPersons.get(ticketOwnerComboBox.getSelectedIndex()),
                    peoplePriceMap,
                    personController.peopleByNames(actualNames)
                    ));

        });
    }
}
