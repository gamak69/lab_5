package org.example.commands.simpl_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class FilterStartsWithName extends Command {
    CollectionManager collectionManager = CollectionManager.getInstance();
    public FilterStartsWithName(){
        super("filterStartsWithName", "Выводит элементы, значение поля name которых начинается с заданной подстроки", false);
    }

    @Override
    public boolean validate(String... args) {
        return args.length == 1;
    }

    @Override
    public ExecStatus execute(City city, String... args) {
        if (!validate(args)){
            return new ExecStatus(false, "Бабаба");
        }
        String start_name = args[0];
        StringBuilder sb = new StringBuilder();
        String HEADER_COLOR = "\u001B[34m"; // Синий цвет заголовка
        String RESET        = "\u001B[0m";  // Сброс цвета
        //String RED          = "\u001B[31m"; // Красный цвет
        //String ORANGE       = "\u001B[38;5;214m"; // ANSI 256-цветная палитра, код 214 (оранжевый)
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sb.append("┌───────────────────────────────────────────────────────────────────────┐\n");
        sb.append(String.format("│ " + HEADER_COLOR + "%-69s" + RESET + " │%n", String.format("Элементы, которые начинаются на '%s'", start_name)));

        for (City x : collectionManager) {
            if (x.getName().startsWith(start_name)) {
                sb.append("├───────────────────────────────────────────────────────────────────────┤\n");
                sb.append(String.format("│ ID: %-65d │%n", x.getId()));
                sb.append(String.format("│ Name: %-63s │%n", x.getName()));
                sb.append(String.format("│ Coordinates: X: %-10d; Y: %-38.2f │%n", x.getCoordinates().getX(), x.getCoordinates().getY()));
                sb.append(String.format("│ Creation Date: %-54s │%n", x.getCreationDate()));
                sb.append(String.format("│ Area: %-63d │%n", x.getArea()));
                sb.append(String.format("│ Population: %-57d │%n", x.getPopulation()));
                sb.append(String.format("│ Meters above sea level: %-45f │%n", x.getMetersAboveSeaLevel()));
                sb.append(String.format("│ Establishment Date: %-49s │%n", sdf.format(x.getEstablishmentDate())));
                sb.append(String.format("│ Government: %-57s │%n", x.getGovernment()));
                sb.append(String.format("│ Standard of Living: %-49s │%n", x.getStandardOfLiving()));
                sb.append(String.format("│ Governor: %-59s │%n", x.getGovernor().getHeight()));
            }
        }

        sb.append("└───────────────────────────────────────────────────────────────────────┘\n");
        return new ExecStatus(sb.toString());
    }
}
