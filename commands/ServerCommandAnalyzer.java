package commands;

import input.CommandReader;
import input.ServerConsoleCommandReader;
import messages.Messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerCommandAnalyzer extends Thread implements CommandAnalyzer{
    private boolean exit;
    private final CommandReader commandReader;
    private final Messenger messenger;
    private final ServerCommandManager commandManager;

    public ServerCommandAnalyzer(ServerCommandManager commandManager, Messenger messenger){
        commandReader = new ServerConsoleCommandReader(new BufferedReader(new InputStreamReader(System.in)));
        this.commandManager = commandManager;
        this.messenger = messenger;
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                String inputString = commandReader.readCommand();
                if (inputString.equals("")) continue;
                commandManager.executeServerCommand(inputString);
            } catch (IOException e) {
                stopReading();
            }
        }
    }

    @Override
    public void startReading() {
        start();
    }

    @Override
    public void stopReading() {
        exit = true;
    }
}
