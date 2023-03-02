package cn.oddworld.concurent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletableFutureTest1 {

    /**
     * supplyAsync 带返回结果的异步任务
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName()+" 煮水。。。");

        CompletableFuture<String> async = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread()+" 洗茶杯。。。");
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "干净的茶杯";
        }).thenApplyAsync(rsp -> {
            try {
                System.out.println(Thread.currentThread()+" 洗茶壶。。。");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return rsp+"和干净的茶壶";
        });

        String rsp = async.join();
        System.out.println(Thread.currentThread().getName()+" 使用"+rsp+"和煮好的水泡茶");
    }
}
