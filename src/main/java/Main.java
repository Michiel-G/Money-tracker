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
        Person person1 = new Person("John");
        Person person2 = new Person("Conor");
        Person person3 = new Person("Frank");
        person1.setWalletAmount(30);
        person2.setWalletAmount(20);
        person3.setWalletAmount(20);


        PersonDB personDB = new PersonDB();
        TicketDB ticketDB = new TicketDB();

        PersonController personController = new PersonController(personDB);
        TicketController ticketController = new TicketController(ticketDB);
        ViewFrame view = new ViewFrame(personController, ticketController);
        view.initialize();

        personDB.attach(view);
        ticketDB.attach(view);

        TicketFactory ticketFactory = new TicketFactory();
        EvenSplitTicket ticket1 = ticketFactory.createEvenSplitTicket(TicketType.RESTAURANT, 20, person1, List.of(person1, person2, person3));
        UnevenSplitTicket ticket2 = ticketFactory.createUnevenSplitTicket(TicketType.RESTAURANT, 20, person1, Map.ofEntries(
                Map.entry(person1, 15),
                Map.entry(person2, 5)
        ));
        EvenSplitTicket ticket3 = ticketFactory.createEvenSplitTicket(TicketType.CONCERT, 50, person1, List.of(person1, person2, person3));
        System.out.println("uneven ticket");
        ticket2.payTicket(person1);
        System.out.println("only 1 person paid: "+ticket2.ticketPayedOff());
        ticket2.payTicket(person2);
        System.out.println("everyone paid: "+ticket2.ticketPayedOff());

        System.out.println("even ticket");

        ticket1.payTicket(person1);
        System.out.println("only 1 person paid: "+ticket1.ticketPayedOff());
        ticket1.payTicket(person2);
        System.out.println("2 people paid: "+ticket1.ticketPayedOff());
        ticket1.payTicket(person3);
        System.out.println("everyone people paid: "+ticket1.ticketPayedOff());
        System.out.println("this will not be possible and throw error");
        // ticket3.payTicket(person1);


    }
}