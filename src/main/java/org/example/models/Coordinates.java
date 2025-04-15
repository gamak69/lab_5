package org.example.models;

public class Coordinates implements Validatable{
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
