package cn.oddworld.concurent;

import java.util.concurrent.TimeUnit;

public class ThreadTest {

    public static void main(String[] args) {

        Thread thread = new Thread(()->{
            int i = 0;
            while (i++ < 10){

                // boolean interrupted = Thread.currentThread().isInterrupted(); 只会查出中断标记，不会清楚
                // 会查出一开始的中断标记，然后会清除这个标记
                boolean interrupted = Thread.interrupted();
                if(interrupted){
                    System.out.println("中断了，向左走");
                }else {
                    System.out.println("向右边走");
                }
            }

        });
        thread.start();
        thread.interrupt();


    }
}
