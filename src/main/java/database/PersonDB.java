package database;

import person.Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Person> getPeopleByNames(List<String> namesFilter){
        return this.db.stream().filter(person -> namesFilter.contains(person.getName()))
                .collect(Collectors.toList());
    }

    public Person getPersonByName(String nameFilter){
        return this.db.stream().filter(person -> person.getName().equals(nameFilter)).findFirst().orElseThrow(() -> new RuntimeException("Person not found"));
    }
}
