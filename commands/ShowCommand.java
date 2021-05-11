package commands;

import collectionManager.CollectionManager;
import messages.Messenger;

/**
 * Класс команды, которая выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class ShowCommand implements ServerCommand {
    private CollectionManager collectionManager;
    private Messenger messenger;

    public ShowCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messenger.getMesseng("showOutput"))
                .append(" (")
                .append(collectionManager.getSize())
                .append("):\n");
        collectionManager.getTicketsStream().forEachOrdered(ticket -> stringBuilder.append(ticket).append("\n"));
        return stringBuilder.toString();
    }
}
