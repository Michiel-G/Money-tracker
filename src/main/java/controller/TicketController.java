package controller;

import database.TicketDB;
import ticket.Ticket;

public class TicketController {
    private TicketDB db;

    public TicketController(TicketDB db) {
        this.db = db;
    }

    public void addTicket(Ticket ticket) {
        db.addTicket(ticket);
    }

}
