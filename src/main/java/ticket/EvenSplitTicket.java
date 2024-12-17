package ticket;

import person.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvenSplitTicket extends Ticket{
    public EvenSplitTicket(TicketType ticketType, int price, Person owner, List<Person> peoplePaying) {
        super(ticketType, price, owner, peoplePaying);
        for (Person person : peoplePaying) {
            person.addDebt(owner,price/(peoplePaying.size()+1));
        }
    }


    @Override
    public int getMoneyOfPerson(Person person) {
        return getPrice()/ peoplePaying.size();
    }
}
