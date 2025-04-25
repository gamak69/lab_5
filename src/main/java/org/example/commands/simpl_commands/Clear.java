package org.example.commands.simpl_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

public class Clear extends Command {
    private final CollectionManager collectionManager = CollectionManager.getInstance();
    public Clear(){
        super("clear", "Очищает коллекцию", false);
    }

    @Override
    public boolean validate(String... args) {
        return args.length == 0;
    }

    @Override
    public ExecStatus execute(City city, String... args) {
        if (!validate(args)){
            return new ExecStatus(false, "Бубубу");
        }
        collectionManager.clear();
        return new ExecStatus("Коллекция успешно очищена");
    }
}
