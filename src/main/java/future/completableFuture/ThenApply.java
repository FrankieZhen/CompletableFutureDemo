package future.completableFuture;

import future.JucTool;

import java.util.concurrent.CompletableFuture;

public class ThenApply {
    public static void main(String[] args) {
        JucTool.printTimeAndThread("吃完饭");
        JucTool.printTimeAndThread("结账、要求开发票");

        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("服务员收款 500元");
            JucTool.sleepMillis(100);
            return "500";
        }).thenApplyAsync(money -> {
            JucTool.printTimeAndThread(String.format("服务员开发票 面额 %s元", money));
            JucTool.sleepMillis(200);
            return String.format("%s元发票", money);
        });

        JucTool.printTimeAndThread(String.format("拿到%s，准备回家", invoice.join()));
    }


    private static void one() {
        JucTool.printTimeAndThread("吃完饭");
        JucTool.printTimeAndThread("结账、要求开发票");

        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("服务员收款 500元");
            JucTool.sleepMillis(100);
            JucTool.printTimeAndThread("服务员开发票 面额 500元");
            JucTool.sleepMillis(200);
            return "500元发票";
        });


        JucTool.printTimeAndThread(String.format("拿到%s，准备回家", invoice.join()));
    }


    private static void two() {
        JucTool.printTimeAndThread("吃完饭");
        JucTool.printTimeAndThread("结账、要求开发票");

        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("服务员收款 500元");
            JucTool.sleepMillis(100);

            CompletableFuture<String> waiter2 = CompletableFuture.supplyAsync(() -> {
                JucTool.printTimeAndThread("服务员开发票 面额 500元");
                JucTool.sleepMillis(200);
                return "500元发票";
            });

            return waiter2.join();
        });


        JucTool.printTimeAndThread(String.format("拿到%s，准备回家", invoice.join()));
    }
}
