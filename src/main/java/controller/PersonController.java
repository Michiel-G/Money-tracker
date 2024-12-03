package controller;

import database.PersonDB;
import person.Person;

import java.util.List;

public class PersonController {
    private PersonDB db;

    public PersonController(PersonDB db) {
        this.db = db;
    }

    public void addPerson(Person person) {
        db.addPerson(person);
    }

    public List<Person> getAllPersons() {
        return db.getAllPersons();
    }
}
