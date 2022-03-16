package future;

import java.util.concurrent.ForkJoinPool;

public class GetCommonPoolSize {
    public static void main(String[] args) {

        // Returns the number of processors available to the Java virtual machine
        System.out.println(Runtime.getRuntime().availableProcessors());
        // 查看 当前线程数
        System.out.println(ForkJoinPool.commonPool().getPoolSize());
        // 查看 最大线程数
        System.out.println(ForkJoinPool.getCommonPoolParallelism());

    }
}
