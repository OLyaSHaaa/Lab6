package serverCollectionManager;

import collectionManager.CollectionManager;
import dataManager.DataManager;
import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import ticket.RawTicket;
import ticket.Ticket;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * Реализация интерфейса CollectionManager
 */
public class TicketCollectionManager implements CollectionManager {
    private LocalDate initDate;
    private HashSet<Ticket> ticketHashSet;
    private DataManager dataManager;

    public TicketCollectionManager(DataManager dataManager) {
        initDate = LocalDate.now();
        ticketHashSet = new HashSet<>();
        this.dataManager = dataManager;
    }

    @Override
    public Class getType() {
        return ticketHashSet.getClass();
    }

    @Override
    public int getSize() {
        return ticketHashSet.size();
    }

    @Override
    public LocalDate getInitDate() {
        return initDate;
    }

    @Override
    public void addElement(RawTicket rawTicket) {
        long id = TicketIdManager.getInstance().getFreeId();
        ticketHashSet.add(new Ticket(id,LocalDateTime.now(),rawTicket));
    }

    @Override
    public void addElement(Ticket ticket) {
        ticketHashSet.add(ticket);
    }

    @Override
    public boolean removeElement(Long id) {
        for (Ticket ticket : ticketHashSet) {
            if (ticket.getId() == id) {
                TicketIdManager.getInstance().removeId(id);
                ticketHashSet.remove(ticket);
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeElement(Ticket ticket) {
        ticketHashSet.remove(ticket);
        TicketIdManager.getInstance().removeId(ticket.getId());
    }

    @Override
    public void clear() {
        TicketIdManager.getInstance().clearIdentifiers();
        ticketHashSet.clear();
    }

    @Override
    public void loadTickets() throws NoEnvVarException, InvalidFieldException, BrokenDataException, NoDataException {
        ticketHashSet.addAll(dataManager.readElements());
        for (Ticket ticket: ticketHashSet){
            long id = ticket.getId();
            if (TicketIdManager.getInstance().idIsFree(id)){
                TicketIdManager.getInstance().addId(id);
            } else{
                ticketHashSet.clear();
                TicketIdManager.getInstance().clearIdentifiers();
                return;
            }
        }
    }

    @Override
    public void saveTickets() {
        dataManager.writeElements(ticketHashSet);
    }

    @Override
    public Stream<Ticket> getTicketsStream() {
        return ticketHashSet.stream();
    }
}