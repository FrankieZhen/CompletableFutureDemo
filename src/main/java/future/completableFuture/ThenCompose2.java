package future.completableFuture;

import future.JucTool;

import java.util.concurrent.CompletableFuture;

public class ThenCompose2 {
    public static void main(String[] args) {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("厨师炒菜");
            JucTool.sleepMillis(200);
            return "番茄炒蛋";
        }).thenCompose(dish -> {
            JucTool.printTimeAndThread("服务员A 准备打饭，但是被老板叫走，打饭交接给服务员B");

            return CompletableFuture.supplyAsync(() -> {
                JucTool.printTimeAndThread("服务员B 打饭");
                JucTool.sleepMillis(100);
                return dish + " + 米饭";
            });
        });

        JucTool.printTimeAndThread(cf1.join()+"好了，开饭");
    }
}
