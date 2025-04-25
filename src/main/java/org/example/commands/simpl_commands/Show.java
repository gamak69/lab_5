package org.example.commands.simpl_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Show extends Command {
    CollectionManager collectionManager = CollectionManager.getInstance();
    public Show(){
        super("show", "Выводит в стандартный поток вывода все элементы коллекции в строковом представлении", false);
    }

    @Override
    public boolean validate(String... args) {
        return args.length == 0;
    }

    @Override
    public ExecStatus execute(City city, String... args) {
        if (city != null){
            throw new IllegalArgumentException(String.format("Команде '%s' переданы никчемные, обосанные данные, фрик ты бл%n", getName()));
        }

        StringBuilder sb = new StringBuilder();
        String HEADER_COLOR = "\u001B[34m";
        String RESET = "\u001B[0m";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        sb.append("┌───────────────────────────────────────────────────────────────────────┐\n");
        sb.append(String.format("│ " + HEADER_COLOR + "%-69s" + RESET + " │%n", "Данные коллекции"));
        for (City x : collectionManager.getCollection()) {
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

        sb.append("└───────────────────────────────────────────────────────────────────────┘\n");
        return new ExecStatus(sb.toString());
        //return new ExecStatus("Тестовый вывод");
    }
}
