package com.wsq.edu;



import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-9-27 16:02
 */
public class Demo2ServiceTest {

    @Test
    public void test(){
        /**
         *
         */
        List<Callable<String>> callables = new ArrayList<>();
        for(int i=0;i<10;i++){
            callables.add(()-> Math.random()+"");
        }
        List<String> res = ThreadPool.addTask(callables);
        System.out.println("**********************");
        for(String str : res){
            System.out.println(str);
        }
    }
}
