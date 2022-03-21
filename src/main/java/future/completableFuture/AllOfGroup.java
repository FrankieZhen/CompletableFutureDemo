package future.completableFuture;

import java.util.concurrent.CompletableFuture;

public class AllOfGroup {
    public static void main(String[] args) {
        taskAfterAllOf();
    }

    public static void taskAfterAllOf() {
        CompletableFuture<Integer> task11 = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<Integer> task12 = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> task13 = CompletableFuture.supplyAsync(() -> 3);

        CompletableFuture<Void> group1 = CompletableFuture.allOf(task11, task12, task13);

        CompletableFuture<Integer> task21 = group1.thenApply(num ->
                task11.join() + task12.join() + task13.join()
        );

        CompletableFuture<Integer> task22 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> task23 = CompletableFuture.supplyAsync(() -> task11.join());

        final CompletableFuture<Void> group2 = CompletableFuture.allOf(task21, task22, task23);

        final CompletableFuture<Integer> task31 = group2.thenApply((num) -> task21.join() + task22.join() + task23.join());
        System.out.println(task31.join());

    }
}
