package org.example.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import lombok.NonNull;

public class City implements Comparable<City>, Serializable, Validatable {
    private long id;                                    //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NonNull
    private String name;                                //Поле не может быть null, Строка не может быть пустой
    @NonNull
    private Coordinates coordinates;                    //Поле не может быть null
    private java.time.LocalDate creationDate;           //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long area;                                  //Значение поля должно быть больше 0, Поле не может быть null
    private Long population;                            //Значение поля должно быть больше 0, Поле не может быть null
    @NonNull
    private Float metersAboveSeaLevel;                  //Поле не может быть null
    @NonNull
    private java.time.ZonedDateTime establishmentDate;  //Поле не может быть null
    private Government government;                      //Поле может быть null
    @NonNull
    private StandardOfLiving standardOfLiving;          //Поле не может быть null
    @NonNull
    private Human governor;                             //Поле не может быть null

    @Override
    public boolean validate(){
        if(name.isEmpty()) return false;
        if(area <= 0) return false;
        if(population <= 0) return false;
        if (metersAboveSeaLevel.isNaN()) return false;
        
        return false;
    }

    public @NonNull String getName() {
        return name;
    }

    @Override
    public int compareTo(City city) {
        return this.getName().compareTo(city.getName());
    }
}
