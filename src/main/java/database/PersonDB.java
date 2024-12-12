package database;

import person.Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class PersonDB extends Database {

    private static PersonDB instance;
    private List<Person> db;
    private ArrayList<PropertyChangeListener> observers;

    public PersonDB() {
        this.db = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void addPerson(Person person) {
        db.add(person);
        notifyObservers("person", null, person);
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

    public List<Person> getAllPersons() {
        return db;
    }
}
