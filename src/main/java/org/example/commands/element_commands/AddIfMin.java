package org.example.commands.element_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

public class AddIfMin extends Command {
    private final CollectionManager collectionManager = CollectionManager.getInstance();
    public AddIfMin(){
        super("addIfMin", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции", true);
    }

    @Override
    public boolean validate(String... args) {
        return args.length == 0;
    }

    @Override
    public ExecStatus execute(City city, String... args){
        if (collectionManager.getCollection().isEmpty()){
            collectionManager.add(city);
            return new ExecStatus("Коллекция добавлена т.к изначально была пустая");
        }
        City city_min = collectionManager.getMin();
        if (city.compareTo(city_min) < 0){
            collectionManager.add(city);
            return new ExecStatus("Все заебок, город ваще лоховской и добавлен так как обосаннее всех");
        }
        else{
            return new ExecStatus("Город оказался не лоховским, поэтому не добавлен (он больше минимального)");
        }
    }
}
