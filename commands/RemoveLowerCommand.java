package commands;

import  collectionManager.CollectionManager;
import messages.Messenger;
import ticket.Ticket;



public class RemoveLowerCommand implements ServerCommand {
    private CollectionManager collectionManager;
    private Messenger messenger;

    public RemoveLowerCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        Ticket ticket = collectionManager.getTicketsStream().min(Ticket::compareTo).get();
        collectionManager.removeElement(ticket);
        return messenger.getMesseng("removeLowerOutput");
    }
}

