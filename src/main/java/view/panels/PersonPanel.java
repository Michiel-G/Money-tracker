package view.panels;

import person.Person;
import view.renderer.PersonRenderer;

import javax.swing.*;

public class PersonPanel extends JPanel {
    private JList<Person> personJList;
    private DefaultListModel<Person> personListModel;

    public PersonPanel() {
        JLabel label = new JLabel("Persons");

        personListModel = new DefaultListModel<>();
        personJList = new JList<>(personListModel);
        personJList.setCellRenderer(new PersonRenderer());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(personJList);
    }

    public void addPerson(Person person)
    {
        this.personListModel.addElement(person);
    }

    public void setPersonJList(JList<Person> personJList) {
        this.personJList = personJList;
    }
}
