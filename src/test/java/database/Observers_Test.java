package database;

import observers.EntryObserver;
import org.junit.Test;
import org.mockito.Mockito;
import person.Person;
import ticket.EvenSplitTicket;
import ticket.Ticket;
import ticket.TicketFactory;
import ticket.TicketType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Observers_Test {
    @Test
    public void Observer_T (){
        TicketDB ticketDB = TicketDB.getInstance();
        Person person1 = new Person("John");
        Person person2 = new Person("Conor");
        Person person3 = new Person("Frank");

        TicketFactory ticketFactory = new TicketFactory();
        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);

        EvenSplitTicket ticket1 = ticketFactory.createEvenSplitTicket(TicketType.RESTAURANT, 20, person1, personList);

        ticketDB.attach(new EntryObserver());
        ticketDB.addTicket(ticket1);
    }
}
