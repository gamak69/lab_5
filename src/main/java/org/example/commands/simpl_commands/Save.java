package org.example.commands.simpl_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

import java.io.IOException;

public class Save extends Command {
    CollectionManager collectionManager = CollectionManager.getInstance();
    public Save(){
        super("save", "Сохраняет коллекцию в файл", false);
    }

    @Override
    public boolean validate(String... args) {
        return args.length == 0;
    }

    @Override
    public ExecStatus execute(City city, String... args) {
        if (!validate(args)){
            return new ExecStatus(false, "...");
        }
        try {
            collectionManager.save();
            return new ExecStatus("Коллекция успешно сохранена");
        } catch (IOException e){
            e.printStackTrace();
            return new ExecStatus(false, "Не сохранилось " + e.getMessage());
        }
    }
}
