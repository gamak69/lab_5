package org.example.utils;

import org.example.commands.Command;
import org.example.exeptions.ExitExсeption;
import org.example.manager.CommandManager;
import org.example.models.City;
import org.example.utils.console.Console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Runner {
    private final CommandManager commandManager = CommandManager.getInstance();
    private final Console console;
    private final Asker asker;
    private final Map<String, Integer> scriptStack = new HashMap<>();
    public Runner(Console console){
        this.console = console;
        asker = new Asker(console);
    }

    public void interactiveMode() {
        try {
            String line;
            while ((line = console.readln()) != null) {
                String[] tokens = line.trim().split(" ");
                if (!tokens[0].isEmpty()) {
                    ExecStatus execStatus = runCommand(tokens);
                    if (execStatus.isOk())
                        console.println(execStatus);
                    else
                        console.printerr(execStatus);
                }
            }
        } catch (ExitExсeption e) {
            console.println("Закрываем программу...");
        }
    }

    private ExecStatus runCommand(String[] tokens) throws ExitExсeption{
        if (tokens[0].equalsIgnoreCase("exit")){
            throw new ExitExсeption();
        }
        Command command = commandManager.getCommand(tokens[0]);
        if (command == null){
            return new ExecStatus(false, String.format("Комманды %s не существует", tokens[0]));
        }

        if (tokens[0].equals("execute")) {
            if (tokens.length != 2)
                return new ExecStatus(false, "Команде 'execute_script' были переданы невалидные аргументы Введите 'help' для справки.");
            console.printGritting(String.format("Запускаем скрипт '%s'", tokens[1]));
            return scriptMode(tokens[1]);
        }

        String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);
        if (!command.validate(args)){
            return new ExecStatus(false, "Неверное количество или тип аргументов");
        }


        City city = null;
        if (command.getNeedCity()){
            try {
                city = asker.askCity();
            } catch (ExitExсeption e) {
                return new ExecStatus(false, "Ты вышел из процесса добавления города, болван");
            }
        }
        ExecStatus execStatus = command.execute(city, args);
        commandManager.updateHistory(command);
        return execStatus;
    }

    private ExecStatus scriptMode(String filename) {
        scriptStack.merge(filename, 1, Integer::sum); // считаем количество запусков всех файлов
        BufferedReader defaultReader = console.getReader();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            console.setReader(reader);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.trim().split(" ");

                if (tokens.length == 0 || tokens[0].isEmpty())
                    continue; // Пропускаем пустые строки

                console.println("> " + line); // Показываем команду перед выполнением

                if (tokens[0].equals("execute")) {
                    if (scriptStack.get(filename) == 50)
                        return new ExecStatus(false, "Достигнут предел рекурсии!");
                }

                ExecStatus execStatus = runCommand(tokens);
                if (!execStatus.isOk())
                    console.printerr(execStatus.getMessage());
                else
                    console.println(execStatus.getMessage());
            }
            return new ExecStatus(true, "Скрипт выполнен успешно.");
        } catch (IOException e) {
            return new ExecStatus(false, "Ошибка чтения файла: " + e.getMessage());
        } catch (ExitExсeption e) {
            return new ExecStatus(false, "Скрипт принудительно завершен.");
        } catch (NullPointerException e) {
            return new ExecStatus(false, "Скрипт содержит невалидную команду! Аварийный выход...");
        }
        finally {
            console.setReader(defaultReader);
            scriptStack.merge(filename, 0, (oldValue, newValue) -> oldValue - 1);
        }
    }

}
