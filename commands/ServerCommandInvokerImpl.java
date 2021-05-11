package commands;

import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import messages.Messenger;
import networkMessages.RequestType;
import ticket.RawTicket;
import ticket.Ticket;
import ticket.TicketType;

/**
 * Реализация интерфейса CommandInvoker
 */
public class ServerCommandInvokerImpl implements ServerCommandInvoker {
    private String arg;
    private RawTicket ticket;
    private RequestType type;
    private String output;

    @Override
    public void invokeCommand(ServerCommand command) {
       output = command.execute();
    }

    @Override
    public void setLongArgToCommand(RequiringArg<Long> command) throws InvalidArgumentTypeException, NoArgException {
        if (type == RequestType.ARG_OBJECT_REQUEST || type == RequestType.ARG_REQUEST){
            try{
                command.setArg(Long.parseLong(arg));
            } catch (NumberFormatException e){
                throw new InvalidArgumentTypeException();
            }
        } else{
            throw new NoArgException();
        }
    }

    @Override
    public void setStringArgToCommand(RequiringArg<String> command) throws NoArgException {
        if (type == RequestType.ARG_REQUEST || type == RequestType.ARG_OBJECT_REQUEST){
            command.setArg(arg);
        } else {
            throw new NoArgException();
        }
    }

    @Override
    public void setObjectToCommand(RequiringObject command) throws NeedObjectException {
        if (type == RequestType.OBJECT_REQUEST || type == RequestType.ARG_OBJECT_REQUEST){
            command.setObject(ticket);
        } else {
            throw new NeedObjectException();
        }
    }

    @Override
    public void setTicketTypeArg(RequiringArg<TicketType> command) throws InvalidArgumentTypeException, NoArgException {
        if (!arg.equals("")) {
            try {
                command.setArg(TicketType.valueOf(arg.toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new InvalidArgumentTypeException();
            }
        } else {
            throw new NoArgException();
        }
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public void setObject(RawTicket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void setType(RequestType type) {
        this.type = type;
    }

    @Override
    public String getCommandOutput() {
        return this.output;
    }
}
