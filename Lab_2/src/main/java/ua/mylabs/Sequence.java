package ua.mylabs;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Sequence {

    private int counter;
//    private AtomicInteger increment = new AtomicInteger(0);
    ReentrantLock lock = new ReentrantLock();

    public void getNext() {
//        System.out.println(increment.getAndIncrement() + " in " + Thread.currentThread().getName());
        lock.lock();
        try {
            System.out.println(counter++ + " in " + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }

}
