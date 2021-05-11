package responses;

import networkMessages.Responses;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class ClientResponseReceiver implements ResponseReceiver{
    private final DatagramSocket socket;

    public ClientResponseReceiver(DatagramSocket socket){
        this.socket = socket;
    }

    @Override
    public Responses receiverResponse() throws IOException {
        ByteBuffer bytes = ByteBuffer.allocate(4096);
        DatagramPacket receiverPacket = new DatagramPacket(bytes.array(),bytes.array().length);
        socket.receive(receiverPacket);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes.array()));
        try{
            return (Responses) objectInputStream.readObject();
        } catch (ClassNotFoundException e){
            throw new IOException();
        }
    }
}
