package ticket;

import person.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnevenSplitTicket extends Ticket{
    private final Map<Person, Integer> moneySplitMap;
    private final Map<Person, Boolean> personPaidMap;
    public UnevenSplitTicket(TicketType ticketType, int price, Person person,
                             Map<Person, Integer> moneySplitMap, List<Person> peoplePaying) {
        super(ticketType, price, person, peoplePaying);
        this.moneySplitMap = moneySplitMap;
        this.personPaidMap = new HashMap<>();
        for (Person personLoop : moneySplitMap.keySet()) {
            this.personPaidMap.put(personLoop, false);
            personLoop.addDebt(person,moneySplitMap.get(personLoop));
        }
        int totalMapMoney=0;
        for (Integer value : moneySplitMap.values()) {
            totalMapMoney+= value;
        }
        if (totalMapMoney!=price){
            throw new RuntimeException("Price of the ticket is not the same as the sum of money in the moneysplitmap!");
        }

    }

    public boolean ticketPayedOff(){
        for (Boolean value : personPaidMap.values()) {
            if (!value){
                return false;
            }
        }
        return true;
    }

    public int getMoneyOfPerson(Person person) {
        return moneySplitMap.get(person);
    }
}
