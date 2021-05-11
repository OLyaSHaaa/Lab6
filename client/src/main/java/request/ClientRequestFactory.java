package request;

import networkMessages.Request;
import networkMessages.RequestImpl;
import networkMessages.RequestType;
import ticket.RawTicket;

public class ClientRequestFactory implements RequestFactory{

    @Override
    public Request createArgObjectRequest(String command, String arg, RawTicket ticket) {
        return new RequestImpl(RequestType.ARG_OBJECT_REQUEST, command, arg, ticket);
    }

    @Override
    public Request createObjectRequest(String command, RawTicket ticket) {
        return new RequestImpl(RequestType.OBJECT_REQUEST, command,null, ticket);
    }

    @Override
    public Request createArgRequest(String command, String arg) {
        return new RequestImpl(RequestType.ARG_REQUEST, command,arg,null);
    }

    @Override
    public Request createSimpleRequest(String command) {
        return new RequestImpl(RequestType.SIMPLE_REQUEST,command,null,null);
    }
}
