package future.completableFuture;

import future.JucTool;

import java.util.concurrent.TimeUnit;

public class QAndA {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            JucTool.printTimeAndThread("开始睡眠");
            forceSleep(3);
            JucTool.printTimeAndThread("结束睡眠");
        });
        thread.start();
        thread.interrupt();
    }

    public static void forceSleep(int second) {
        long startTime = System.currentTimeMillis();
        long sleepMills = TimeUnit.SECONDS.toMillis(second);

        while ((startTime + sleepMills) > System.currentTimeMillis()) {
            long sleepTime = startTime + sleepMills - System.currentTimeMillis();
            if (sleepTime <= 0) {
                break;
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
