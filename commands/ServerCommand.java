package commands;

import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;

/**
 * Общий интерфейс всех классов команд
 */
public interface ServerCommand {

    /**
     * Метод, который запускает команду
     */
    String execute();

    /**
     * Метод, который указывает, как запускать эту команду
     * @param commandInvoker объект CommandInvoker
     */
    default void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.invokeCommand(this);
    }
}
