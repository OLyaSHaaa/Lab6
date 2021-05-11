package request;

import networkMessages.Request;
import ticket.RawTicket;

public interface RequestFactory {
    Request createArgObjectRequest(String command, String arg, RawTicket ticket);
    Request createObjectRequest(String command, RawTicket ticket);
    Request createArgRequest(String command, String arg);
    Request createSimpleRequest(String command);
}
