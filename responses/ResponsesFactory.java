package responses;

import networkMessages.Responses;

public interface ResponsesFactory {
    Responses createDefaultResponse(String content);
    Responses createErrorResponse(String content);
    Responses createNeedObjectResponse();
    Responses createClientCommandResponse();
}
