package org.example.commands.simpl_commands;

import org.example.commands.Command;
import org.example.manager.Console;

public class Help extends Command {
    public Help(Console console){
        super(console, "help", "вывести справку по доступным командам");
    }
    public void action(){
        console.println("help : вывести справку по доступным командам");
        console.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        console.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        console.println("add {element} : добавить новый элемент в коллекцию");
        console.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному");
        console.println("remove_by_id id : удалить элемент из коллекции по его id");
        console.println("clear : очистить коллекцию");
        console.println("save : сохранить коллекцию в файл");
        console.println("execute_script file_name : считать и исполнить скрипт из указанного файла");
        console.println("exit : завершить программу (без сохранения в файл)");
        console.println("remove_head : вывести первый элемент коллекции и удалить его");
        console.println("add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        console.println("history : вывести последние 15 команд (без их аргументов)");
        console.println("min_by_meters_above_sea_level : вывести любой объект из коллекции, значение поля metersAboveSeaLevel которого является минимальным");
        console.println("count_greater_than_establishment_date establishmentDate : вывести количество элементов, значение поля establishmentDate которых больше заданного");
        console.println("filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки");
    }
}
