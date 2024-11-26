package ticket;

import person.Person;

import java.util.List;
import java.util.Map;

public class TicketFactory {
    public Ticket createEvenSplitTicket(TicketType ticketType, int price, Person person, List<Person> peoplePaying){
        return new EvenSplitTicket(ticketType, price, person,  peoplePaying);
    }
    public Ticket createUnevenSplitTicket(TicketType ticketType, int price, Person person, Map<Person, Integer> moneySplitMap){
        return new UnevenSplitTicket(ticketType, price, person, moneySplitMap);
    };
}
