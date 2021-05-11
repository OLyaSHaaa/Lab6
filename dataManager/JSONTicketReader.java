package dataManager;


import serverCollectionManager.TicketIdManager;
import collectionManager.VenueIdManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.BrokenDataException;
import exceptions.NoEnvVarException;
import messages.Messenger;
import ticket.Ticket;
import ticket.TicketValidator;
import ticket.TicketValidatorImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class JSONTicketReader implements DataReader {
    private String fileName;
    private final HashSet<Ticket> ticketHashSet = new HashSet<>();

    public JSONTicketReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public HashSet<Ticket> readElements() throws NoEnvVarException, BrokenDataException {
        if (fileName == null) {
            throw new NoEnvVarException();
        }
        try (InputStreamReader stream = new InputStreamReader(new FileInputStream(fileName))) {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            Ticket[] tickets = gson.fromJson(stream, Ticket[].class);
            if (tickets != null) {
                for (Ticket ticket : tickets) {
                    TicketValidator ticketValidator = new TicketValidatorImpl();
                    if (!ticketValidator.validateId(ticket.getId())) {
                        throw new BrokenDataException();
                    }
                    if (TicketIdManager.getInstance().idIsFree(ticket.getId())){
                        TicketIdManager.getInstance().addId(ticket.getId());
                    } else {
                        throw new BrokenDataException();
                    }
                    if (!ticketValidator.validateName(ticket.getName())) {
                        throw new BrokenDataException();
                    }
                    if (!ticketValidator.validateCoordinates(ticket.getCoordinates())) {
                        throw new BrokenDataException();
                    }
                    if (!ticketValidator.validateTicketType(ticket.getTicketType())) {
                        throw new BrokenDataException();
                    }
                    if (!ticketValidator.validatePrice(ticket.getPrice())) {
                        throw new BrokenDataException();
                    }
                    if (!ticketValidator.validateVenue(ticket.getVenue())) {
                        throw new BrokenDataException();
                    }
                    if (VenueIdManager.getInstance().idIsFree(ticket.getVenue().getId())){
                        VenueIdManager.getInstance().addId(ticket.getVenue().getId());
                    } else {
                        throw new BrokenDataException();
                    }
                }
                ticketHashSet.addAll(Arrays.asList(tickets));
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return ticketHashSet;
    }
}