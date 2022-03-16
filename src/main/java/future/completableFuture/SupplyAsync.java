package future.completableFuture;


import future.JucTool;

import java.util.concurrent.CompletableFuture;

public class SupplyAsync {
    public static void main(String[] args) {
        JucTool.printTimeAndThread("进入餐厅");
        JucTool.printTimeAndThread("点了 番茄炒蛋 + 一碗米饭");

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("厨师炒菜");
            JucTool.sleepMillis(200);
            JucTool.printTimeAndThread("厨师打饭");
            JucTool.sleepMillis(100);
            return "番茄炒蛋 + 米饭 做好了";
        });

        JucTool.printTimeAndThread("打一把游戏");
        JucTool.printTimeAndThread(String.format("%s ,开始吃饭", cf1.join()));
    }
}
