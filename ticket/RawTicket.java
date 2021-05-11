package ticket;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class RawTicket implements Comparable<RawTicket>, Serializable {
    private static final long serialVersionUID = 5366370939317668205L;
    private  final String name; //Поле не может быть null, Строка не может быть пустой
    private  final Coordinates coordinates; //Поле не может быть null
    private  final long price; //Значение поля должно быть больше 0
    private  final TicketType ticketType; //Поле может быть null
    private final Venue venue; //Поле не может быть null

    public RawTicket(String name, Coordinates coordinates, long price, TicketType ticketType, Venue venue) {
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.ticketType = ticketType;
        this.venue = venue;
    }

    public String getName() {
        return name;
    }

    /**
     * @return координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return цена билета
     */
    public long getPrice () { return price;}

    /**
     * @return тип билета
     */
    public TicketType getTicketType(){ return ticketType;}

    /**
     * @return место встречи
     */
    public Venue getVenue() {
        return venue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null | getClass() != o.getClass()) return false;
        RawTicket rawTicket = (RawTicket) o;
        return name.equals(rawTicket.name) && coordinates.equals(rawTicket.coordinates) && price == rawTicket.price &&
                ticketType.equals(rawTicket.ticketType) && venue.equals(rawTicket.venue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,coordinates,price,ticketType,venue);
    }

    @Override
    public int compareTo(RawTicket o) {
        return getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "RawTicket{" +
                "name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", price=" + price +
                ", ticketType=" + ticketType +
                ", venue=" + venue +
                '}';
    }
}
