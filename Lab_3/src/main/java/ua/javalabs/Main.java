package ua.javalabs;

public class Main {

    public static ThreadLocal<Double> threadLocalValue = new ThreadLocal<>();
//    public static Double threadLocalValue = 0.0;

    private static class ReaderThread extends Thread {
        public void run() {
            threadLocalValue.set(Math.random());
//            threadLocalValue = Math.random();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocalValue.get() + " in " + Thread.currentThread().getName());
//            System.out.println(threadLocalValue + " in " + Thread.currentThread().getName());
        }
    }

    public static Boolean stop = false;

    public static class MyThread extends Thread {

        public void run() {
            int i = 1;
            while (!stop) {
                i++;
            }
            System.out.println("Thread stop i=" + i);
        }
    }

    public static void main(String[] args) {
        MyThread test = new MyThread();
        test.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop = true;
        System.out.println("now, in main thread stop is:" + stop);

//        for (int i = 0; i < 10; i++) {
//            new ReaderThread().start();
//        }
    }
}