package ticket;

import person.Person;

import java.util.ArrayList;
import java.util.List;

public class EvenSplitTicket extends Ticket{
    private List<Person> peoplePaying;
    public EvenSplitTicket(TicketType ticketType, int price, Person person, List<Person> peoplePaying) {
        super(ticketType, price, person);
        this.peoplePaying = peoplePaying;
    }

    public void payTicket(Person person){
        int priceToPay = getPrice()/peoplePaying.size();
        if(peoplePaying.contains(person) && person.getWalletAmount() >= priceToPay) {
            person.setWalletAmount(person.getWalletAmount() - priceToPay);
        } else {
            if(!peoplePaying.contains(person)){
                throw new RuntimeException(person.getName()+" does not need to pay on this ticket");
            } else{
                throw new RuntimeException(person.getName()+" does not have enough money");
            }

        }
    }

    public List<Person> getPeoplePaying() {
        return peoplePaying;
    }
}
