package future.completableFuture;

import future.JucTool;

public class BeforehandInterrupt {
    public static void main(String[] args) {

        Thread.currentThread().interrupt();

        try {
            JucTool.printTimeAndThread("开始睡眠");
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            JucTool.printTimeAndThread("发生中断");
        }

        JucTool.printTimeAndThread("结束睡眠");

    }
}
