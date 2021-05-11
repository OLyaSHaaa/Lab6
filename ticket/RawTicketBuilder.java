package ticket;


import exceptions.InvalidFieldException;


public interface RawTicketBuilder {

    void setName(String name) throws InvalidFieldException;
    void setCoordinatesX(int x) throws InvalidFieldException;
    void setCoordinatesY(Double y) throws InvalidFieldException;
    void setPrice(long price) throws InvalidFieldException;
    void setTicketType(TicketType ticketType) throws InvalidFieldException;
    void setVenueId();
    void setVenueId(int id) throws InvalidFieldException;
    void setVenueName(String name) throws  InvalidFieldException;
    void setVenueCapacity(int capacity) throws InvalidFieldException;
    RawTicket getRawTicket();




}