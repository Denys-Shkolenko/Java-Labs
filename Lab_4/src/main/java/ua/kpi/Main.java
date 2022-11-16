package ua.kpi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static class Sequence {

        int next = 0;
        public synchronized void getNext(){
            System.out.println(next++ + " in " + Thread.currentThread().getName());
        }

    }

    private static final ExecutorService exec = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        Sequence s = new Sequence();
        Runnable task = s::getNext;

        for (int i = 0; i < 15; i++) {
            exec.execute(task);
        }
        exec.shutdown();
    }
}