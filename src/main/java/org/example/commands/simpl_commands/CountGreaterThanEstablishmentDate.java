package org.example.commands.simpl_commands;

import org.example.commands.Command;
import org.example.manager.CollectionManager;
import org.example.models.City;
import org.example.utils.ExecStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CountGreaterThanEstablishmentDate extends Command {
    private final CollectionManager collectionManager = CollectionManager.getInstance();
    public CountGreaterThanEstablishmentDate(){
        super("countGreaterThanEstablishmentDate", "Выводит количество элементов, значение поля establishmentDate которых больше заданного", false);
    }

    @Override
    public boolean validate(String... args) {
        return args.length == 1;
    }
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    @Override
    public ExecStatus execute(City city, String... args) {
        if (!validate(args)){
            return new ExecStatus(false,"Бухахаха");
        }
        Date input_date;
        try {
            input_date = dateFormat.parse(args[0]);
        } catch (ParseException e) {
            return new ExecStatus(false, "Некорректный формат даты. Используйте ДД-ММ-ГГГГ");
        }

        long count = collectionManager.getCollection().stream()
                .filter(c -> c.getEstablishmentDate().after(input_date))
                .count();

        return new ExecStatus(true, "Количество элементов: " + count);
    }
}
