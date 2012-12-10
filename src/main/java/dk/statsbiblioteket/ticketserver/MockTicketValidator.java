package dk.statsbiblioteket.ticketserver;

public class MockTicketValidator implements TicketValidator {
    @Override
    public boolean isValid(String address, String resource, String ticket) {
        System.out.println(String.format("Validating ticket '%s' for resource '%s' from '%s'.", ticket, resource, address));
        return ticket.equals("aft");
    }
}
