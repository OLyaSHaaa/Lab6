package commands;

import collectionManager.CollectionManager;
import command.RequiringArg;
import exceptions.NeedObjectException;
import serverCollectionManager.TicketIdManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgException;
import messages.Messenger;
import ticket.RawTicket;
import ticket.Ticket;

/**
 * Класс команды, которая обновляет значение элемента коллекции, id которого равен заданному
 */
public class UpdateCommand implements ServerCommand, RequiringArg<Long>, RequiringObject{
    private CollectionManager collectionManager;
    private Messenger messenger;
    private Long arg;
    private RawTicket ticket;

    public UpdateCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        if (!TicketIdManager.getInstance().idIsFree(arg)){
            TicketIdManager.getInstance().removeId(arg);
            Ticket oldPerson = collectionManager.getTicketsStream().filter(person -> person.getId() == arg).findAny().get();
            Ticket newPerson = new Ticket(oldPerson.getId(), oldPerson.getCreationDate(), ticket);
            collectionManager.removeElement(arg);
            collectionManager.addElement(ticket);
            return messenger.getMesseng("updateOutput");
        } else {
            return messenger.getMesseng("notUpdateOutput");
        }
    }

    @Override
    public void setArg(Long arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setLongArgToCommand(this);
        commandInvoker.setObjectToCommand(this);
        commandInvoker.invokeCommand(this);
    }

    @Override
    public void setObject(RawTicket ticket) {
        this.ticket = ticket;
    }
}
