package ticket.decorator;

import person.Person;
import ticket.Ticket;

public abstract class PriceDecorator extends Ticket{
    Ticket wrappee;

    public PriceDecorator(Ticket wrappee) {
        super(wrappee.getTicketType(), wrappee.getPrice(), wrappee.getOwner(), wrappee.getPeoplePaying());
        this.wrappee = wrappee;
    }

    @Override
    public abstract int calculateTotalPrice();
}
