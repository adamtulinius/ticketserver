package dk.statsbiblioteket.ticketserver;

import org.webbitserver.WebServer;
import org.webbitserver.WebServers;

import java.util.concurrent.ExecutionException;

public class TicketServer {
    public static void main(String... args) throws ExecutionException, InterruptedException {
        new TicketServer();
    }

    public TicketServer() throws ExecutionException, InterruptedException {
        WebServer server = WebServers.createWebServer(8080);
        server.add(new TicketHttpHandler("web", new MockTicketValidator()));
        server.start().get();
    }
}
