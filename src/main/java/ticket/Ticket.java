package ticket;

import person.Person;

import java.util.List;

public abstract class Ticket {
    private final TicketType ticketType;
    private final int price;
    private final Person person;
    // add the person who owns the ticket too!
    protected final List<Person> peoplePaying;

    public Ticket(TicketType ticketType, int price, Person person, List<Person> peoplePaying) {
        this.ticketType = ticketType;
        this.price = price;
        this.person = person;
        this.peoplePaying = peoplePaying;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public int getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    public abstract boolean ticketPayedOff();


    public abstract void payTicket(Person person);
    public List<Person> getPeoplePaying() {
        return peoplePaying;
    }
    public abstract int getMoneyOfPerson(Person person);


}
