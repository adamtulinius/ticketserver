package dk.statsbiblioteket.ticketserver;

import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

import java.net.InetSocketAddress;

public class TicketHandler implements HttpHandler {
    TicketValidator ticketValidator;
    private HttpHandler httpHandler;

    public TicketHandler(TicketValidator ticketValidator, HttpHandler httpHandler) {
        this.ticketValidator = ticketValidator;
        this.httpHandler = httpHandler;
    }

    @Override
    public void handleHttpRequest(HttpRequest httpRequest, HttpResponse httpResponse, HttpControl httpControl) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress) httpRequest.remoteAddress();
        String resource = httpRequest.uri().split("\\?", 2)[0];
        String ticket = httpRequest.queryParam("ticket");
        if (ticket != null && ticketValidator.isValid(socketAddress.getHostName(), resource, ticket)) {
            httpHandler.handleHttpRequest(httpRequest, httpResponse, httpControl);
        } else {
            httpResponse.status(403);
            httpResponse.content("Forbidden");
            httpResponse.end();
        }
    }

}
