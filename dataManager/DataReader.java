package dataManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoEnvVarException;
import messages.Messenger;
import ticket.Ticket;

import java.io.FileNotFoundException;
import java.util.HashSet;


public interface DataReader {

    HashSet<Ticket> readElements()  throws InvalidFieldException, NoEnvVarException, BrokenDataException;
}
