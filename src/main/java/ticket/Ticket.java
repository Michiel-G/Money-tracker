package ticket;

import person.Person;

public class Ticket {
    private final TicketType ticketType;
    private final int price;
    private final Person person;

    public Ticket(TicketType ticketType, int price, Person person) {
        this.ticketType = ticketType;
        this.price = price;
        this.person = person;
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

}
