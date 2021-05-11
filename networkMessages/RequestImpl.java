package networkMessages;

import ticket.RawTicket;

import java.io.Serializable;
import java.util.Objects;

public class RequestImpl implements Request, Serializable {
    private static final long serialVersionUID = 8785774135702639006L;
    private final RequestType type;
    private final String command;
    private final String arg;
    private final RawTicket ticket;

    public RequestImpl(RequestType type, String command, String arg, RawTicket ticket){
        this.type = type;
        this.command = command;
        this.arg = arg;
        this.ticket = ticket;
    }

    @Override
    public RequestType getType(){
        return type;
    }

    @Override
    public String getCommand(){
        return command;
    }

    @Override
    public String getArg(){
        return arg;
    }

    @Override
    public RawTicket getObject(){
        return ticket;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null) return false;
        RequestImpl request = (RequestImpl) o;
        return type == request.type && command.equals(request.command) && Objects.equals(arg,request.arg) && Objects.equals(ticket, request.ticket);
    }

    @Override
    public int hashCode(){
        return Objects.hash(type, command, arg, ticket);
    }

    @Override
    public String toString(){
        return String.format("RequestImpl(type=%s, command=%s, aeg=%s, ticket=%s)", type, command, arg, ticket);

    }

}