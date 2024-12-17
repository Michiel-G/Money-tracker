package ticket;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import person.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Ticket_Test {
    @Test
    public void t_pay_off() throws Exception
    {
        Person person1 = new Person("John");
        Person person2 = new Person("Conor");
        Person person3 = new Person("Frank");

        TicketFactory ticketFactory = new TicketFactory();
        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        Map<Person, Integer> unevenList = new HashMap<>();
        unevenList.put(person1, 15);
        unevenList.put(person2, 5);

        // create tickets
        EvenSplitTicket ticket1 = ticketFactory.createEvenSplitTicket(TicketType.RESTAURANT, 20, person1, personList);
        UnevenSplitTicket ticket2 = ticketFactory.createUnevenSplitTicket(TicketType.RESTAURANT, 20, person1, unevenList);
        EvenSplitTicket ticket3 = ticketFactory.createEvenSplitTicket(TicketType.CONCERT, 50, person1, personList);

        // create global tickets to pay off debts

//        ticket2.payTicket(person1);
//        Assert.assertFalse(ticket2.ticketPayedOff()); // not payed off
//        ticket2.payTicket(person2);
//        Assert.assertTrue(ticket2.ticketPayedOff()); // payed off
//
//        ticket1.payTicket(person1);
//        Assert.assertFalse(ticket1.ticketPayedOff()); // not payed off
//        ticket1.payTicket(person2);
//        Assert.assertFalse(ticket1.ticketPayedOff()); // not payed off
//        ticket1.payTicket(person3);
//        Assert.assertTrue(ticket1.ticketPayedOff()); // payed off
//        RuntimeException thrown = Assert.assertThrows(RuntimeException.class, () ->ticket3.payTicket(person1));
//
//        Assert.assertTrue(thrown.getMessage().contains("does not have enough money"));
    }
}
