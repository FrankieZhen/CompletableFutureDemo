package future.completableFuture;

import future.JucTool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThenApply {
    public static void main(String[] args) {
        simple();
//        thenApply();
//        thenApply2();
//        thenApplyAsync();
//        supplyAsync();
    }

    private static void simple() {
        JucTool.printTimeAndThread("来到饭店吃饭");

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            int i = 100;
            JucTool.printTimeAndThread("点了一些菜，用了" + i + "元");
            JucTool.sleepMillis(100);
            JucTool.printTimeAndThread(String.format("吃完饭，给老板%s元", i));
            JucTool.sleepMillis(200);
            return String.format("拿到%s元发票", i);
        });

        JucTool.printTimeAndThread(String.format("拿到%s，准备回家", cf.join()));
    }

    private static void thenApply() {
        JucTool.printTimeAndThread("来到饭店吃饭");

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            int i = 100;
            JucTool.printTimeAndThread("点了一些菜，用了" + i + "元");
            JucTool.sleepMillis(100);
            return i;
        });

        CompletableFuture<String> cf2 = cf1.thenApply(money -> {
            JucTool.printTimeAndThread(String.format("吃完饭，给老板%s元", money));
            JucTool.sleepMillis(200);
            return String.format("拿到%s元发票", money);
        });

        JucTool.printTimeAndThread(String.format("拿到%s，准备回家", cf2.join()));
    }

    /**
     * 链式写法
     */
    private static void thenApply2() {
        JucTool.printTimeAndThread("来到饭店吃饭");

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            int i = 100;
            JucTool.printTimeAndThread("点了一些菜，用了" + i + "元");
            JucTool.sleepMillis(100);
            return i;
        }).thenApply(money -> {
            JucTool.printTimeAndThread(String.format("吃完饭，给老板%s元", money));
            JucTool.sleepMillis(200);
            return String.format("拿到%s元发票", money);
        });

        JucTool.printTimeAndThread(String.format("拿到%s，准备回家", cf.join()));
    }

    private static void thenApplyAsync() {
        JucTool.printTimeAndThread("来到饭店吃饭");

        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            int i = 100;
            JucTool.printTimeAndThread("点了一些菜，用了" + i + "元");
            JucTool.sleepMillis(100);
            return i;
        }, executorService).thenApplyAsync(money -> {
            JucTool.printTimeAndThread(String.format("吃完饭，给老板%s元", money));
            JucTool.sleepMillis(200);
            return String.format("拿到%s元发票", money);
        }, executorService);

        JucTool.printTimeAndThread(String.format("拿到%s，准备回家", cf.join()));
        executorService.shutdown();
    }

    private static void supplyAsync() {
        JucTool.printTimeAndThread("来到饭店吃饭");

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            int i = 100;
            JucTool.printTimeAndThread("点了一些菜，用了" + i + "元");
            JucTool.sleepMillis(100);

            CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
                JucTool.printTimeAndThread(String.format("吃完饭，给老板%s元", i));
                JucTool.sleepMillis(200);
                return i + "元发票";
            });

            return cf2.join();
        });

        JucTool.printTimeAndThread(String.format("拿到%s，准备回家", cf.join()));
    }
}
