package cn.oddworld.concurent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest3 {

    /**
     * supplyAsync 带返回结果的异步任务
     * @param args
     */
    public static void main(String[] args) {

        List<CompletableFuture<Void>> all = new ArrayList<>();
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10; i++){
            CompletableFuture<Void> async = CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            all.add(async);
        }

        // 等待所有任务完成。
        CompletableFuture.allOf(all.toArray(new CompletableFuture[all.size()])).join();
        //all.forEach(x -> x.join()); 一样的

        System.out.println("done...last time ="+(System.currentTimeMillis()-start) +"ms");
    }
}
