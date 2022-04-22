package com.github.naughtless.cg.hippodrome.arena;

public class Horse implements Runnable{
    /*
    Properties
     */
    //Horse Properties
    private String horseName;
    private double topSpeed;
    private double currentSpeed;
    private double distance;

    private final double baseFailureRate = 0.01; //Default 0.015 -> Equates 0.5% failure rate per tick.
    private boolean hasWon;
    private boolean isRunning;

    //Game Properties
    private double targetDistance;


    /*
    Horse Constructor
     */
    public Horse(String horseName, double speed, double targetDistance) {
        this.horseName = horseName;
        this.topSpeed = speed;
        this.distance = 0;
        this.targetDistance = targetDistance;

        this.hasWon = false;
        this.isRunning = false;
    }



    /*
    Operational Methods
     */
    protected void attemptMove(){
        testForVictory();
        testForFall();

        distance += (currentSpeed * Math.random());

        attemptRestoreSpeed();
    }
    protected void printProgress() {
        clearConsole();
        int amountOfDots = (int) distance / 10;
        System.out.print(".".repeat(amountOfDots));
        System.out.println(horseName);
    }

    //Test for conditions.
    private void testForFall() {
        //Horse cannot fall before it re-reaches top speed.
        if(currentSpeed < topSpeed) {
            return;
        }

        double fallValue = (currentSpeed * Math.random());
        if(fallValue < baseFailureRate) {
            // Temporary Speed Penalty (20-30 %)
            // 20% FLAT + 0-10% VAR
            currentSpeed = currentSpeed - ((currentSpeed * 0.2) + (currentSpeed * 0.1 * Math.random()));

            // Top Speed Penalty 100 - (2-5)%
            // 2% FLAT + 0-3% VAR
            topSpeed = topSpeed - ((topSpeed * 0.02) + (topSpeed * 0.03 * Math.random()));
        }
    }
    public void testForVictory() {
        hasWon = distance >= targetDistance;
    }

    //Gradual Penalty Reduction
    private void attemptRestoreSpeed() {
        // Temp penalty reduction per tick (1-2% of topSpeed).
        if(currentSpeed < topSpeed) {
            currentSpeed += ((topSpeed * 0.01) + (topSpeed * 0.01 * Math.random()));

            if(currentSpeed > topSpeed) {
                currentSpeed = topSpeed;
            }
        }
    }

    //Clear Console
    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }



    /*
    Runner Service
     */
    private Thread currentThread;

    public void run() {
        while(isRunning) {
            attemptMove();
            try {
                //Simulates the 8.33-second delay to emulate 120 Hz refresh rate.
                //Runs faster, at 8ms delay compared to the 9ms display delay.
                Thread.sleep(8);
            } catch(Exception ignored) {}
        }
        System.out.println(horseName + " has stopped running!");
    }

    public void startRunning() {
        currentThread = new Thread(this);
        isRunning = true;
        currentThread.start();
    }
    public void stopRunning() {
        isRunning = false;
    }


    /*
    Setters & Getters
     */
    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }
    public void setSpeed(double speed) {
        this.topSpeed = speed;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public String getHorseName() {
        return horseName;
    }
    public double getSpeed() {
        return topSpeed;
    }
    public double getDistance() {
        return distance;
    }
    public boolean getVictoryStatus() {
        return hasWon;
    }
}
