package ticket;

import person.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvenSplitTicket extends Ticket{
    public EvenSplitTicket(TicketType ticketType, int price, Person owner, List<Person> peoplePaying) {
        super(ticketType, price, owner, peoplePaying);
    }

    @Override
    public void addDebts() {
        for (Person person : peoplePaying) {
            person.addDebt(getOwner(),price/(peoplePaying.size()+1));
        }
    }

    @Override
    public int calculateTotalPrice() {
        return this.getPrice();
    }
}
