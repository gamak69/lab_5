package org.example.commands.simpl_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.manager.CommandManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

public class History extends Command {
    CollectionManager collectionManager = CollectionManager.getInstance();
    public History(){
        super("history", "Выводит последние 15 команд", false);
    }

    @Override
    public boolean validate(String... args) {
        return args.length == 0;
    }

    @Override
    public ExecStatus execute(City city, String... args) {
        if (city != null){
            throw new IllegalArgumentException(String.format("Команде '%s' переданы никчемные, обосанные данные, фрик ты%n", getName()));
        }
        StringBuilder sb = new StringBuilder();

        String HEADER_COLOR = "\u001B[34m";
        String RESET        = "\u001B[0m";
        sb.append("┌─────────────────────────────────────────────┐\n");
        sb.append(String.format("│ " + HEADER_COLOR + "%-43s" + RESET + " │%n", "История"));

        int i = 1;
        for (Command command : CommandManager.getHistory()) {
            sb.append("├─────────────────────────────────────────────┤\n");
            sb.append(String.format("│ %-2d) %-39s │%n", i++, command.getName()));
        }

        sb.append("└─────────────────────────────────────────────┘\n");

        return new ExecStatus(sb.toString());
    }
}
