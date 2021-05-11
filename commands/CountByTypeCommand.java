package commands;

import collectionManager.CollectionManager;
import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgException;
import messages.Messenger;
import ticket.TicketType;

/**
 * Класс команды, которая выводит количество элементов, значение поля type которых равно заданному
 */
public class CountByTypeCommand implements ServerCommand, RequiringArg<TicketType> {
    private CollectionManager collectionManager;
    private Messenger messenger;
    private TicketType arg;

    public CountByTypeCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        long count = collectionManager.getTicketsStream()
                    .filter(ticket -> ticket.getTicketType() == arg)
                    .count();
        return messenger.getMesseng("countByTypeOutput") + count;
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
