package future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xuchaozhen
 * @since 2022/3/6 15:45
 */
public class FutureTest1 {
    public static void main(String[] args) {


        FutureTask futureTask = new FutureTask( () -> {
            JucTool.printTimeAndThread("任务1");
            return "完成";
        });

        new Thread(futureTask).start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
