package org.example.models;

public enum StandardOfLiving {
    ULTRA_HIGH("Невообразимо высокий"),
    VERY_HIGH("Очень высокий"),
    NIGHTMARE("Кошмар");
    private String name;
    StandardOfLiving(String name){
        this.name = name;
    }
    public static StandardOfLiving getValues(String name){
        for (StandardOfLiving e : values()){
            if (e.name.equals(name)){
                return e;
            }
        }
        return null;
    }
}
