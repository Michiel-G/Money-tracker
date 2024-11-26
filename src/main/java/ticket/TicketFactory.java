package ticket;

import person.Person;

import java.util.List;
import java.util.Map;

public interface TicketFactory {
    Ticket createEvenSplitTicket(int price, Person person, List<Person> peoplePaying);
    Ticket createUnevenSplitTicket(int price, Person person, Map<Person, Integer> moneySplitMap);
}
