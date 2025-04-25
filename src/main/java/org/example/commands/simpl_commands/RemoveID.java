package org.example.commands.element_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

public class RemoveID extends Command {
    public RemoveID(){
        super("RemoveById {id}", "Удаляет элемент из коллекции по его id", true);
    }
    CollectionManager collectionManager = CollectionManager.getInstance();
    public ExecStatus execute(City city, String... args){
        if (city == null || args.length != 2){
            throw new IllegalArgumentException(String.format("Команде '%s' переданы никчемные, обосанные данные, фрик ты%n", getName()));
        }
        if (collectionManager.removeById(Long.parseLong(args[1]))){
            return new ExecStatus("Элемент успешно удален");
        }
        return new ExecStatus(false, "Элемент не удален");
    }
}
