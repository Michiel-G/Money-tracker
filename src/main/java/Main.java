import person.Person;
import ticket.EvenSplitTicket;
import ticket.Ticket;
import ticket.TicketType;
import ticket.UnevenSplitTicket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("John");
        Person person2 = new Person("Conor");
        Person person3 = new Person("Frank");
        person1.setWalletAmount(20);
        person2.setWalletAmount(20);
        person3.setWalletAmount(20);

        Ticket ticket1 = new EvenSplitTicket(TicketType.RESTAURANT, 20, person1, List.of(person1, person2, person3));
        Ticket ticket2 = new UnevenSplitTicket(TicketType.RESTAURANT, 20, person1, Map.ofEntries(
                Map.entry(person1, 5),
                Map.entry(person2, 5),
                Map.entry(person3, 10)
        ));


    }
}