package controller;

import database.Database;
import database.PersonDB;
import person.Person;

public class PersonController {
    private PersonDB db;

    public PersonController(PersonDB db) {
        this.db = db;
    }

    public void addPerson(Person person) {
        db.addPerson(person);
    }
}
