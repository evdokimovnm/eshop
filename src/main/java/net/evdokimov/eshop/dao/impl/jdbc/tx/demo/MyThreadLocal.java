package net.evdokimov.eshop.dao.impl.jdbc.tx.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MyThreadLocal<T> {
    private Map<Thread, T> holder = new ConcurrentHashMap<>();

    public T get() {
       return holder.get(Thread.currentThread());
    }

    public void put(T elem) {
        holder.put(Thread.currentThread(), elem);
    }
}

/*class Test {
    public static void main(String[] args) {
        final MyThreadLocal<String> threadLocal = new MyThreadLocal<>();
        threadLocal.put("Thread 1");
        System.out.println(threadLocal.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(threadLocal.get());
                threadLocal.put("Thread 2");
                System.out.println(threadLocal.get());
            }
        }).start();
    }
}*/
