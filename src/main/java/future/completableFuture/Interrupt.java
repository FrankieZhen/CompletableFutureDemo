package future.completableFuture;

import future.JucTool;

public class Interrupt {
    public static void main(String[] args) {
        Thread carOne = new Thread(() -> {
            long startMills = System.currentTimeMillis();
            while (System.currentTimeMillis() - startMills < 10) {
                if (Thread.currentThread().isInterrupted()) {
//                if (Thread.interrupted()) {
                    JucTool.printTimeAndThread("向左开1米");
                } else {
                    JucTool.printTimeAndThread("往前开1米");
                }
            }
        });

        carOne.start();

        JucTool.sleepMillis(2);
        carOne.interrupt();

    }
}
