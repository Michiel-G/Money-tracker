package ticket.decorator;

import person.Person;
import ticket.Ticket;

public class TaxDecorator extends PriceDecorator{
    private double taxRate;

    public TaxDecorator(Ticket wrappee, double taxRate) {
        super(wrappee);
        this.taxRate = taxRate;
        calculateTotalPrice();
    }

    @Override
    public int calculateTotalPrice() {
        return (int) (this.getPrice() * taxRate);
    }

    @Override
    public void addDebts() {
        for (Person person : peoplePaying) {
            person.addDebt(getOwner(),price/(peoplePaying.size()+1));
        }
    }
}
