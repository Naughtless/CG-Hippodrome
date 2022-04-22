package com.github.naughtless.cg.hippodrome.arena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hippodrome {
    /*
    Properties -- Game Parameters
     */
    protected static Hippodrome game;
    private List<Horse> horses;
    private static final double targetDistance = 2000.0;



    /*
    Main Method.
     */
    public static void main(String[] args) throws Exception {
        //Initialize game object.
        game = new Hippodrome(
                new ArrayList<>(Arrays.asList(
                        new Horse("<<--== Buffet", 3, targetDistance),
                        new Horse("<<--== Gates", 3, targetDistance),
                        new Horse("<<--== Jobs", 3, targetDistance),
                        new Horse("<<--== Musk", 3.1, targetDistance)
                ))
        );

        game.startGame();
    }



    /*
    Constructor.
     */
    public Hippodrome(List<Horse> horses) {
        //Populate horses.
        this.horses = horses;
    }


    /*
    Operations Methods.
     */
    //Starts the game.
    private void startGame() {
        //Horses Start Running
        startRunning();

        //Progress Tracker
        while (!testForWin()) {
            printProgress();
            try{
                //Simulates the 8.33-second delay to emulate 120 Hz refresh rate.
                Thread.sleep(9);
            } catch(Exception ignored) {}
        }

        stopRunning();
        game.printWinner();
    }

    //Methods to call on runner threads to start.
    private void startRunning() {
        for (Horse currentHorse : horses) {
            currentHorse.startRunning();
        }
    }
    private void stopRunning() {
        for (Horse currentHorse : horses) {
            currentHorse.stopRunning();
        }
    }

    private void printProgress() {
        for (Horse currentHorse : horses) {
            currentHorse.printProgress();
        }

        //Spacing
        System.out.println("\n".repeat(10));
    }
    private void printWinner() {
        System.out.println("THE WINNER IS : " + getWinner().getHorseName());

        //Debug -- Print Final Distances
        for(Horse currentHorse : horses) {
            System.out.println(currentHorse.getHorseName() + " DIST " + currentHorse.getDistance());
        }
    }

    private boolean testForWin() {
        for(Horse currentHorse: horses) {
            if(currentHorse.getVictoryStatus()) {
                return true;
            }
        }
        return false;
    }




    //Standard Setter Getters
    public List<Horse> getHorses() {
        return horses;
    }
    public Horse getWinner() {
        Horse winnerHorse = horses.get(0);
        for(int i = 0 ; i < horses.size() ; ++i) {
            if(horses.get(i).getDistance() >= winnerHorse.getDistance()) {
                winnerHorse = horses.get(i);
            }
        }
        return winnerHorse;
    }
}
