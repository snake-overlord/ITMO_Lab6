package models;

import java.io.Serializable;

/**
 * Coordinates class with properties <b>x</b>, <b>y</b>.
 */
public class Coordinates implements Serializable {
    private double x;
    private int y;

    /**
     *
     * @param x - X coordinate
     * @param y - Y coordinate
     */
    public Coordinates(double x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates(){};


    public int getY() {
        return y;
    }
    public void setX(double x){
        try{
            this.x = x;
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
    }

    public double getX() {
        return x;
    }
    public void setY(int y){
        try{this.y = y;
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
    }
}

