package org.example.manager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.commands.Command;
import org.example.commands.element_commands.*;
import org.example.commands.simpl_commands.*;

import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

@NoArgsConstructor
public class CommandManager {
    private static CommandManager instance;
    public static CommandManager getInstance(){
        if (instance == null){
            instance = new CommandManager();
        }
        return instance;
    }
    @Getter
    private static final Map<String, Command> commandMap = new LinkedHashMap<>();
    @Getter
    private static final Deque<Command> history = new LinkedList<>();
    static {
        commandMap.put("add", new Add());
        commandMap.put("addIfMin", new AddIfMin());
        commandMap.put("update", new Update());
        commandMap.put("clear", new Clear());
        commandMap.put("exit", new Exit());
        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("removeId", new RemoveID());
        commandMap.put("removeHead", new RemoveHead());
        commandMap.put("countGreaterThanEstablishmentDate", new CountGreaterThanEstablishmentDate());
        commandMap.put("execute", new ExecuteScript());
        commandMap.put("filterStartsWithName", new FilterStartsWithName());
        commandMap.put("history", new History());
        commandMap.put("minByMetersAboveSeaLevel", new MinByMetersAboveSeaLevel());
        commandMap.put("save", new Save());
        commandMap.put("show", new Show());
    }
    public Command getCommand(String commandName){
        return commandMap.get(commandName);
    }
    public void updateHistory(Command command){
        history.addLast(command);
        if (history.size()>15){
            history.removeFirst();
        }
    }
}
