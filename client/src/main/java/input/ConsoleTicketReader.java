package input;

import exceptions.InvalidFieldException;
import messages.Messenger;
import output.OutputManager;
import ticket.RawTicket;
import ticket.RawTicketBuilder;
import ticket.RawTicketBuilderImpl;
import ticket.TicketType;

import java.io.BufferedReader;
import java.io.IOException;


public class ConsoleTicketReader implements TicketReader {
    private BufferedReader reader;
    private Messenger messenger;
    private OutputManager outputManager;

    public ConsoleTicketReader(BufferedReader reader, Messenger messenger, OutputManager outputManager) {
        this.reader = reader;
        this.messenger = messenger;
        this.outputManager = outputManager;
    }

    @Override
    public RawTicket readTicket() throws IOException{
        RawTicketBuilder ticketBuilder = new RawTicketBuilderImpl();
        readFields(ticketBuilder);
        return ticketBuilder.getRawTicket();
    }

    private void readName(RawTicketBuilder ticketBuilder) throws IOException{
        outputManager.printMsg(messenger.getMesseng("inputName") + ": ");
        try{
            ticketBuilder.setName(reader.readLine().trim());
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readName(ticketBuilder);
        }
    }

    private void readCoordinatesX(RawTicketBuilder ticketBuilder) throws IOException{
        outputManager.printMsg(messenger.getMesseng("inputCoordinatesX") + ": ");
        try{
            ticketBuilder.setCoordinatesX(Integer.parseInt(reader.readLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(messenger.getMesseng("noInteger") + "\n");
            readCoordinatesX(ticketBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readCoordinatesX(ticketBuilder);
        }
    }

    private void readCoordinatesY(RawTicketBuilder ticketBuilder) throws IOException{
        outputManager.printMsg(messenger.getMesseng("inputCoordinatesY") + ": ");
        try{
            ticketBuilder.setCoordinatesY(Double.parseDouble(reader.readLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(messenger.getMesseng("noDouble") + "\n");
            readCoordinatesY(ticketBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readCoordinatesY(ticketBuilder);
        }
    }

    private void readPrice(RawTicketBuilder ticketBuilder) throws IOException{
        outputManager.printMsg(messenger.getMesseng("inputPrice") + ": ");
        try{
            ticketBuilder.setPrice(Long.parseLong(reader.readLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(messenger.getMesseng("noInteger") + "\n");
            readPrice(ticketBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readPrice(ticketBuilder);
        }
    }

    private void readTicketType(RawTicketBuilder ticketBuilder) throws IOException{
        outputManager.printMsg(messenger.getMesseng("inputTicketType") + ": ");
        try{
            ticketBuilder.setTicketType(TicketType.valueOf(reader.readLine().trim().toUpperCase()));
        } catch (IllegalArgumentException e) {
            outputManager.printErrorMsg(messenger.getMesseng("noEnum") + "\n");
            readTicketType(ticketBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readTicketType(ticketBuilder);
        }
    }

    private void readVenueName(RawTicketBuilder ticketBuilder) throws IOException{
        outputManager.printMsg(messenger.getMesseng("inputVenueName") + ": ");
        try{
            ticketBuilder.setVenueName(reader.readLine().trim());
        } catch (IllegalArgumentException e) {
            outputManager.printErrorMsg(messenger.getMesseng("noEnum") + "\n");
            readVenueName(ticketBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readVenueName(ticketBuilder);
        }
    }

    private void readVenueCapacity(RawTicketBuilder ticketBuilder) throws IOException{
        outputManager.printMsg(messenger.getMesseng("inputVenueCapacity") + ": ");
        try {
            ticketBuilder.setVenueCapacity(Integer.parseInt(reader.readLine().trim()));
        } catch (NumberFormatException e) {
            outputManager.printErrorMsg(messenger.getMesseng("noInteger") + "\n");
            readVenueCapacity(ticketBuilder);
        } catch (InvalidFieldException e) {
            outputManager.printErrorMsg(e.getMessage() + "\n");
            readVenueCapacity(ticketBuilder);
        }
    }

    private void readFields(RawTicketBuilder ticketBuilder) throws IOException{
        readName(ticketBuilder);
        readCoordinatesX(ticketBuilder);
        readCoordinatesY(ticketBuilder);
        readPrice(ticketBuilder);
        readTicketType(ticketBuilder);
        ticketBuilder.setVenueId();
        readVenueName(ticketBuilder);
        readVenueCapacity(ticketBuilder);
    }
}
