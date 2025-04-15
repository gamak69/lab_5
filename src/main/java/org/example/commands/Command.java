package org.example.commands;

import lombok.Getter;
import org.example.manager.Console;

public abstract class Command {
    @Getter
    private final String name;
    @Getter
    private final String description;
    private final Boolean needCity;
    protected Command(String name, String description, boolean needCity){
        this.name = name;
        this.description = description;
        this.needCity = needCity;
    }
    public boolean needCity(){
        return needCity;
    }
}
