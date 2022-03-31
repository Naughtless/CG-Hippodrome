package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hippodrome {
    //Properties
    protected static Hippodrome game;
    private List<Horse> horses;
    private List<RunnerService> runners;


    //Constructors
    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
        initRunnerThread();
    }


    //Methods
    private void initRunnerThread() {
        runners = new ArrayList<>();
        for(int i = 0 ; i < horses.size() ; ++i) {
            runners.add(new RunnerService(horses.get(i)));
        }
    }
    private void startGame() throws Exception {
        startRunning();

        for(int i = 0 ; i < 80 ; ++i) {
            printProgress();
            Thread.sleep(200);
        }

        stopRunning();
        game.printWinner();
    }
    private void printProgress() {
        for(int i = 0 ; i < horses.size() ; ++i) {
            horses.get(i).print();
        }
        for(int i = 0 ; i < 10 ; ++i) {
            System.out.println();
        }
    }
    private void printWinner() {
        System.out.println("THE WINNER IS : " + getWinner().getName());
    }

    private void startRunning() {
        for(int i = 0 ; i < runners.size() ; ++i) {
            runners.get(i).startRunning();
        }
    }
    private void stopRunning() {
        for(int i = 0 ; i < runners.size() ; ++i) {
            runners.get(i).stopRunning();
        }
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


    //Main Method
    public static void main(String[] args) throws Exception {
        //Initialize game object.
        game = new Hippodrome(
                new ArrayList<>(Arrays.asList(
                        new Horse("Warren Buffet", 3, 0),
                        new Horse("Bill Gates", 3, 0),
                        new Horse("Steve Jobs", 3, 0),
                        new Horse("Elon Musk", 3.1, 0)
                ))
        );
        game.startGame();
    }


}
