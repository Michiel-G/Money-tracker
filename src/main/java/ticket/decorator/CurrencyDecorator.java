package ticket.decorator;

import person.Person;
import ticket.Ticket;

public class CurrencyDecorator extends PriceDecorator {
    private Currency currency;

    public CurrencyDecorator(Ticket wrappee, Currency currency) {
        super(wrappee);
        this.currency = currency;
    }

    @Override
    public int calculateTotalPrice() {
        int convertedPrice = wrappee.getPrice();
        if (currency == Currency.Dollar) {
            convertedPrice = (int) (convertedPrice / 1.10);
        } else if (currency == Currency.Zlotych) {
            convertedPrice = convertedPrice / 4;
        }
        return convertedPrice;
    }

    @Override
    public void addDebts() {
        for (Person person : peoplePaying) {
            person.addDebt(getOwner(),price/(peoplePaying.size()+1));
        }
    }
}
