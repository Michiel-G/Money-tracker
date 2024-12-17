package ticket;

import person.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvenSplitTicket extends Ticket{
    public EvenSplitTicket(TicketType ticketType, int price, Person person, List<Person> peoplePaying) {
        super(ticketType, price, person, peoplePaying);


    }


    @Override
    public int getMoneyOfPerson(Person person) {
        return getPrice()/ peoplePaying.size();
    }
}
