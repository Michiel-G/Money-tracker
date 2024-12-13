package view.renderer;

import person.Person;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class PersonRenderer extends JLabel implements ListCellRenderer<Person> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Person> list, Person value, int index, boolean isSelected, boolean cellHasFocus) {
        String name = value.getName();

        setText(name);
        return this;
    }
}
