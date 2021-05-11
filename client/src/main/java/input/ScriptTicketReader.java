package input;

import exceptions.InvalidFieldException;
import exceptions.ScriptException;
import ticket.*;

import java.io.BufferedReader;
import java.io.IOException;

public class ScriptTicketReader implements TicketReader {
    private BufferedReader reader;

    public ScriptTicketReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public RawTicket readTicket(){
        RawTicketBuilder ticketBuilder = new RawTicketBuilderImpl();
        try {
            readFields(ticketBuilder);
        } catch(IOException | InvalidFieldException | NumberFormatException e){
            throw new ScriptException();
        }
        return ticketBuilder.getRawTicket();
    }

    private void readFields(RawTicketBuilder ticketBuilder) throws IOException, InvalidFieldException {
        ticketBuilder.setName(reader.readLine().trim());
        ticketBuilder.setCoordinatesX(Integer.parseInt(reader.readLine().trim()));
        ticketBuilder.setCoordinatesY(Double.parseDouble(reader.readLine().trim()));
        ticketBuilder.setPrice(Long.parseLong(reader.readLine().trim()));
        ticketBuilder.setTicketType(TicketType.valueOf(reader.readLine().trim().toUpperCase()));
        ticketBuilder.setVenueName(reader.readLine().trim());
        ticketBuilder.setVenueCapacity(Integer.parseInt(reader.readLine().trim()));

    }
}
