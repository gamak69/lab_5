package org.example.commands.element_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

public class Add extends Command {
    private final CollectionManager collectionManager = CollectionManager.getInstance();
    public Add(){
        super("add", "добавить новый элемент в коллекцию", true);
    }

    @Override
    public boolean validate(String... args) {
        return args.length == 0;
    }

    @Override
    public ExecStatus execute(City city, String... args) {
        if (collectionManager.add(city)){
            return new ExecStatus("Город добавлен");
        }
        return new ExecStatus(false, "Организация не добавлена");
    }
}
