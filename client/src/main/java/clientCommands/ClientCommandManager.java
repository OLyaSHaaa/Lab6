package clientCommands;

import java.io.IOException;

public interface ClientCommandManager {
    void usedScriptAdd(String scriptName);
    boolean scriptIsUsed(String scriptName);
    void executeCommand(String command, String arg) throws IOException;
    void clearUsedScripts();
    void usedScriptRemove(String scriptName);
}
