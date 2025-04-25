package org.example.commands.simpl_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

public class Exit extends Command {
    public Exit(){
        super("exit", "Завершает программу (без сохранения в файл)", false);
    }

    @Override
    public boolean validate(String... args) {
        return args.length == 0;
    }

    @Override
    public ExecStatus execute(City city, String... args){
        return null;
    }
}
