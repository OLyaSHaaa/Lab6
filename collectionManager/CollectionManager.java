package collectionManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import ticket.RawTicket;
import ticket.Ticket;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.stream.Stream;

public interface    CollectionManager {

    Class getType();
    int getSize();
    LocalDate getInitDate();
    void addElement(Ticket ticket);
    void addElement(RawTicket ticket);
    boolean removeElement(Long id);
    void removeElement(Ticket ticket);
    void clear();
    void loadTickets() throws NoEnvVarException, InvalidFieldException, BrokenDataException, NoDataException;
    void saveTickets();
    Stream<Ticket> getTicketsStream();

}
