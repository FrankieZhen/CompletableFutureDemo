package future.completableFuture;

import future.JucTool;

import java.util.concurrent.CompletableFuture;

public class Either {
    public static void main(String[] args) {
        applyToEither();
//        acceptEither();
    }

    private static void applyToEither() {
        JucTool.printTimeAndThread("出门坐公交公交站");
        JucTool.printTimeAndThread("等待 1路 或者 2路 公交到来");

        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("1路公交正在赶来");
            JucTool.sleepMillis(300);
            return "1路公交到了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("2路公交正在赶来");
            JucTool.sleepMillis(200);
            return "2路公交到了";
        }), firstComeBus -> firstComeBus);

        JucTool.printTimeAndThread(String.format("%s,上车", bus.join()));
    }

    private static void acceptEither() {
        JucTool.printTimeAndThread("出门坐公交公交站");
        JucTool.printTimeAndThread("等待 1路 或者 2路 公交到来");

        CompletableFuture<Void> bus = CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("1路公交正在赶来");
            JucTool.sleepMillis(300);
            return "1路公交到了";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            JucTool.printTimeAndThread("2路公交正在赶来");
            JucTool.sleepMillis(200);
            return "2路公交到了";
        }), firstComeBus -> {
            JucTool.printTimeAndThread("看不清车牌了，直接上车");
        });

        bus.join();
    }

}
