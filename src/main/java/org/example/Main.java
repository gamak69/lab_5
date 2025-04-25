package org.example;


import org.example.utils.Runner;
import org.example.utils.console.Console;
import org.example.utils.console.StandardConsole;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args){
        Console console = new StandardConsole(new BufferedReader(new InputStreamReader(System.in)));
        Runner runner = new Runner(console);

        console.printGritting("Приветствуем вас в приложении для модерации списка городов!");
        console.printGritting("Введите 'help' для получения справки по командам");

        runner.interactiveMode();
    }
}