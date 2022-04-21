package com.github.naughtless.cg.hippodrome;

public class RunnerService implements Runnable{
    //Properties
    private Horse horse;
    private Thread currentThread;
    private boolean currentlyRunning;


    //Runnable Thread Method
    @Override
    public void run() {
        while(currentlyRunning) {
            horse.move();
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {}
        }
        System.out.println(horse.getName() + " has stopped running!");
    }


    //Constructors
    public RunnerService(Horse horse) {
        this.horse = horse;
    }


    //Methods
    public void startRunning() {
        currentlyRunning = true;
        currentThread = new Thread(this);
        currentThread.start();
    }
    public void stopRunning() {
        currentlyRunning = false;
        currentThread.interrupt();
    }
}
