package commands;

import collectionManager.CollectionManager;
import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgException;
import messages.Messenger;
import ticket.TicketType;


public class FilterGreaterThanTypeCommand implements ServerCommand, RequiringArg<TicketType> {
    private CollectionManager collectionManager;
    private Messenger messenger;
    private TicketType arg;

    public FilterGreaterThanTypeCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        collectionManager.getTicketsStream()
                .filter(ticket -> ticket.getTicketType().getTypeCode() > arg.getTypeCode())
                .forEachOrdered(ticket -> System.out.println(messenger.getTicketString(ticket)+ "\n"));
        return null;
    }

    @Override
    public void setArg(TicketType arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException {
        commandInvoker.setTicketTypeArg(this);
        commandInvoker.invokeCommand(this);
    }
}
