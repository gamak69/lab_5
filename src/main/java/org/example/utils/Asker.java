package org.example.utils;

import org.example.exeptions.ExitExсeption;
import org.example.manager.CollectionManager;
import org.example.manager.IdManager;
import org.example.models.*;

import org.example.utils.console.Console;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Asker {
    private final CollectionManager collectionManager = CollectionManager.getInstance();
    private Console console;
    public Asker(Console console){
        this.console = console;
    }
    public City askCity() throws ExitExсeption {
        console.println("Для выполнения команды требуется ввести информацию о городе");
        console.println("* введите 'exit' для отмены операции *");
        long id = IdManager.next_id();
        String name = askName();
        Coordinates coordinates = askCoordinates(name);
        LocalDateTime creationDate = LocalDateTime.now();
        Long area = askArea(name);
        Long population = askPopulation(name);
        Float metersAboveSeaLevel = askMetersAbolveLevel(name);
        Date establishmentDate = askEstablishmentDate(name);
        Government government = askGovernment(name);
        StandardOfLiving standardOfLiving = askStandardOfLiving(name);
        Human governor = askGovernor(name);
        return new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, establishmentDate, government, standardOfLiving, governor);
    }
    private String askName() throws ExitExсeption{
        String name;

        do {
            console.print("Введите название города: ");
            String input = console.readln();
            if (input == null){
                throw new ExitExсeption();
            }
            name = input.trim();
            if (name.equalsIgnoreCase("exit")){
                throw new ExitExсeption();
            }

        } while (name.isEmpty());
        return name;
    }
    private Coordinates askCoordinates(String name) throws ExitExсeption{
        Long x = null;
        do {
            console.print("Введите координаты \"x\" города: ");
            String input = console.readln();
            if (input == null){
                throw new ExitExсeption();
            }
            input = input.trim();
            if (input.equalsIgnoreCase("exit")){
                throw new ExitExсeption();
            }
            if (!input.isEmpty()){
                try {
                    x = Long.parseLong(input);
                } catch (IllegalArgumentException e){
                    console.printerr("Данное значение хуйня какая-то, переделывайте");
                }

            }
        } while (x == null);
        Double y = null;
        do {
            console.print("Введите координаты \"y\" города: ");
            String input = console.readln();
            if (input == null){
                throw new ExitExсeption();
            }
            input = input.trim();
            if (input.equalsIgnoreCase("exit")){
                throw new ExitExсeption();
            }
            if (!input.isEmpty()){
                try {
                    y = Double.parseDouble(input);
                    if (y <= -659){
                        y = null;
                        console.printerr("Данное значение хуйня какая-то, переделывайте");
                    }
                } catch (IllegalArgumentException e){
                    console.printerr("Данное значение хуйня какая-то, переделывайте");
                }
            }
        } while (y == null);
        return new Coordinates(x,y);
    }
    private Long askArea(String name) throws ExitExсeption{
        Long area = null;
        do{
            console.print("Введите площадь города: ");
            String input = console.readln();
            if (input == null){
                throw new ExitExсeption();
            }
            input = input.trim();
            if (input.equalsIgnoreCase("exit")){
                throw new ExitExсeption();
            }
            if (!input.isEmpty()) {
                try {
                    area = Long.parseLong(input);
                    if (area <= 0){
                        area = null;
                        console.printerr("Данное значение хуйня какая-то, переделывайте");
                    }
                } catch (NumberFormatException e){
                    console.printerr("Данное значение хуйня какая-то, переделывайте");
                }
            }
        } while (area == null);
        return area;
    }
    private Long askPopulation(String name) throws ExitExсeption{
        Long population = null;
        do {
            console.print("Введите численность населения города: ");
            String input = console.readln();
            if (input == null){
                throw new ExitExсeption();
            }
            input = input.trim();
            if (input.equalsIgnoreCase("exit")){
                throw new ExitExсeption();
            }
            if (!input.isEmpty()){
                try {
                    population = Long.parseLong(input);
                    if (population <= 0){
                        population = null;
                        console.printerr("Данное значение хуйня какая-то, переделывайте");
                    }
                } catch (NumberFormatException e){
                    console.printerr("Данное значение хуйня какая-то, переделывайте");
                }
            }
        } while (population == null);
        return population;
    }

    private Float askMetersAbolveLevel(String name) throws ExitExсeption{
        Float meretsAbolveLevle = null;
        do {
            console.print("Введите высоту над уровнем моря: ");
            String input = console.readln();
            if (input == null){
                throw new ExitExсeption();
            }
            input = input.trim();
            if (input.equalsIgnoreCase("exit")){
                throw new ExitExсeption();
            }
            if (!input.isEmpty()){
                try {
                    meretsAbolveLevle = Float.parseFloat(input);
                    if (meretsAbolveLevle <= 0){
                        meretsAbolveLevle = null;
                        console.printerr("Данное значение хуйня какая-то, переделывайте");
                    }
                } catch (NumberFormatException e){
                    console.printerr("Данное значение хуйня какая-то, переделывайте");
                }
            }
        } while (meretsAbolveLevle == null);
        return meretsAbolveLevle;
    }

    private Date askEstablishmentDate(String name) throws ExitExсeption {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
        dateFormat.setLenient(false);

        Date date = null;
        do {
            console.print("Введите дату основания города (формат: ДД-ММ-ГГГГ): ");
            String input = console.readln();

            if (input == null){
                throw new ExitExсeption();
            }
            input = input.trim();
            if (input.equalsIgnoreCase("exit")) {
                throw new ExitExсeption();
            }
            if (!input.isEmpty()) {
                try {
                    date = dateFormat.parse(input);
                } catch (ParseException e) {
                    console.printerr("Некорректная дата. Пример: 11-09-2001");
                }
            }

        } while (date == null);

        return date;
    }

    private Government askGovernment(String name) throws ExitExсeption{
        Government value = null;
        do {
            console.print("Введите систему управления: ANARCHY, DICTATORSHIP, PUPPET_STATE, REPUBLIC, ETHNOCRACY: ");
            String input = console.readln();
            if (input == null){
                throw new ExitExсeption();
            }
            input = input.trim();
            if (input.equalsIgnoreCase("exit")){
                throw new ExitExсeption();
            }
            if (!input.isEmpty()){
                try {
                    if (Government.getValuesBoolean(input)){
                        value = Government.getValues(input);
                    }
                    else {console.printerr("Данное значение хуйня какая-то, переделывайте");}
                } catch (IllegalArgumentException e){
                    console.printerr("Данное значение хуйня какая-то, переделывайте");
                }
            }
        } while (value == null);
        return value;
    }
    private StandardOfLiving askStandardOfLiving(String name) throws ExitExсeption{
        StandardOfLiving value = null;
        do {
            console.print("Введите уровень жизни: ULTRA_HIGH, VERY_HIGH, NIGHTMARE: ");
            String input = console.readln();
            if (input == null){
                throw new ExitExсeption();
            }
            input = input.trim();
            if (input.equalsIgnoreCase("exit")){
                throw new ExitExсeption();
            }
            if (!input.isEmpty()){
                try {
                    if (StandardOfLiving.getValuesBoolean(input)){
                        value = StandardOfLiving.getValues(input);
                    }
                    else {
                        console.printerr("Данное значение хуйня какая-то, переделывайте");
                    }
                } catch (IllegalArgumentException e){
                    console.printerr("Данное значение хуйня какая-то, переделывайте");
                }
            }
        }while (value == null);
        return value;
    }
    private Human askGovernor(String name) throws ExitExсeption{
        Double height = null;
        do {
            console.print("Введите рост мэра: ");
            String input = console.readln();
            if (input == null){
                throw new ExitExсeption();
            }
            input = input.trim();
            if (input.equalsIgnoreCase("exit")){
                throw new ExitExсeption();
            }
            if (!input.isEmpty()){
                try {
                    height = Double.parseDouble(input);
                    if (height <= 0){
                        height = null;
                        console.printerr("Данное значение хуйня какая-то, переделывайте");
                    }
                } catch (NumberFormatException e){
                    console.printerr("Данное значение хуйня какая-то, переделывайте");
                }
            }
        }while (height == null);
        return new Human(height);
    }

}
