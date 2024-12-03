package ticket;

import person.Person;

import java.util.HashMap;
import java.util.Map;

public class UnevenSplitTicket extends Ticket{
    private Map<Person, Integer> moneySplitMap;
    private Map<Person, Boolean> personPaidMap;
    public UnevenSplitTicket(TicketType ticketType, int price, Person person, Map<Person, Integer> moneySplitMap) {
        super(ticketType, price, person);
        this.moneySplitMap = moneySplitMap;
        this.personPaidMap = new HashMap<>();
        for (Person personLoop : moneySplitMap.keySet()) {
            this.personPaidMap.put(personLoop, false);
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

    public void payTicket(Person person){
        int priceToPay = moneySplitMap.getOrDefault(person, 0);
        if(priceToPay!=0 && person.getWalletAmount() >= priceToPay && !personPaidMap.get(person)) {
            person.setWalletAmount(person.getWalletAmount() - priceToPay);
            personPaidMap.put(person, true);
        } else {
            if(priceToPay==0 || personPaidMap.get(person)){
                throw new RuntimeException(person.getName()+" does not need to pay on this ticket");
            }
            else{
                throw new RuntimeException(person.getName()+" does not have enough money");
            }
        }
    }

    public int getMoneyOfPerson(Person person) {
        return moneySplitMap.get(person);
    }
}
