package database;

import controller.TicketController;
import observers.EntryObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import person.Person;
import ticket.Ticket;


// Prepare class RegistrationController for testing by injecting mocks
@PrepareForTest(TicketDB.class)
public class TicketDB_UTest {
    public TicketDB_UTest()
    {

    }

    @Before
    public void initialize()
    {

    }
    @Test
    public void createTicket(){
        TicketDB ticketDB = TicketDB.getInstance();
        Ticket ticket = Mockito.mock(Ticket.class);
        ticketDB.attach(Mockito.mock(EntryObserver.class));
        ticketDB.addTicket(ticket);

    }
}
