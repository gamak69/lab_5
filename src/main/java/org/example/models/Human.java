package org.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class Human implements Validatable, Serializable {
    private Double height;
    public Human(Double height){
        if (height <= 0){
            throw new IllegalArgumentException();
        }
        this.height = height;
    }

    @Override
    public boolean validate() {
        return height > 0;
    }
}
