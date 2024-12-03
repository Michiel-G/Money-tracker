package ticket;

import person.Person;

import java.util.List;
import java.util.Map;

public class TicketFactory {
    public EvenSplitTicket createEvenSplitTicket(TicketType ticketType, int price, Person person, List<Person> peoplePaying){
        return new EvenSplitTicket(ticketType, price, person,  peoplePaying);
    }
    public UnevenSplitTicket createUnevenSplitTicket(TicketType ticketType, int price, Person person, Map<Person, Integer> moneySplitMap){
        return new UnevenSplitTicket(ticketType, price, person, moneySplitMap);
    };
}
