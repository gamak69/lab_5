package org.example.utils.files;

import lombok.Getter;

public class AppConfig {
    @Getter
    private final String filePath;
    public AppConfig(String envVariableName){
        this.filePath = validateEnvVariable(envVariableName);
    }
    private String validateEnvVariable(String name){
        String value = System.getenv(name);
        if (value == null || value.isEmpty()){
            throw new IllegalStateException(String.format("Ошибка: Переменная окружения '%s' не задана, балбес.%n", name));
        }
        return value;
    }
}
