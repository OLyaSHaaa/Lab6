package commands;

import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import networkMessages.RequestType;
import ticket.RawTicket;
import ticket.TicketType;

/**
 * Класс-визитор для запуска команд и передачи им необходимых аргументов
 */
public interface ServerCommandInvoker {

    /**
     * Метод, который запускает команду
     * @param command команда
     */
    void invokeCommand(ServerCommand command);

    /**
     * Метод, который передает команде целочисленный аргумент
     * @param command команда
     * @throws InvalidArgumentTypeException если аргумент не целочисленный
     * @throws NoArgException если аргумент отсутствует
     */
    void setLongArgToCommand(RequiringArg<Long> command) throws InvalidArgumentTypeException, NoArgException;

    /**
     * Метод, который передает команде аргумент-строку
     * @param command команда
     * @throws NoArgException если аргумент отсутствует
     */
    void setStringArgToCommand(RequiringArg<String> command) throws NoArgException;

    void setTicketTypeArg(RequiringArg<TicketType> command) throws InvalidArgumentTypeException, NoArgException;

    void setObjectToCommand(RequiringObject command) throws NeedObjectException;

    /**
     * Метод, который задает необходимый аргумент
     * @param arg аргумент
     */
    void setArg(String arg);
    void setObject(RawTicket ticket);
    void setType(RequestType type);
    String getCommandOutput();

}

