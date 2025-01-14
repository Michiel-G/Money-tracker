package ticket;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import person.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;


public class Ticket_Test {

    @Test
    public void t_incorrect_unevensplit_moneysplit()
    {
        Person person1 = new Person("John");
        Person person2 = new Person("Conor");

        TicketFactory ticketFactory = new TicketFactory();
        Map<Person, Integer> unevenList = new HashMap<>();
        unevenList.put(person1, 15);
        unevenList.put(person2, 5);

        // create invalid ticket
        assertThrows(RuntimeException.class, ()->
                ticketFactory.createUnevenSplitTicket(
                        TicketType.RESTAURANT,
                        30,
                        person1,
                        unevenList));

    }

    @Test
    public void t_correct_unevensplit_moneysplit()
    {
        Person person1 = new Person("John");
        Person person2 = new Person("Conor0");

        TicketFactory ticketFactory = new TicketFactory();
        Map<Person, Integer> unevenList = new HashMap<>();
        unevenList.put(person1, 15);
        unevenList.put(person2, 5);
        try {
            // create valid ticket
            ticketFactory.createUnevenSplitTicket(
                    TicketType.RESTAURANT,
                    20,
                    person1,
                    unevenList);
        } catch (Exception e) {
            fail("Ticket was not valid");
        }
    }
}
