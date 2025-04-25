package org.example.commands.simpl_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class RemoveHead extends Command {
    CollectionManager collectionManager = CollectionManager.getInstance();
    public RemoveHead(){
        super("removeHead", "Выводит первый элемент коллекции и удаляет его", false);
    }

    @Override
    public boolean validate(String... args) {
        return args.length == 0;
    }

    @Override
    public ExecStatus execute(City city, String... args) {
        City remove_city = collectionManager.getCollection().pollFirst();
        if (remove_city == null){
            return new ExecStatus("Коллекция пустая, Ну шо, Наталья, походу мы обосрались");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        StringBuilder sb = new StringBuilder();
        String HEADER_COLOR = "\u001B[38;5;214m";
        String RESET = "\u001B[0m";
        City x = remove_city;
        sb.append("┌───────────────────────────────────────────────────────────────────────┐\n");
        sb.append(String.format("│ " + HEADER_COLOR + "%-69s" + RESET + " │%n", "Данные удаленной коллекции"));
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
        sb.append("└───────────────────────────────────────────────────────────────────────┘\n");

        return new ExecStatus(sb.toString());
    }
}
