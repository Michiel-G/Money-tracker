package ticket;

import person.Person;

import java.util.ArrayList;
import java.util.List;

public class EvenSplitTicket extends Ticket{
    private List<Person> peoplePaying;
    public EvenSplitTicket(TicketType ticketType, int price, Person person, List<Person> peoplePaying) {
        super(ticketType, price, person);
        this.peoplePaying = peoplePaying;
    }

    public List<Person> getPeoplePaying() {
        return peoplePaying;
    }
}
