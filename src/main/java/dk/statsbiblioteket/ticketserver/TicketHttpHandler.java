package dk.statsbiblioteket.ticketserver;

import org.webbitserver.HttpControl;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;
import org.webbitserver.handler.StaticFileHandler;

public class TicketHttpHandler extends StaticFileHandler {
    TicketValidator ticketValidator;

    public TicketHttpHandler(String dir) {
        super(dir);
        ticketValidator = new TicketValidator();
    }

    @Override
    public void handleHttpRequest(HttpRequest httpRequest, HttpResponse httpResponse, HttpControl httpControl) throws Exception {
        String ticket = httpRequest.queryParam("ticket");
        if (ticket != null && ticketValidator.isValid(ticket)) {
            super.handleHttpRequest(httpRequest, httpResponse, httpControl);
        } else {
            httpResponse.status(403);
            httpResponse.content("Forbidden");
            httpResponse.end();
        }
    }
}
