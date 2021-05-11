package commands;

import collectionManager.CollectionManager;
import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgException;
import messages.Messenger;

/**
 * Класс команды, которая удаляет элемент из коллекции по его id
 */
public class RemoveByIdCommand implements ServerCommand, RequiringArg<Long> {
    private CollectionManager collectionManager;
    private Messenger messenger;
    private Long arg;

    public RemoveByIdCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        if (collectionManager.removeElement(arg)){
            return messenger.getMesseng("removeOutput");
        } else{
            return messenger.getMesseng("notRemoveOutput");
        }
    }

    @Override
    public void setArg(Long arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException {
        commandInvoker.setLongArgToCommand(this);
        commandInvoker.invokeCommand(this);
    }
}
