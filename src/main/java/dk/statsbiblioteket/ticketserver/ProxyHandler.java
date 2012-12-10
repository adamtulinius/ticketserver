package dk.statsbiblioteket.ticketserver;

import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

public class ProxyHandler implements HttpHandler {
    TicketValidator ticketValidator;

    public ProxyHandler(TicketValidator ticketValidator) {
        this.ticketValidator = ticketValidator;
    }

    @Override
    public void handleHttpRequest(HttpRequest httpRequest, HttpResponse httpResponse, HttpControl httpControl) throws Exception {

    }

}
