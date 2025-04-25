package org.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class Coordinates implements Validatable, Serializable {
    private Long x;
    private Double y;
    public Coordinates(Long x, Double y){
        if (x == null || y == null || y <= -659){
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean validate(){
        return y > -659;
    }
}
