package clientCommands;


import exceptions.NoArgException;

public interface ClientCommand {

    String execute();

    default void acceptInvoker(ClientCommandInvoker clientCommandInvoker) throws NoArgException {
        clientCommandInvoker.invokeCommand(this);
    }
}
