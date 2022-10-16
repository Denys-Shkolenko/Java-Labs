package ua.mylabs;

public class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton(){}

    public static ThreadSafeSingleton getInstanceUsingDoubleLocking() throws InterruptedException {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    Thread.sleep(5);
                    instance = new ThreadSafeSingleton();
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }
        return instance;
    }

}

