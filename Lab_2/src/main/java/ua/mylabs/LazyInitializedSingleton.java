package ua.mylabs;

public class LazyInitializedSingleton {

    private static LazyInitializedSingleton instance;

    private LazyInitializedSingleton() {}

    public static LazyInitializedSingleton getInstance() throws InterruptedException {
        if (instance == null) {
            Thread.sleep(5);
            instance = new LazyInitializedSingleton();
            System.out.println(Thread.currentThread().getName());
        }
        return instance;
    }

}

