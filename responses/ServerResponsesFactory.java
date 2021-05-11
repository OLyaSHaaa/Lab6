package responses;

import networkMessages.ResponsImpl;
import networkMessages.ResponsType;
import networkMessages.Responses;

public class ServerResponsesFactory implements ResponsesFactory{
    @Override
    public Responses createDefaultResponse(String content) {
        return new ResponsImpl(ResponsType.DEFAULT_RESPONSE, content);
    }

    @Override
    public Responses createErrorResponse(String content) {
        return new ResponsImpl(ResponsType.ERROR_RESPONSE, content);
    }

    @Override
    public Responses createNeedObjectResponse() {
        return new ResponsImpl(ResponsType.NEED_OBJECT_RESPONSE, null);
    }

    @Override
    public Responses createClientCommandResponse() {
        return new ResponsImpl(ResponsType.CLIENT_COMMAND_RESPONSE, null);
    }
}
