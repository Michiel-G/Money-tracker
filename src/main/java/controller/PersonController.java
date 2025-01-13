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

    public List<Person> getAllPeople() {
        return db.getAllPeople();
    }
    public void removeAllDebts(){
        db.removeAllDebts();
    }
    public List<Person> peopleByNames(List<String> names) {
        return db.getPeopleByNames(names);
    }
    public Person personByName(String name) {
        return db.getPersonByName(name);
    }

}
