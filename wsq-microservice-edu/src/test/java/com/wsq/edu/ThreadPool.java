package com.wsq.edu;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-9-27 16:01
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPool {

    private static ExecutorService executorService = new ThreadPoolExecutor
            (100, 500, 1, TimeUnit.SECONDS, new LinkedBlockingDeque());

    public static <T> List<T> addTask(List<Callable<T>> tasks) {
        /**
         * java5提供了Future接口来代表Callable接口里的call()方法的返回值，
         * 并为Future接口提供了一个FutureTask实现类，
         * 该实现类实现了Future接口，并实现了Runnable接口，所以这样可以作为Thread的target。
         */
        List<Future<T>> futureList = null;
        List<T> results = null;
        try {
            //批量执行的最常用形式
            futureList = executorService.invokeAll(tasks);
            results = new ArrayList<>();
            for (Future<T> future : futureList) {
                //通过FutuerTask类的对象的get()方法来获取线程结束后的返回值
                results.add(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return results;
    }

}
