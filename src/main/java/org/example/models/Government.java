package org.example.models;

public enum Government {
    ANARCHY("Анархия"),
    DICTATORSHIP("Диктатура"),
    PUPPET_STATE("Марионеточное государство"),
    REPUBLIC("Республика"),
    ETHNOCRACY("Этнократия");
    private String name;
    Government(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
