package ua.mylabs;

public class Main {

    static class MyThread extends Thread {
        Sequence s;
        MyThread(Sequence s) {
            this.s = s;
        }
        @Override
        public void run() {
//            Object object1 = ThreadSafeSingleton.getInstanceUsingDoubleLocking();
//            Object object2 = LazyInitializedSingleton.getInstance();
            s.getNext();
        }
    }

    public static void main(String[] args) {
        Sequence s = new Sequence();
        MyThread[] t = new MyThread[10];
        for (int i = 0; i < t.length; i++) {
            t[i] = new MyThread(s);
        }

        for (int i = 0; i < t.length; i++) {
            t[i].start();
        }
    }
}