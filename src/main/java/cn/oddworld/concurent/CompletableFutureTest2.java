package cn.oddworld.concurent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest2 {

    /**
     * supplyAsync 带返回结果的异步任务
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName()+" 等公交。。。");
        CompletableFuture<String> async = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread()+" 947a来了。。。");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "947a来了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread()+" 947来了。。。");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "947来了";
        }), f -> {
            if(f.equals("947a来了")){
                throw new RuntimeException("公交947a出异常了");
            }
            return f;
        }).exceptionally(e -> {

            System.out.println(e.getCause());
            System.out.println("叫出租车");
            return "出租车到了";
        });

        String rsp = async.join();
        System.out.println(Thread.currentThread().getName()+" "+rsp+"搭公交回家");
    }
}
