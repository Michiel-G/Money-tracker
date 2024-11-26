package ticket;

import person.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvenSplitTicket extends Ticket{
    private List<Person> peoplePaying;
    private Map<Person, Boolean> personPaidMap;
    public EvenSplitTicket(TicketType ticketType, int price, Person person, List<Person> peoplePaying) {
        super(ticketType, price, person);
        this.personPaidMap = new HashMap<>();
        for (Person personLoop : peoplePaying) {
            this.personPaidMap.put(personLoop, false);
        }
        this.peoplePaying = peoplePaying;
    }

    public boolean ticketPayedOff(){
        for (Boolean value : personPaidMap.values()) {
            if (!value){
                return false;
            }
        }
        return true;
    }

    public void payTicket(Person person){
        int priceToPay = getPrice()/peoplePaying.size();
        if(peoplePaying.contains(person) && person.getWalletAmount() >= priceToPay && !personPaidMap.get(person)) {
            person.setWalletAmount(person.getWalletAmount() - priceToPay);
        } else {
            if(!peoplePaying.contains(person) || personPaidMap.get(person)){
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
