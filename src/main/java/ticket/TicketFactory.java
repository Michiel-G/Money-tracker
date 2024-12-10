package ticket;

import person.Person;

import java.util.*;

public class TicketFactory {
    public EvenSplitTicket createEvenSplitTicket(TicketType ticketType, int price, Person person, List<Person> peoplePaying){
        return new EvenSplitTicket(ticketType, price, person,  peoplePaying);
    }
    public UnevenSplitTicket createUnevenSplitTicket(TicketType ticketType, int price, Person person, Map<Person, Integer> moneySplitMap){
        List<Person> peoplePaying = new ArrayList<>(moneySplitMap.keySet());
        return new UnevenSplitTicket(ticketType, price, person, moneySplitMap,peoplePaying);
    };

    public List<Person> createGlobalTicket(List<Ticket> tickets){
        List<Person> people = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if(!people.contains(ticket.getPerson())){
               people.add(ticket.getPerson());
            }
            for (Person person : ticket.getPeoplePaying()) {
                if(!people.contains(person)){
                    people.add(person);
                }
                person.addDebt(ticket.getPerson(), ticket.getMoneyOfPerson(person));
            }
        }

        return people;
    };
}
