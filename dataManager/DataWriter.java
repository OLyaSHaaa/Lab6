package dataManager;

import ticket.Ticket;

import java.util.HashSet;


public interface DataWriter {

    /**
     * Метод, который записывает коллекцию в хранилище
     * @param tickets коллекция
     */

    void writeElements(HashSet<Ticket> tickets);
}

