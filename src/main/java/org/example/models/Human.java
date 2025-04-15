package org.example.models;

public class Human implements Validatable{
    private Double height;
    public Human(Double height){
        if (height == 0){
            throw new IllegalArgumentException();
        }
        this.height = height;
    }

    @Override
    public boolean validate() {
        return height != 0;
    }
}
