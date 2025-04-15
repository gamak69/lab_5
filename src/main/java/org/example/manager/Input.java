package org.example.manager;

import java.util.Scanner;
import java.io.*;

public class Input {
    Console console;
    public Input(Console console){
        this.console = console;
    }
    public String input() throws IOException {
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        return reader.readLine();
    }



}
