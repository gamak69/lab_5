package org.example.commands.simpl_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

public class ExecuteScript extends Command {
    CollectionManager collectionManager = CollectionManager.getInstance();
    public ExecuteScript(){
        super("execute", "Считывает и исполняет скрипт из указанного файла", false);
    }

    @Override
    public boolean validate(String... args) {
        return args.length == 1;
    }

    @Override
    public ExecStatus execute(City city, String... args) {
        if (!validate(args)){
            return new ExecStatus(false, "Иди нахуй, я заебался имена ошибкам придумывать");
        }
        return new ExecStatus(true, "");
    }
}
