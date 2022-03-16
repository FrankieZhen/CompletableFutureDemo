package future.completableFuture;

import future.Dish;
import future.JucTool;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class UseThreadPool {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newCachedThreadPool();
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);

        UseStream(threadPool);

        System.out.println("==============");
        UseFor(threadPool2);

        threadPool.shutdown();
        threadPool2.shutdown();
    }

    public static void UseStream(ExecutorService threadPool) {
        JucTool.printTimeAndThread("进餐厅点菜");
        long startTime = System.currentTimeMillis();

        CompletableFuture[] dishes = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new Dish("菜" + i, 1))
                .map(dish -> CompletableFuture.runAsync(dish::makeUseCPU, threadPool))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(dishes).join();

        JucTool.printTimeAndThread("菜都做好了，上桌 " + (System.currentTimeMillis() - startTime));
    }

    public static void UseFor(ExecutorService threadPool) {
        JucTool.printTimeAndThread("进餐厅点菜");
        long startTime = System.currentTimeMillis();
        // 点菜
        ArrayList<Dish> dishes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Dish dish = new Dish("菜" + i, 1);
            dishes.add(dish);
        }
        // 做菜
        ArrayList<CompletableFuture> cfList = new ArrayList<>();
        for (Dish dish : dishes) {
            CompletableFuture<Void> cf = CompletableFuture.runAsync(dish::makeUseCPU,threadPool);
            cfList.add(cf);
        }
        // 等待所有任务执行完毕
        CompletableFuture.allOf(cfList.toArray(new CompletableFuture[cfList.size()])).join();

        JucTool.printTimeAndThread("菜都做好了，上桌 " + (System.currentTimeMillis() - startTime));
    }
}
