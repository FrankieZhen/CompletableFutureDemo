package future.completableFuture;

import future.JucTool;

import java.util.concurrent.CompletableFuture;

public class ThenCompose {
    public static void main(String[] args) {
        thenCompose();
//        applyAsync();
//        thenApply();
    }

    private static void thenCompose() {
        JucTool.printTimeAndThread("进入餐厅");
        JucTool.printTimeAndThread("点了 番茄炒蛋 + 一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("厨师炒菜");
            JucTool.sleepMillis(200);
            return "番茄炒蛋";
        }).thenCompose(dish -> CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("服务员打饭");
            JucTool.sleepMillis(100);
            return dish + " + 米饭";
        }));

        JucTool.printTimeAndThread("打一把游戏");
        JucTool.printTimeAndThread(String.format("%s 好了,开吃", cf1.join()));
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
            CompletableFuture<String> race = CompletableFuture.supplyAsync(() -> {
                JucTool.printTimeAndThread("服务员打饭");
                JucTool.sleepMillis(100);
                return " + 米饭";
            });
            return "番茄炒蛋" + race.join();
        });

        JucTool.printTimeAndThread("打一把游戏");
        JucTool.printTimeAndThread(String.format("%s 好了,开吃", cf1.join()));
    }


    /**
     * thenApplyAsy
     */
    private static void thenApply() {
        JucTool.printTimeAndThread("进入餐厅");
        JucTool.printTimeAndThread("点了 番茄炒蛋 + 一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("厨师炒菜");
            JucTool.sleepMillis(200);
            return "番茄炒蛋";
        }).thenApplyAsync((dish) -> {
            JucTool.printTimeAndThread("服务员打饭");
            JucTool.sleepMillis(100);
            return dish + " + 米饭";
        });

        JucTool.printTimeAndThread("打一把游戏");
        JucTool.printTimeAndThread(String.format("%s 好了,开吃", cf1.join()));
    }
}
