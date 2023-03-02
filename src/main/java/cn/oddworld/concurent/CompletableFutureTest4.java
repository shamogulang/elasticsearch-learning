package cn.oddworld.concurent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletableFutureTest4 {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);

    /**
     * supplyAsync 带返回结果的异步任务
     * @param args
     */
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
                10, 0,
                TimeUnit.MINUTES, new LinkedBlockingQueue<>(120),
                new ThreadFactory(){ public Thread newThread(Runnable r) {
                    return new Thread(r, "futureTask_" + poolNumber.getAndIncrement());
                }}, new ThreadPoolExecutor.AbortPolicy());
        List<CompletableFuture<Void>> all = new ArrayList<>();
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10; i++){
            CompletableFuture<Void> async = CompletableFuture.runAsync(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+" doing");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, executor);
            all.add(async);
        }

        // 等待所有任务完成。
        CompletableFuture.allOf(all.toArray(new CompletableFuture[all.size()])).join();
        //all.forEach(x -> x.join()); 一样的

        System.out.println("done...last time ="+(System.currentTimeMillis()-start) +"ms");

        executor.shutdown();
    }
}
