package request;

import networkMessages.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerRequestReceiver implements RequestReceiver{
    private final DatagramChannel channel;
    private SocketAddress address;
    private final ByteBuffer buf;

    public ServerRequestReceiver(DatagramChannel channel){
        buf = ByteBuffer.allocate(4096);
        this.channel = channel;
    }

    @Override
    public Request receiverRequest() throws IOException, ClassNotFoundException {
        address = channel.receive(buf);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(buf.array()));
        return (Request) objectInputStream.readObject();
    }

    @Override
    public SocketAddress getAddress() {
        return address;
    }
}
