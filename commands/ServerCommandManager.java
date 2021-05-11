package commands;

import exceptions.NoSuchCommandException;
import networkMessages.RequestType;
import ticket.RawTicket;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public interface ServerCommandManager {
    void executeClientCommand(RequestType type, String command, String arg, RawTicket ticket, SocketAddress address, DatagramChannel channel);
    void executeServerCommand(String command);
}
