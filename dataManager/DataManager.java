package dataManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoEnvVarException;
import ticket.Ticket;


import java.util.Collection;
import java.util.HashSet;

public interface DataManager {
    Collection<? extends Ticket> readElements() throws NoEnvVarException, InvalidFieldException, BrokenDataException;
    void writeElements(HashSet<Ticket> tickets);
}
