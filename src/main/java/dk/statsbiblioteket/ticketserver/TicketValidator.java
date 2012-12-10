package dk.statsbiblioteket.ticketserver;

public interface TicketValidator {
    public boolean isValid(String address, String resource, String ticket);
}
