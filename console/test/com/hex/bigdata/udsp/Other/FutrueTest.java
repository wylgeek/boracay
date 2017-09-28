package com.hex.bigdata.udsp.Other;

import java.util.concurrent.*;
import java.lang.String;
public class FutrueTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.submit(new MyRunnable(5000));
        executor.submit(new MyRunnable(2000));
        executor.submit(new MyRunnable(1000));
        executor.submit(new MyRunnable(500));
    }
}

class MyRunnable implements Runnable{
    private Integer num;

    public MyRunnable(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    final ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void run() {
        try {
        Future<String> futureTask =executor.submit(new MyCallable(num));
        String result = futureTask.get(4000, TimeUnit.MILLISECONDS);
        System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

class MyCallable implements Callable<String>{

    private Integer num;

    public MyCallable(Integer num) {
        this.num = num;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(num);
        return num.toString();
    }
}