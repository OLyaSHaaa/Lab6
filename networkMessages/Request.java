package networkMessages;

import ticket.RawTicket;

public interface Request {
    RequestType getType();
    String getCommand();
    String getArg();
    RawTicket getObject();
}
