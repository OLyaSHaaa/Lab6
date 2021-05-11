package commands;

import collectionManager.CollectionManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import input.InputManager;
import messages.Messenger;
import ticket.RawTicket;

/**
 * Класс команды, которая добавляет новый элемент в коллекцию
 */
public class AddCommand implements ServerCommand, RequiringObject{
    private CollectionManager collectionManager;
    private Messenger messenger;
    private RawTicket ticket;

    public AddCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        collectionManager.addElement(ticket);
        return messenger.getMesseng("addOutput");
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setObjectToCommand(this);
        commandInvoker.invokeCommand(this);
    }

    @Override
    public void setObject(RawTicket ticket) {
        this.ticket = ticket;
    }
}
