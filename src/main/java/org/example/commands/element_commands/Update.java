package org.example.commands.element_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

public class Update extends Command {
    private final CollectionManager collectionManager = CollectionManager.getInstance();
    public Update(){
        super("update", "Обновляет значение элемента коллекции, id которого равен заданному", true);
    }

    @Override
    public boolean validate(String... args) {
        if (args.length != 1) return false;
        try {
            Long.parseLong(args[0]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public ExecStatus execute(City city, String... args){
        if (collectionManager.update(Long.parseLong(args[0]), city)){
            return new ExecStatus("Коллекция успешно обновлена");
        }
        return new ExecStatus(false, "Коллекция не обновлена");
    }
}