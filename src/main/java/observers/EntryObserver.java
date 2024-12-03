package observers;


import ticket.Ticket;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EntryObserver implements PropertyChangeListener {
    public EntryObserver() {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Ticket from "+((Ticket)evt.getNewValue()).getPerson().getName());
    }
}
