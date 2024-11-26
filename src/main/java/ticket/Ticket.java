package ticket;

import person.Person;

public class Ticket {
    private TicketType ticketType;
    private int moneyPaid;
    private Person person;

    public Ticket(TicketType ticketType, int moneyPaid, Person person) {
        this.ticketType = ticketType;
        this.moneyPaid = moneyPaid;
        this.person = person;
    }


}
