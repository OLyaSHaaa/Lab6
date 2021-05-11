package dataManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoEnvVarException;
import messages.Messenger;
import ticket.Ticket;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;

/**
 * Реализация интерфейса DataManager
 */
public class DataManagerImpl implements DataManager {
    private DataWriter dataWriter;
    public DataReader dataReader;

    public DataManagerImpl(DataReader dataReader, DataWriter dataWriter){
        this.dataReader = dataReader;
        this.dataWriter = dataWriter;
    }


    @Override
    public Collection<? extends Ticket> readElements() throws NoEnvVarException, InvalidFieldException, BrokenDataException {
        return dataReader.readElements();
    }

    @Override
    public void writeElements(HashSet<Ticket> tickets) {
        dataWriter.writeElements(tickets);
    }


}
