package future.completableFuture;

import future.JucTool;

import java.util.concurrent.CompletableFuture;

public class ApplyAcceptRun {
    public static void main(String[] args) {
        thenApply();
        thenApply();
        thenRun();
    }


    private static void thenApply() {
        JucTool.printTimeAndThread("来到饭店吃饭");

        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() -> {
            int i = 100;
            JucTool.printTimeAndThread("点了一些菜，用了" + i + "元");
            JucTool.sleepMillis(100);
            return i;
        }).thenApply(money -> {
            JucTool.printTimeAndThread(String.format("吃完饭，给老板%s元", money));
            JucTool.sleepMillis(200);
            return String.format("拿到%s元发票", money);
        });

        JucTool.printTimeAndThread(String.format("拿到%s，准备回家", invoice.join()));
    }

    private static void thenAccept() {
        JucTool.printTimeAndThread("来到饭店吃饭");

        CompletableFuture<Void> invoice = CompletableFuture.supplyAsync(() -> {
            int i = 100;
            JucTool.printTimeAndThread("点了一些菜，用了" + i + "元");
            JucTool.sleepMillis(100);
            return i;
        }).thenAccept(money -> {
            JucTool.printTimeAndThread(String.format("吃完饭，给老板%s元", money));
            JucTool.sleepMillis(200);
        });

        JucTool.printTimeAndThread("没有发票，准备回家" + invoice.join());
    }

    private static void thenRun() {
        JucTool.printTimeAndThread("来到饭店吃饭");

        CompletableFuture<Void> invoice = CompletableFuture.supplyAsync(() -> {
            int i = 100;
            JucTool.printTimeAndThread("点了一些菜，用了" + i + "元");
            JucTool.sleepMillis(100);
            return i;
        }).thenRun(() -> {
            JucTool.printTimeAndThread("吃完饭，不知道给多少钱，老板扫我");
            JucTool.sleepMillis(200);
        });

        JucTool.printTimeAndThread("给完钱，准备回家" + invoice.join());
    }
}
