package future.completableFuture;

import future.JucTool;

import java.util.concurrent.CompletableFuture;

public class AnyOf {
    public static void main(String[] args) {
        anyOf();
    }

    public static void anyOf() {
        JucTool.printTimeAndThread("等车");
        long startTime = System.currentTimeMillis();

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("叫滴滴");
            JucTool.sleepMillis(500);
            return "滴滴专车到了";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("等1路公交车");
            JucTool.sleepMillis(1000);
            return "1路公交车到了";
        });

        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("等2路公交车");
            JucTool.sleepMillis(800);
            return "2路公交车到了";
        });

        CompletableFuture<Object> resultFuture = CompletableFuture.anyOf(cf1, cf2, cf3);
        JucTool.printTimeAndThread(resultFuture.join().toString() + (System.currentTimeMillis() - startTime));
    }
}
