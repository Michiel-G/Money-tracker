package ticket;

import person.Person;

import java.util.*;

public class TicketFactory {
    public EvenSplitTicket createEvenSplitTicket(TicketType ticketType, int price, Person owner, List<Person> peoplePaying){
        return new EvenSplitTicket(ticketType, price, owner,  peoplePaying);
    }
    public UnevenSplitTicket createUnevenSplitTicket(TicketType ticketType, int price, Person owner, Map<Person, Integer> moneySplitMap){
        List<Person> peoplePaying = new ArrayList<>(moneySplitMap.keySet());
        return new UnevenSplitTicket(ticketType, price, owner, moneySplitMap,peoplePaying);
    }
}
