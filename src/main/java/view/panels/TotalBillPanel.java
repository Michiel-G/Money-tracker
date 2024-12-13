package view.panels;

import controller.PersonController;
import controller.TicketController;
import person.Person;
import ticket.Ticket;
import ticket.UnevenSplitTicket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TotalBillPanel  extends JPanel {
    private TicketController ticketController;
    private PersonController personController;
    private DefaultTableModel tableModel;
    private JButton resetButton;

    public TotalBillPanel(TicketController ticketController, PersonController personController) {
        this.ticketController = ticketController;
        this.personController = personController;

        List<Person> allPeopleDebts = personController.getAllPersons()
                .stream()
                .filter(person ->
                        !person.getDebts().isEmpty()
                )
                .collect(Collectors.toList());

        Object[][] data = new Object[allPeopleDebts.size()][allPeopleDebts.size()+1];
        for (int i = 0; i < allPeopleDebts.size(); i++) {
            Person indebtedPerson = allPeopleDebts.get(i);
            data[i][0] = indebtedPerson.getName();
            for (int j = 0; j < allPeopleDebts.size(); j++) {
                Person receivingPerson = allPeopleDebts.get(j);
                data[i][j+1] = indebtedPerson.getDebts().getOrDefault(receivingPerson, 0);
            }
        }
        List<String> columnList = new ArrayList<>();
        columnList.add("has to pay");
        columnList.addAll(allPeopleDebts.stream().map(Person::getName).collect(Collectors.toList()));
        String[]  columnNames =columnList.toArray(new String[allPeopleDebts.size()]);
        this.tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only the second column (index 1) is editable
                return false;
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

        this.resetButton = new JButton("reset tickets");


        this.add(scrollPane);
        this.add(resetButton);
        addResetButtonListener();
    }

    public void update(){
        List<Person> allPeopleDebts = personController.getAllPersons();

        Object[][] data = new Object[allPeopleDebts.size()][allPeopleDebts.size()+1];
        for (int i = 0; i < allPeopleDebts.size(); i++) {
            Person indebtedPerson = allPeopleDebts.get(i);
            data[i][0] = indebtedPerson.getName();
            for (int j = 0; j < allPeopleDebts.size(); j++) {
                Person receivingPerson = allPeopleDebts.get(j);
                data[i][j+1] = indebtedPerson.getDebts().getOrDefault(receivingPerson, 0);
            }
        }
        List<String> columnList = new ArrayList<>();
        columnList.add("has to pay");
        columnList.addAll(allPeopleDebts.stream().map(Person::getName).collect(Collectors.toList()));
        String[]  columnNames =columnList.toArray(new String[allPeopleDebts.size()]);
        this.tableModel.setRowCount(0);
        this.tableModel.setColumnIdentifiers(columnNames);

        for (Object[] row : data) {
            this.tableModel.addRow(row);
        }
    }
    public void addResetButtonListener() {
        this.resetButton.addActionListener(listener -> {
            System.out.println("helaowefaw");
            this.ticketController.removeAllTickets();
            this.personController.removeAllDebts();
            update();

        });
    }
}
