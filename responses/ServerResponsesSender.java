package responses;

import networkMessages.Responses;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerResponsesSender implements ResponsesSender{
    private final DatagramChannel channel;
    private final SocketAddress address;
    private final ByteArrayOutputStream byteArrayOutputStream;

    public ServerResponsesSender(DatagramChannel channel,SocketAddress address){
        this.channel = channel;
        this.address = address;
        byteArrayOutputStream = new ByteArrayOutputStream(4096);
    }

    public void sendResponse(Responses response){
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(response);
            channel.send(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()),address);
        } catch (IOException e){
        }
    }
}
