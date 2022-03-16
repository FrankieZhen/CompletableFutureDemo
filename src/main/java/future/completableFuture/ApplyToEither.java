package future.completableFuture;

import future.JucTool;

import java.util.concurrent.CompletableFuture;

public class ApplyToEither {
    public static void main(String[] args) {
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
}
