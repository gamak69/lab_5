package org.example.models;

import java.io.Serializable;

public enum Government implements Serializable {
    ANARCHY("Анархия"),
    DICTATORSHIP("Диктатура"),
    PUPPET_STATE("Марионеточное государство"),
    REPUBLIC("Республика"),
    ETHNOCRACY("Этнократия");
    private final String name;
    Government(String name){
        this.name = name;
    }
    public static Government getValues(String name){
        for(Government government : values()){
            if (government.name.equalsIgnoreCase(name) || government.name().equalsIgnoreCase(name)){
                return government;
            }
        }
        return null;
    }
    public static boolean getValuesBoolean(String name){
        for(Government government : values()){
            if (government.name.equalsIgnoreCase(name) || government.name().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
}
