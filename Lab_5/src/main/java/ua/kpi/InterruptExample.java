package ua.kpi;

public class InterruptExample {

    public static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " started");
            int n = 1;
            while (!isInterrupted()) {
                n++;
            }
            System.out.println(Thread.currentThread().getName() + " finished after " + n + " iterations");
        }

    }

    public static void main(String[] args) {

        MyThread[] t = new MyThread[5];

        for (int i = 0; i < t.length; i++) {
            t[i] = new MyThread();
            t[i].start();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException: " + e);
        }

        for (MyThread myThread : t) {
            myThread.interrupt();
        }

    }
}