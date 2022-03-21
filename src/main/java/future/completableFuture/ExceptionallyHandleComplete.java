package future.completableFuture;

import future.JucTool;

import java.util.concurrent.CompletableFuture;

public class ExceptionallyHandleComplete {
    public static void main(String[] args) {
//        exceptionallyTask();
//        handleTask();
        withCompleteTask();
    }

    public static void exceptionallyTask() {
        JucTool.printTimeAndThread("出门坐公交公交站");

        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("公交正在赶来");
            JucTool.sleepMillis(100);
//            int i = 1/0;
            return "公交到了";
        }).exceptionally(e -> {
            JucTool.printTimeAndThread(e.getMessage());
            JucTool.printTimeAndThread("叫出租车");
            return "出租车 叫到了";
        });

        JucTool.printTimeAndThread(String.format("%s,上车", bus.join()));
    }


    public static void handleTask() {
        JucTool.printTimeAndThread("出门坐公交公交站");

        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("公交正在赶来");
            JucTool.sleepMillis(100);
            int i = 1/0;
            return "公交到了";
        }).handle((result, e) -> {
            if (e == null) {
                JucTool.printTimeAndThread(result);
                JucTool.printTimeAndThread("等到了");
                return "坐公交";
            } else {
                JucTool.printTimeAndThread(e.getMessage());
                JucTool.printTimeAndThread("叫出租车");
                return "坐出租车";
            }
        });

        JucTool.printTimeAndThread(String.format("%s 回家", bus.join()));
    }

    public static void withCompleteTask() {
        JucTool.printTimeAndThread("出门坐公交公交站");

        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("公交正在赶来");
            JucTool.sleepMillis(100);
//            int i = 1/0;
            return "公交到了";
        }).whenComplete((result, e) -> {
            if (e == null) {
                JucTool.printTimeAndThread(result);
                JucTool.printTimeAndThread("等到了公交了，回家");
            } else {
                JucTool.printTimeAndThread(e.getMessage());
                JucTool.printTimeAndThread("叫出租车回家");
            }
        });

        bus.join();
    }
}
