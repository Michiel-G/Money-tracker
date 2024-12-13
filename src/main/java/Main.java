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
        PersonDB personDB = new PersonDB();
        TicketDB ticketDB = new TicketDB();

        PersonController personController = new PersonController(personDB);
        TicketController ticketController = new TicketController(ticketDB);
        ViewFrame view = new ViewFrame(personController, ticketController);
        view.initialize();

        personDB.attach(view);
        ticketDB.attach(view);


    }
}