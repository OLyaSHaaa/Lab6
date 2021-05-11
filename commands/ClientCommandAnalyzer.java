package commands;

import networkMessages.Request;
import request.RequestReceiver;
import request.ServerRequestReceiver;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

public class ClientCommandAnalyzer extends Thread implements CommandAnalyzer{
    private boolean exit;
    private final ServerCommandManager commandManager;
    private final DatagramChannel channel;

    public ClientCommandAnalyzer(ServerCommandManager commandManager, DatagramChannel channel){
        this.commandManager = commandManager;
        this.channel = channel;
    }

    @Override
    public void startReading() {
        start();
    }

    @Override
    public void run() {
        while (!exit){
            try{
                RequestReceiver requestReceiver = new ServerRequestReceiver(channel);
                Request request = requestReceiver.receiverRequest();
                commandManager.executeClientCommand(request.getType(),request.getCommand(), request.getArg(), request.getObject(), requestReceiver.getAddress(), channel);
            } catch (IOException e){
                stopReading();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopReading() {
        exit = true;
    }
}
