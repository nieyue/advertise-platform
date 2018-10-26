package com.yayao;

import java.util.concurrent.*;

public class FutureTest {
    static String callphone()   {
        System.out.println("callphone");
        try {
            Thread.sleep(1000l);
            return "callphone";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    static String sendmail()  {
        System.out.println("sendmail");
        try {
            Thread.sleep(1000l);
            return "sendmail";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    static void  syn(){
        long starttime=System.currentTimeMillis();
        System.out.println("start="+starttime);
        callphone();
        sendmail();
        long endtime=System.currentTimeMillis();
        System.out.println("end="+endtime);
        System.out.println("cost="+(endtime-starttime));
    }

    static void  async(){
        long starttime=System.currentTimeMillis();
        System.out.println("start="+starttime);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Long> future = executor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                callphone();
                return null;
            }
        });
        Future<Long> future2 = executor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                sendmail();
                return null;
            }
        });
        boolean flag = true;
        while(flag) {
            if (future.isDone() && future2.isDone()) {
                try {

                    future.get();
                    future2.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                //callphone();
                //sendmail();
                long endtime = System.currentTimeMillis();
                System.out.println("end=" + endtime);
                System.out.println("cost=" + (endtime - starttime));
                flag=false;
            }
        }
    }
    public static void main(String[] args) {
        async();
        syn();
    }
}
