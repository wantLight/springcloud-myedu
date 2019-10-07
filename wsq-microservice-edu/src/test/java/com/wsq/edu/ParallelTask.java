package com.wsq.edu;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-9-27 16:22
 */
import java.util.*;
import java.util.function.Consumer;

public class ParallelTask {

    private static int listcount = 0;

    /**
     * 矢量队列
     * Vector 可实现自动增长的对象数组
     * 可以往其中随意插入不同类的对象，即不需顾及类型也不需预先选定向量的容量，并可以方便地进行查找。
     */
    private static Vector datas = new Vector();

    public class RunThread extends Thread {
        Consumer task, fianltask;

        public RunThread(String name, Consumer task, Consumer ftask) {
            super(name);
            this.task = task;
            this.fianltask = ftask;
        }

        /**
         * 给线程设置一个中断标志，线程仍会继续运行
         */
        @Override
        public void interrupt() {
            super.interrupt();
            /**
             * 保证在同一时刻，只有一个线程可以执行某个方法或某个代码块
             */
            synchronized (this) {
                listcount--;
                if (listcount == 0) {
                    // 我是最后一个了，那么，好吧

                    fianltask.accept(null);
                }
            }
        }

        @Override
        public void run() {
            while (!datas.isEmpty()) {
                HashMap<String, Object> data = (HashMap) datas.remove(0);
                System.out.println("线程" + getName() + " 开始处理新数据 " + data.get("id"));
                if (task != null) task.accept(data);
                System.out.println("线程" + getName() + " 处理数据完成 " + data.get("id"));
            }
            synchronized (this) {
                listcount--;
                if (listcount == 0) {
                    // 我是最后一个了，那么，好吧
                    fianltask.accept(null);
                }
            }
        }
    }


    /**
     * 步骤-1
     * @param task
     * @param finaltask
     * @param datas
     * @param maxThreads
     */
    void runTask(Consumer task, Consumer finaltask, List datas, int maxThreads) {
        ParallelTask.listcount = maxThreads;
        this.datas.addAll(datas);
        for (int i = 0; i < maxThreads; i++) {
            new RunThread(String.valueOf(i), task, finaltask).start();
        }
    }

    public static void main(String[] args) throws Exception {
        ParallelTask pt = new ParallelTask();

        ArrayList<Object> datas = new ArrayList();
        for (int i = 1; i < 33; i++) {
            HashMap<String, Object> d = new HashMap<>();
            d.put("id", "task_" + i);
            d.put("data", i * 3);
            datas.add(d);
        }

        pt.runTask(o -> {
            try {
                System.out.println("do...... " + ((HashMap) o).get("data"));
                Thread.sleep(Math.round(Math.random() * 60 + 5) * 100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, o -> {
            System.out.println("全部工作完成了，欢迎下次再来！！！");
        }, datas, 10);
    }

}
