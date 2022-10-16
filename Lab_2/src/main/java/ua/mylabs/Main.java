package ua.mylabs;

public class Main {

    static class MyThread extends Thread {
        Sequence s;
        MyThread(Sequence s) {
            this.s = s;
        }
        @Override
        public void run() {
//            try {
//            ThreadSafeSingleton obj1 = ThreadSafeSingleton.getInstanceUsingDoubleLocking();
//                LazyInitializedSingleton obj2 = LazyInitializedSingleton.getInstance();
//            } catch (InterruptedException e) {
//                System.out.println("Exception: " + e);
//            }
            s.getNext();
        }
    }

    public static void main(String[] args) {

        Sequence s = new Sequence();
        MyThread[] t = new MyThread[10];

        for (int i = 0; i < t.length; i++) {
            t[i] = new MyThread(s);
        }

        for (MyThread myThread : t) {
            myThread.start();
        }
    }
}