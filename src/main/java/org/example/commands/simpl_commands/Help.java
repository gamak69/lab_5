package org.example.commands.simpl_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.manager.CommandManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

public class Help extends Command {
    CollectionManager collectionManager = CollectionManager.getInstance();
    public Help(){
        super("help", "Выводит справку по доступным командам", false);
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
        //                 //
        sb.append("┌──────────────────────────────────────────┬─────────────────────────────────────────────────────────────────────────────────────────────────────────────┐\n");
        sb.append(String.format("│ " + HEADER_COLOR + "%-40s" + RESET + " │ " + HEADER_COLOR + "%-108s" + RESET + "│%n", "Команда", "Описание"));
        sb.append("├──────────────────────────────────────────┼─────────────────────────────────────────────────────────────────────────────────────────────────────────────┤\n");

        for (Command command : CommandManager.getCommandMap().values()) {
            sb.append(String.format("│ %-40s │ %-108s│%n", command.getName(), command.getDescription()));
        }

        sb.append("└──────────────────────────────────────────┴─────────────────────────────────────────────────────────────────────────────────────────────────────────────┘\n");

        return new ExecStatus(sb.toString());
    }

}
