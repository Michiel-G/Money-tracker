package database;

import person.Person;

import java.beans.PropertyChangeListener;

public abstract class Database {
    Database() {

    }

    public abstract void attach(PropertyChangeListener observer);

    public abstract void notifyObservers(String propertyName, Object oldValue, Object newValue);
}
