package future.completableFuture;

import future.JucTool;

import java.util.concurrent.CompletableFuture;

public class Both {
    public static void main(String[] args) {
//        runAfterBothTask();
        thenAcceptBothTask();
    }

    public static void runAfterBothTask() {
        JucTool.printTimeAndThread("在公交站等人");
        JucTool.printTimeAndThread("等待 小明和小红");

        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            JucTool.printTimeAndThread("小明正在路上");
            JucTool.sleepMillis(500);
            JucTool.printTimeAndThread("小明到了");
        }).runAfterBoth(CompletableFuture.runAsync(() -> {
            JucTool.printTimeAndThread("小红正在路上");
            JucTool.sleepMillis(1000);
            JucTool.printTimeAndThread("小红到了");
        }), () -> {
            JucTool.printTimeAndThread("一起去玩");
        });

        cf.join();
    }

    public static void thenAcceptBothTask() {
        JucTool.printTimeAndThread("在公交站等人");
        JucTool.printTimeAndThread("等待 小明和小红");

        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("小明正在路上");
            JucTool.sleepMillis(300);
            int i = 2;
            JucTool.printTimeAndThread("小明到了，带来了" + i + "元钱");
            return i;
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("小红正在路上");
            JucTool.sleepMillis(1000);
            int i = 3;
            JucTool.printTimeAndThread("小红到了，带来了" + i + "元钱");
            return i;
        }), (r1, r2) -> {
            JucTool.printTimeAndThread("凑到钱" + (r1 + r2) + "元，叫出租车");
        });

        cf.join();
    }
}
