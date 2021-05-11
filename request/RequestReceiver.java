package request;

import networkMessages.Request;

import java.io.IOException;
import java.net.SocketAddress;

public interface RequestReceiver {
    Request receiverRequest() throws IOException,ClassNotFoundException;
    SocketAddress getAddress();
}
