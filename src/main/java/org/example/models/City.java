package org.example.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class City implements Comparable<City>, Serializable, Validatable {
    private long id;                                    //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NonNull
    private String name;                                //Поле не может быть null, Строка не может быть пустой
    @NonNull
    @JsonUnwrapped
    private Coordinates coordinates;                    //Поле не может быть null
    private java.time.LocalDateTime creationDate;           //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long area;                                  //Значение поля должно быть больше 0, Поле не может быть null
    private Long population;                            //Значение поля должно быть больше 0, Поле не может быть null
    @NonNull
    private Float metersAboveSeaLevel;                  //Поле не может быть null
    @NonNull
    private java.util.Date establishmentDate;           //Поле не может быть null
    private Government government;                      //Поле может быть null
    @NonNull
    private StandardOfLiving standardOfLiving;          //Поле не может быть null
    @NonNull
    private Human governor;                             //Поле не может быть null

    //"Mon Nov 11 00:00:00 MSK 26644"

    @Override
    public boolean validate(){
        if(name.isEmpty()) return false;
        if(area <= 0) return false;
        if(population <= 0) return false;
        if (metersAboveSeaLevel.isNaN()) return false;
        return false;
    }

    @Override
    public int compareTo(City city) {
        return this.getName().compareTo(city.getName());
    }
}
