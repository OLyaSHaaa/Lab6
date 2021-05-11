package commands;

import messages.Messenger;

import java.util.Stack;

/**
 * Класс команды, которая выводит последние 9 команд
 */
public class HistoryCommand implements ServerCommand {
    private Stack<String> history;
    private Messenger messenger;


    public HistoryCommand(Stack<String> history, Messenger messenger){
        this.history = history;
        this.messenger = messenger;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messenger.getMesseng("historyOutput")).append(":\n");
        history.forEach(command -> stringBuilder.append(command).append("\n"));
        return stringBuilder.toString();
    }
}
