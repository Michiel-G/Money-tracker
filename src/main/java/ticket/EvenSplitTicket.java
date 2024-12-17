package ticket;

import person.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvenSplitTicket extends Ticket{
    private final Map<Person, Boolean> personPaidMap;
    public EvenSplitTicket(TicketType ticketType, int price, Person person, List<Person> peoplePaying) {
        super(ticketType, price, person, peoplePaying);
        this.personPaidMap = new HashMap<>();
        for (Person personLoop : peoplePaying) {
            this.personPaidMap.put(personLoop, false);
            personLoop.addDebt(person, price/(peoplePaying.size()+1));
        }


    }


    @Override
    public int getMoneyOfPerson(Person person) {
        return getPrice()/ peoplePaying.size();
    }
}
