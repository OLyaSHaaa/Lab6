package commands;

import collectionManager.CollectionManager;
import messages.Messenger;

import java.util.HashSet;
import java.util.Set;

public class GroupCountingByPriceCommand implements ServerCommand {
    private CollectionManager collectionManager;
    private Messenger messenger;

    public GroupCountingByPriceCommand(CollectionManager collectionManager, Messenger messenger) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        Set<Long> prices = new HashSet<>();
        collectionManager.getTicketsStream().forEach(ticket -> prices.add(ticket.getPrice()));
        for (long price: prices){
            return price + ": " + collectionManager.getTicketsStream().filter(ticket -> ticket.getPrice() == price)
                            .count();
        }
        return null;
    }
}
