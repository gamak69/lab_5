package org.example.commands;

import lombok.Getter;
import org.example.models.City;
import org.example.utils.ExecStatus;

public abstract class Command {
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final Boolean needCity;
    protected Command(String name, String description, boolean needCity){
        this.name = name;
        this.description = description;
        this.needCity = needCity;
    }
    public boolean validate(String... args){
        return true;
    }
    public abstract ExecStatus execute(City city, String... args);
}
