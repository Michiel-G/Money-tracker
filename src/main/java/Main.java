import controller.PersonController;
import controller.TicketController;
import database.PersonDB;
import database.TicketDB;
import person.Person;
import ticket.*;
import view.ViewFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        PersonDB personDB = PersonDB.getInstance();
        TicketDB ticketDB = TicketDB.getInstance();

        PersonController personController = new PersonController(personDB);
        TicketController ticketController = new TicketController(ticketDB);
        personController.addPerson(new Person("Max"));
        personController.addPerson(new Person("Michiel"));
        ViewFrame view = new ViewFrame(personController, ticketController);
        view.initialize();

        personDB.attach(view);
        ticketDB.attach(view);


    }
}