package org.example.models;

import lombok.Data;

import java.io.Serializable;

public enum StandardOfLiving implements Serializable {
    ULTRA_HIGH("Невообразимо высокий"),
    VERY_HIGH("Очень высокий"),
    NIGHTMARE("Кошмар");
    private String name;
    StandardOfLiving(String name){
        this.name = name;
    }
    public static StandardOfLiving getValues(String name){
        for (StandardOfLiving e : values()){
            if (e.name.equalsIgnoreCase(name) || e.name().equalsIgnoreCase(name)){
                return e;
            }
        }
        return null;
    }
    public static boolean getValuesBoolean(String name){
        for (StandardOfLiving e : values()){
            if (e.name.equalsIgnoreCase(name) || e.name().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
}
