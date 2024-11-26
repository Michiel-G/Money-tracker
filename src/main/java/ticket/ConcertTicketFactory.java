package ticket;

import person.Person;

import java.util.List;
import java.util.Map;

public class ConcertTicketFactory implements TicketFactory{
    @Override
    public Ticket createEvenSplitTicket(int price, Person person, List<Person> peoplePaying){
        return new EvenSplitTicket(TicketType.CONCERT, price, person,  peoplePaying);

    }

    @Override
    public Ticket createUnevenSplitTicket(int price, Person person, Map<Person, Integer> moneySplitMap) {
        return new UnevenSplitTicket(TicketType.CONCERT, price, person, moneySplitMap);
    }
}
