/**
 * класс, реализующий интерфейс мессенджер
 */
package messages;

import exceptions.NoSuchCommandException;
import ticket.Ticket;

import java.util.HashMap;

public class    MessengerImpl implements Messenger{
    private final HashMap<String, String> messages;
    private Messenger messenger;

    public MessengerImpl(){
        messages = new HashMap<>();
        messages.put("helpDescription", " help: вывести справку по доступным командам");
        messages.put("addDescription"," add: добавить новый элемент в коллекцию");
        messages.put("add_if_maxDescription"," add_if_max: добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        messages.put("clearDescription"," clear: очистить коллекцию");
        messages.put("execute_scriptDescription"," execute_script: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        messages.put("exitDescription"," exit: завершить программу (с сохранением в файл)");
        messages.put("group_counting_by_priceDescription"," group_counting_by_price");
        messages.put("infoDescription"," info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        messages.put("remove_by_idDescription"," remove_by_id: удалить элемент из коллекции по его id");
        messages.put("showDescription"," show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        messages.put("updateDescription"," update: обновить значение элемента коллекции, id которого равен заданному");
        messages.put("historyDescription"," history");
        messages.put("remove_lowerDescription"," remove_lower: удалить из коллекции все элементы, меньшие, чем заданный");
        messages.put("filter_greater_than_typeDescription"," filter_greater_than_type");
        messages.put("count_by_typeDescription"," count_by_type");
        messages.put("invalidId"," invalid id value, must be more than 0");
        messages.put("notUniqueId"," not uniq id");
        messages.put("noIdLeft"," no id left");
        messages.put("invalidName"," invalid name value, must be not null");
        messages.put("invalidCoordinates"," invalid coordinates value, must be not null");
        messages.put("invalidCoordinatesX"," invalid coordinates.x value, must be more than -941 and not null");
        messages.put("invalidCreationDate"," invalid creationDate value, must be not null");
        messages.put("invalidPrice"," invalid Price value, must be more than 0");
        messages.put("invalidTicketType"," invalid TicketType value");
        messages.put("invalidVenueId"," invalid VenueId value, must be not null");
        messages.put("invalidVenueName"," invalid VenueName value, must be not null");
        messages.put("invalidVenueCapacity"," invalid VenueCapacity value, must be not null");
        messages.put("noInteger"," value must be integer number");
        messages.put("noLong"," value must be long number");
        messages.put("noDate"," date must be in the specified format");
        messages.put("noEnum"," value must be selected from the suggested options");
        messages.put("noArg"," this command needs 1 argument");
        messages.put("noMsg"," this message does not exist");
        messages.put("noEnvVar"," environmental variable does not exist");
        messages.put("brokenData"," file is broken, program starts with empty collection");
        messages.put("noData"," file does not exist");
        messages.put("wrongFieldType"," wrong field type");
        messages.put("noSuchCommand"," no such command");
        messages.put("noSuchId"," an element with this id does not exist");
        messages.put("noSuchField"," this field does not exist in class Person");
        messages.put("noSuchElement"," end of input");
        messages.put("script"," script error, script execution stopped");
        messages.put("noFile"," this file does not exist");
        messages.put("scriptRecursion"," script calls recursion, script execution stopped");
        messages.put("collectionType"," Collection type");
        messages.put("collectionInitDate"," Initialization date");
        messages.put("collectionSize"," Number of elements");
        messages.put("inputName","Name");
        messages.put("inputCoordinatesX","coordinates.x");
        messages.put("inputCoordinatesY","coordinates.y");
        messages.put("inputPrice","price");
        messages.put("inputTicketType","ticketType []");
        messages.put("inputVenueName","venueName");
        messages.put("inputVenueCapacity","venueCapacity");
        messages.put("start","запустився");
        messages.put("finish","упал");
        messages.put("exitOutput", "exit application");
        messages.put("addOutput", "element added to the collection");
        messages.put("showOutput","all elements in the collection");
        messages.put("helpOutput","help for commands");
        messages.put("notAddOutput","element didn't add to the collection");
        messages.put("clearOutput","collection cleared");
        messages.put("groupCountingByPriceOutput","elements counting dkfkj");
        messages.put("historyOutput","command history");
        messages.put("countByTypeOutput"," count");
        messages.put("filterGreaterThanTypeOutput"," type больше заданного");
        messages.put("removeOutput","element removed from collection");
        messages.put("notRemoveOutput","element didn't remove from the collection because element with this id does not exist");
        messages.put("updateOutput","element updated successfully");
        messages.put("notUpdateOutput","element was not updated because element with this id does not exist");
        messages.put("saveOutput", "collection is saved to a file");
        messages.put("scriptOutput", "script is executed");
        messages.put("noConnection", "failed to establish a connection to the server, please try again");
    }

    @Override
    public String getMesseng(String msgName){
        return messages.get(msgName);
    }

    @Override
    public String getTicketString(Ticket ticket) {
        return messenger.getTicketString(ticket);
    }

 }



