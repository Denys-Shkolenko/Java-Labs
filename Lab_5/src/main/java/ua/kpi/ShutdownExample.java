package ua.kpi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutdownExample {

    private static final ExecutorService ex = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " started");
            int n = 1;
            while (!ex.isShutdown()) {
                n++;
            }
            System.out.println(Thread.currentThread().getName() + " finished after " + n + " iterations");
        };

        for (int i = 0; i < 5; i++) {
            ex.execute(task);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException: " + e);
            }
        }
        ex.shutdown();

    }
}
