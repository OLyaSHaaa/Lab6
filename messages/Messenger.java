/**
 * Iнтерфейс мессенджер
 */
package messages;

import exceptions.NoSuchCommandException;
import ticket.Ticket;

public interface Messenger {
    String getMesseng(String msgName);

    String getTicketString(Ticket ticket);
}


