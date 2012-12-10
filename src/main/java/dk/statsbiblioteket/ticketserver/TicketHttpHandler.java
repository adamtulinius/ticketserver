package dk.statsbiblioteket.ticketserver;

import org.webbitserver.HttpControl;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;
import org.webbitserver.handler.StaticFileHandler;

import java.net.InetSocketAddress;

public class TicketHttpHandler extends StaticFileHandler {
    TicketValidator ticketValidator;

    public TicketHttpHandler(String dir, TicketValidator ticketValidator) {
        super(dir);
        this.ticketValidator = ticketValidator;
    }

    @Override
    public void handleHttpRequest(HttpRequest httpRequest, HttpResponse httpResponse, HttpControl httpControl) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress) httpRequest.remoteAddress();
        String resource = httpRequest.uri().split("\\?", 2)[0];
        String ticket = httpRequest.queryParam("ticket");
        if (ticket != null && ticketValidator.isValid(socketAddress.getHostName(), resource, ticket)) {
            super.handleHttpRequest(httpRequest, httpResponse, httpControl);
        } else {
            httpResponse.status(403);
            httpResponse.content("Forbidden");
            httpResponse.end();
        }
    }
}
