package future.completableFuture;

import future.JucTool;

import java.util.concurrent.CompletableFuture;

public class ThenCombine {
    public static void main(String[] args) {
        JucTool.printTimeAndThread("进入餐厅");
        JucTool.printTimeAndThread("点了 番茄炒蛋 + 一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("厨师炒菜");
            JucTool.sleepMillis(200);
            return "番茄炒蛋";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("服务员蒸饭");
            JucTool.sleepMillis(300);
            return "米饭";
        }), (dish, rice) -> {
            JucTool.printTimeAndThread("服务员打饭");
            JucTool.sleepMillis(100);
            return String.format("%s + %s 好了", dish, rice);
        });

        JucTool.printTimeAndThread("打一把游戏");
        JucTool.printTimeAndThread(String.format("%s ,开吃", cf1.join()));

    }


    /**
     * 用 applyAsync 也能实现
     */
    private static void applyAsync() {
        JucTool.printTimeAndThread("进入餐厅");
        JucTool.printTimeAndThread("点了 番茄炒蛋 + 一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("厨师炒菜");
            JucTool.sleepMillis(200);
            return "番茄炒蛋";
        });
        CompletableFuture<String> race = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("服务员蒸饭");
            JucTool.sleepMillis(300);
            return "米饭";
        });
        JucTool.printTimeAndThread("打一把游戏");

        String result = String.format("%s + %s 好了", cf1.join(), race.join());
        JucTool.printTimeAndThread("服务员打饭");
        JucTool.sleepMillis(100);

        JucTool.printTimeAndThread(String.format("%s ,开吃", result));
    }
}
