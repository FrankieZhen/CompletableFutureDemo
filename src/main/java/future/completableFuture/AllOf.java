package future.completableFuture;

import future.JucTool;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AllOf {
    public static void main(String[] args) {
        allOf();
//        forEachFutureTask();
    }

    public static void allOf() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        JucTool.printTimeAndThread("进餐厅点菜");
        long startTime = System.currentTimeMillis();

        CompletableFuture[] cfs = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> CompletableFuture.supplyAsync(() -> {
                    JucTool.printTimeAndThread("正在做第" + i + "道菜");
                    JucTool.sleepMillis(1000);
                    return "第" + i + "道菜做完了";
                }, threadPool))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(cfs).join();
        JucTool.printTimeAndThread("菜都做好了，上桌 " + (System.currentTimeMillis() - startTime));
        threadPool.shutdown();
    }

    public static void forEachFutureTask() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        JucTool.printTimeAndThread("进餐厅点菜");
        long startTime = System.currentTimeMillis();
        List<FutureTask> futureTasks = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new FutureTask(() -> {
                    JucTool.printTimeAndThread("正在做第" + i + "道菜");
                    JucTool.sleepMillis(1000);
                    return "第" + i + "道菜做完了";
                })).collect(Collectors.toList());

        futureTasks.forEach(executorService::submit);

        futureTasks.forEach(ft -> {
            try {
                System.out.println(ft.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        JucTool.printTimeAndThread("菜都做好了，上桌 " + (System.currentTimeMillis() - startTime));
        executorService.shutdown();
    }
}
