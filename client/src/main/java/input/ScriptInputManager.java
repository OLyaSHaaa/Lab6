package input;

import messages.Messenger;
import ticket.RawTicket;
import ticket.Ticket;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class ScriptInputManager implements InputManager{
    private BufferedReader reader;
    private CommandReader commandReader;
    private TicketReader ticketReader;

    public ScriptInputManager(BufferedReader reader, Messenger messenger){
        this.reader = reader;
        commandReader = new ScriptCommandReader(reader);
        ticketReader = new ScriptTicketReader(reader);
    }

    @Override
    public boolean ready() {
        try {
            return reader.ready();
        } catch (IOException e) {
            return false;
        }
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
