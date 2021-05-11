package responses;

import networkMessages.Responses;

import java.io.IOException;

public interface ResponseReceiver {
    Responses receiverResponse() throws IOException;
}
