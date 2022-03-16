package future.completableFuture;

import future.JucTool;

import java.util.Random;

public class TwoCarCrossBridge {
    public static void main(String[] args) {

        Thread carTwo = new Thread(() -> {
            JucTool.printTimeAndThread("卡丁2号 准备过桥");
            JucTool.printTimeAndThread("发现1号在过，开始等待");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                JucTool.printTimeAndThread("卡丁2号 开始过桥");
            }
            JucTool.printTimeAndThread("卡丁2号 过桥完毕");
        });


        Thread carOne = new Thread(() -> {
            JucTool.printTimeAndThread("卡丁1号 开始过桥");
            int timeSpend = new Random().nextInt(500) + 1000;
            JucTool.sleepMillis(timeSpend);
            JucTool.printTimeAndThread("卡丁1号 过桥完毕 耗时:" + timeSpend);
//            SmallTool.printTimeAndThread("卡丁2号的状态" + carTwo.getState());
            carTwo.interrupt();
        });

        carOne.start();
        carTwo.start();

    }
}
