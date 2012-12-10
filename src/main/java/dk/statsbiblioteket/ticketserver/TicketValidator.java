package dk.statsbiblioteket.ticketserver;

public class TicketValidator {
    public boolean isValid(String ticket) {
        System.out.println("Validating ticket: " + ticket);
        return ticket.equals("aft");
    }
}
