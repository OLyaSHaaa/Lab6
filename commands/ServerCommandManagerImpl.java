package commands;

import application.Application;
import collectionManager.CollectionManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import messages.Messenger;
import networkMessages.RequestType;
import responses.ResponsesFactory;
import responses.ResponsesSender;
import responses.ServerResponsesFactory;
import responses.ServerResponsesSender;
import ticket.RawTicket;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.*;

/**
 * Реализация интерфеса CommandManager
 */
public class ServerCommandManagerImpl implements ServerCommandManager {
    private Map<String, ServerCommand> clientCommandMap;
    private Map<String,ServerCommand> serverCommandMap;
    private Stack<String> history;
    private ServerCommandInvoker commandInvoker;
    private ServerCommandInvoker commandInvokerForServerCommand;
    private Messenger messenger;

    public ServerCommandManagerImpl(CollectionManager collectionManager, Application app, Messenger messenger){
        this.messenger = messenger;
        clientCommandMap = new HashMap<>();
        serverCommandMap = new HashMap<>();
        history = new Stack<>();
        commandInvoker = new ServerCommandInvokerImpl();
        commandInvokerForServerCommand = new ServerCommandInvokerImpl();
        clientCommandMap.put("help", new HelpCommand(clientCommandMap.keySet(), messenger));
        clientCommandMap.put("info", new InfoCommand(collectionManager, messenger));
        clientCommandMap.put("show", new ShowCommand(collectionManager, messenger));
        clientCommandMap.put("add", new AddCommand(collectionManager,messenger));
        clientCommandMap.put("update", new UpdateCommand(collectionManager, messenger));
        clientCommandMap.put("remove_by_id", new RemoveByIdCommand(collectionManager, messenger));
        clientCommandMap.put("clear", new ClearCommand(collectionManager, messenger));
        clientCommandMap.put("add_if_max",new AddIfMaxCommand(collectionManager,messenger));
        clientCommandMap.put("remove_lower", new RemoveLowerCommand(collectionManager,messenger));
        clientCommandMap.put("history", new HistoryCommand(history, messenger));
        clientCommandMap.put("group_counting_by_price",new GroupCountingByPriceCommand(collectionManager,messenger));
        clientCommandMap.put("count_by_type", new CountByTypeCommand(collectionManager,messenger));
        clientCommandMap.put("filter_greater_than_type", new FilterGreaterThanTypeCommand(collectionManager,messenger));

        serverCommandMap.put("exit", new ExitCommand(collectionManager, app, messenger));
        serverCommandMap.put("save", new SaveCommand(collectionManager, messenger));
    }

    @Override
    public void executeServerCommand(String command) {
        if (serverCommandMap.containsKey(command)){
            try {
                serverCommandMap.get(command).acceptInvoker(commandInvokerForServerCommand);
            } catch (NoArgException | InvalidArgumentTypeException | NeedObjectException ignored) {

            }
        }
    }

    @Override
    public void executeClientCommand(RequestType type, String command, String arg, RawTicket ticket,
                                     SocketAddress address, DatagramChannel channel){
        ResponsesFactory responseFactory = new ServerResponsesFactory();
        ResponsesSender responseSender = new ServerResponsesSender(channel, address);
        if (clientCommandMap.containsKey(command)) {
            commandInvoker.setType(type);
            commandInvoker.setArg(arg);
            commandInvoker.setObject(ticket);
            try {
                clientCommandMap.get(command).acceptInvoker(commandInvoker);
                history.push(command);
                if (history.size() > 9) {
                    history.remove(0);
                }
                responseSender.sendResponse(responseFactory.createDefaultResponse(commandInvoker.getCommandOutput()));
            } catch (NoArgException e) {
                responseSender.sendResponse(responseFactory.createErrorResponse("noArg"));
            } catch (InvalidArgumentTypeException e) {
                responseSender.sendResponse((responseFactory.createErrorResponse("invalidArgumentType")));
            } catch (NeedObjectException e) {
                responseSender.sendResponse(responseFactory.createNeedObjectResponse());
            }

        } else {
            responseSender.sendResponse(responseFactory.createErrorResponse("noSuchCommand"));
        }
    }
}
