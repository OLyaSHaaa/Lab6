package clientCommands;

import client.ClientApplication;
import exceptions.NoArgException;
import input.InputManager;
import messages.Messenger;
import networkMessages.ResponsType;
import networkMessages.Responses;
import output.OutputManager;
import request.ClientRequestFactory;
import request.ClientRequestSender;
import request.RequestFactory;
import request.RequestSender;
import responses.ClientResponseReceiver;
import responses.ResponseReceiver;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClientCommandManagerImpl implements ClientCommandManager{
    private final DatagramSocket socket;
    private final SocketAddress address;
    private final InputManager inputManager;
    private final OutputManager outputManager;
    private final Messenger messenger;
    private final Map<String, ClientCommand> clientCommandMap;
    private final ClientCommandInvoker commandInvoker;
    private final Set<String> usedScript = new HashSet<>();

    public ClientCommandManagerImpl(DatagramSocket socket, SocketAddress address, InputManager inputManager,
                                    OutputManager outputManager, Messenger messenger, ClientApplication app){
        this.socket = socket;
        this.address = address;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
        this.messenger = messenger;
        commandInvoker = new ClientCommandInvokerImpl();
        clientCommandMap = new HashMap<>();
        clientCommandMap.put("exit",new ExitCommand(app,messenger));
        clientCommandMap.put("execute_script", new ExecuteScriptCommand(socket, address, outputManager, messenger, app));
    }

    @Override
    public void usedScriptAdd(String scriptName) {
        usedScript.add(scriptName);
    }

    @Override
    public boolean scriptIsUsed(String scriptName) {
        return usedScript.contains(scriptName);
    }

    private void executeClientCommand(String command, String arg){
        commandInvoker.setArg(arg);
        try{
            clientCommandMap.get(command).acceptInvoker(commandInvoker);
            outputManager.printMsg(commandInvoker.getCommandOutput() + "\n");
        } catch (NoArgException e){
            outputManager.printErrorMsg(messenger.getMesseng("noArg") + "\n");
        }
    }

    private void executeServerCommand(String command, String arg) throws IOException{
        RequestSender requestSender = new ClientRequestSender(address,socket);
        RequestFactory requestFactory = new ClientRequestFactory();
        if (arg == null || arg.equals("")){
            requestSender.sendRequest(requestFactory.createSimpleRequest(command));
        } else{
            requestSender.sendRequest(requestFactory.createArgRequest(command,arg));
        }
        ResponseReceiver responseReceiver = new ClientResponseReceiver(socket);
        Responses response = responseReceiver.receiverResponse();
        if (response.getType() == ResponsType.NEED_OBJECT_RESPONSE){
            if (arg == null || arg.equals("")){
                requestSender.sendRequest(requestFactory.createObjectRequest(command,inputManager.readTicket()));
            } else {
                requestSender.sendRequest(requestFactory.createArgObjectRequest(command,arg,inputManager.readTicket()));
            }
            response = responseReceiver.receiverResponse();
        }
        if (response.getType() == ResponsType.ERROR_RESPONSE){
            outputManager.printErrorMsg(messenger.getMesseng(response.getContent()) + "\n");
        }
        if (response.getType() == ResponsType.DEFAULT_RESPONSE){
            outputManager.printMsg(response.getContent() + "\n");
        }
    }

    @Override
    public void executeCommand(String command, String arg) throws IOException {
        if (clientCommandMap.containsKey(command)){
            executeClientCommand(command, arg);
        } else {
            try{
                 executeServerCommand(command,arg);
            } catch (SocketTimeoutException e){
                outputManager.printErrorMsg(messenger.getMesseng("noConnection") + "\n");
                executeCommand(command,arg);
            }
        }
    }

    @Override
    public void clearUsedScripts() {
        usedScript.clear();
    }

    @Override
    public void usedScriptRemove(String scriptName) {
        usedScript.remove(scriptName);
    }
}
