package org.example.commands.simpl_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

public class Info extends Command {
    CollectionManager collectionManager = CollectionManager.getInstance();
    public Info(){
        super("info", "Выводит в стандартный поток вывода информацию о коллекции", false);
    }

    @Override
    public boolean validate(String... args) {
        return args.length == 0;
    }

    @Override
    public ExecStatus execute(City city, String... args) {
        if (!validate(args)){
            return new ExecStatus(false, "Бебебе");
        }
        String HEADER_COLOR = "\u001B[34m"; // Синий цвет заголовка
        String RESET        = "\u001B[0m";  // Сброс цвета
        String RED          = "\u001B[31m"; // Красный цвет

        String initTime = (collectionManager.getLastInitTime() == null)
                ? RED + "Коллекция ещё не была инициализирована                      " + RESET
                : "Дата: " + collectionManager.getLastInitTime().toLocalDate() + " | Время: " + collectionManager.getLastInitTime().toLocalTime();

        //String saveTime = (collectionManager.getLastSaveTime() == null)
        //        ? RED + "Коллекция ещё не была сохранена                             " + RESET
        //        : "Дата: " + collectionManager.getLastSaveTime().toLocalDate() + " | Время: " + collectionManager.getLastSaveTime().toLocalTime();

        StringBuilder sb = new StringBuilder();


        sb.append("┌──────────────────────────────┬──────────────────────────────────────────────────────────────┐\n");
        sb.append(String.format("│ " + HEADER_COLOR + "%-28s" + RESET + " │ " + HEADER_COLOR + "%-61s" + RESET + "│%n", "Атрибут", "Значение"));
        sb.append("├──────────────────────────────┼──────────────────────────────────────────────────────────────┤\n");
        sb.append(String.format("│ %-28s │ %-60s │%n", "Тип коллекции", collectionManager.getCollection().getClass().getSimpleName()));
        sb.append(String.format("│ %-28s │ %-60s │%n", "Время инициализации", initTime));
        //sb.append(String.format("│ %-28s │ %-60s │%n", "Время сохранения", saveTime));
        sb.append(String.format("│ %-28s │ %-60d │%n", "Элементов в коллекции", collectionManager.getCollection().size()));
        sb.append("└──────────────────────────────┴──────────────────────────────────────────────────────────────┘\n");

        return new ExecStatus(sb.toString());
    }
}
