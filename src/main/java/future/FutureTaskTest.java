package future;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author xuchaozhen
 * @since 2022/3/6 15:45
 */
public class FutureTaskTest {
    public static void main(String[] args) {


        Task2();


    }

    public static void Task1() {
        FutureTask futureTask = new FutureTask(() -> {
            System.out.println("任务1");
            Thread.sleep(1000);
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

    public static void Task2() {

        List<FutureTask> futureTasks = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new FutureTask(() -> {
                    System.out.println("开启任务" + i);
                    Thread.sleep(1000);
                    return "任务" + i + "完成";
                })).collect(Collectors.toList());

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        futureTasks.forEach(executorService::submit);

        futureTasks.forEach(ft -> {
            try {
                System.out.println(ft.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}
