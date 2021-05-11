package server;

import application.Application;
import collectionManager.CollectionManager;
import commands.*;
import connection.ServerConnectionManager;
import connection.ServerConnectionManagerImpl;
import dataManager.*;
import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import messages.Messenger;
import messages.MessengerImpl;
import serverCollectionManager.TicketCollectionManager;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

public class ServerApplication implements Application {
    private final int port;
    private CommandAnalyzer serverCommandAnalyzer;
    private CommandAnalyzer clientCommandAnalyzer;
    private ServerConnectionManager connectionManager;

    public ServerApplication(int port){
        this.port = port;
    }

    @Override
    public void start() {
        String fileName = System.getenv("lab6");
        Messenger messenger = new MessengerImpl();
        DataReader dataReader = new JSONTicketReader(fileName);
        DataWriter dataWriter = new JSONTicketWriter(fileName);
        DataManager dataManager = new DataManagerImpl(dataReader,dataWriter);
        CollectionManager collectionManager = new TicketCollectionManager(dataManager);
        connectionManager = new ServerConnectionManagerImpl();
        ServerCommandManager commandManager = new ServerCommandManagerImpl(collectionManager,this,messenger);
        serverCommandAnalyzer = new ServerCommandAnalyzer(commandManager,messenger);
        try{
            DatagramChannel channel = connectionManager.openConnection(port);
            clientCommandAnalyzer = new ClientCommandAnalyzer(commandManager,channel);
        } catch (IOException e){
            e.printStackTrace();
            return;
        }
        try {
            collectionManager.loadTickets();
        } catch (InvalidFieldException| NoDataException| BrokenDataException e){
            e.printStackTrace();
        } catch (NoEnvVarException e){
            e.printStackTrace();
            return;
        }
        clientCommandAnalyzer.startReading();
        serverCommandAnalyzer.startReading();
    }

    @Override
    public void exit() {
        try{
            connectionManager.closeConnection();
        } catch (IOException e){
            e.printStackTrace();
        }
        clientCommandAnalyzer.stopReading();
        serverCommandAnalyzer.stopReading();
    }
}
