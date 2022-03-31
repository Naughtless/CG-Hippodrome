package org.example;

public class Horse {
    //Properties
    private String name;
    private double speed;
    private double distance;


    //Constructors
    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }


    //Methods
    protected void move(){
        distance += (speed * Math.random());
    }
    protected void print() {
        clearConsole();
        int amountOfDots = (int) distance;
        System.out.print(".".repeat(amountOfDots));
        System.out.println(name);
    }
    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    //Standard Setter Getters
    public void setName(String name) {
        this.name = name;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public String getName() {
        return name;
    }
    public double getSpeed() {
        return speed;
    }
    public double getDistance() {
        return distance;
    }
}
