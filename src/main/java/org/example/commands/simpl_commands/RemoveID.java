package org.example.commands.simpl_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

public class RemoveID extends Command {
    CollectionManager collectionManager = CollectionManager.getInstance();
    public RemoveID(){
        super("removeId {id}", "Удаляет элемент из коллекции по его id", false);
    }

    @Override
    public boolean validate(String... args) {
        return args.length == 1;
    }

    @Override
    public ExecStatus execute(City city, String... args){
        if (city != null){
            throw new IllegalArgumentException(String.format("Команде '%s' переданы никчемные, обосанные данные, фрик ты%n", getName()));
        }
        if (collectionManager.removeById(Long.parseLong(args[1]))){
            return new ExecStatus("Элемент успешно удален");
        }
        return new ExecStatus(false, "Элемент не удален");
    }
}
