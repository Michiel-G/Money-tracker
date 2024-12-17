package ticket;

import person.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnevenSplitTicket extends Ticket{
    private final Map<Person, Integer> moneySplitMap;
    public UnevenSplitTicket(TicketType ticketType, int price, Person person,
                             Map<Person, Integer> moneySplitMap, List<Person> peoplePaying) {
        super(ticketType, price, person, peoplePaying);
        this.moneySplitMap = moneySplitMap;
        int totalMapMoney=0;
        for (Integer value : moneySplitMap.values()) {
            totalMapMoney+= value;
        }
        if (totalMapMoney!=price){
            throw new RuntimeException("Price of the ticket is not the same as the sum of money in the moneysplitmap!");
        }

    }

    public int getMoneyOfPerson(Person person) {
        return moneySplitMap.get(person);
    }
}
