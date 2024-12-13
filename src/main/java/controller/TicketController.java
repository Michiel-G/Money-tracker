package controller;

import database.TicketDB;
import ticket.Ticket;

import java.util.List;

public class TicketController {
    private TicketDB db;

    public TicketController(TicketDB db) {
        this.db = db;
    }

    public void removeAllTickets(){
        db.removeAllTickets();
    }
    public void addTicket(Ticket ticket) {
        db.addTicket(ticket);
    }

    public List<Ticket> getAllTickets(){
        return db.getAllTickets();
    }

}
