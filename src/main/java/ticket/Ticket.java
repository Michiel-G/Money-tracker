package ticket;

import person.Person;

import java.util.List;

public abstract class Ticket {
    private final TicketType ticketType;
    private final int price;
    private final Person owner;
    protected final List<Person> peoplePaying;

    public Ticket(TicketType ticketType, int price, Person owner, List<Person> peoplePaying) {
        this.ticketType = ticketType;
        this.price = price;
        this.owner = owner;
        this.peoplePaying = peoplePaying;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public int getPrice() {
        return price;
    }

    public Person getOwner() {
        return owner;
    }
    public List<Person> getPeoplePaying() {
        return peoplePaying;
    }
    public abstract int getMoneyOfPerson(Person person);


}
