package commands;

import collectionManager.CollectionManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import input.InputManager;
import messages.Messenger;
import ticket.RawTicket;
import ticket.Ticket;

/**
 * Класс команды, которая добавляет новый элемент в коллекцию,
 * если его значение превышает значение наибольшего элемента этой коллекции
 */
public class AddIfMaxCommand implements ServerCommand, RequiringObject {
    private final CollectionManager collectionManager;
    private final Messenger messenger;
    private RawTicket ticket;

    public AddIfMaxCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        if (ticket.compareTo(collectionManager.getTicketsStream().max(Ticket::compareTo).get().getRawTicket()) > 0){
            collectionManager.addElement(ticket);
            return messenger.getMesseng("addOutput");
        } else{
            return messenger.getMesseng("notAddOutput");
        }
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.invokeCommand(this);
        commandInvoker.setObjectToCommand(this);
    }

    @Override
    public void setObject(RawTicket ticket) {
        this.ticket = ticket;
    }
}

