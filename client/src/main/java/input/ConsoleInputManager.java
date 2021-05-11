package input;

import messages.Messenger;
import output.OutputManager;
import ticket.RawTicket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputManager implements InputManager {
    private final BufferedReader reader;
    private final CommandReader commandReader;
    private final TicketReader ticketReader;

    public ConsoleInputManager(Messenger messenger, OutputManager outputManager){
        reader = new BufferedReader(new InputStreamReader(System.in));
        commandReader = new ConsoleCommandReader(reader);
        ticketReader = new ConsoleTicketReader(reader,messenger,outputManager);
    }

    @Override
    public boolean ready() throws IOException {
        return reader.ready();
    }

    @Override
    public String readCommand() throws IOException{
        return commandReader.readCommand();
    }

    @Override
    public RawTicket readTicket() throws IOException{
        return ticketReader.readTicket();
    }

}