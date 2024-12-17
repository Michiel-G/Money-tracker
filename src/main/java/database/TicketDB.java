package database;

import person.Person;
import ticket.Ticket;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class TicketDB extends Database {

    private static TicketDB instance;
    private List<Ticket> db;
    private ArrayList<PropertyChangeListener> observers;


    private TicketDB() {
        this.db = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void removeAllTickets(){
        System.out.println("ahwufeiphaw");
        this.db = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        db.add(ticket);
        notifyObservers("ticket", null, ticket);
    }

    public List<Ticket> getAllTickets(){
        return db;
    }

    public static TicketDB getInstance() {
        if (instance == null) {
            instance = new TicketDB();
        }
        return instance;
    }

    @Override
    public void attach(PropertyChangeListener observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String propertyName, Object oldValue, Object newValue) {
        for (PropertyChangeListener observer : observers) {
            observer.propertyChange(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
        }
    }
}
