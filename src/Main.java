import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.lang.Integer.*;

/**
 * Created by User on 14.07.2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        new Main().testInvokeAny();
    }



    public void testSubmit() throws ExecutionException, InterruptedException {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> f = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "Task executed";
            }
        });
        System.out.println("Waiting for a result");
        System.out.println("result: " + f.get());
        f.get();

        executorService.shutdown();

    }
    public void testInvokeAny() throws InterruptedException, ExecutionException{
        List<Callable<String>> callables = new ArrayList<>();
        Random random=new Random();
        IntStream.range(0,7).forEach(i -> callables.add(() -> {
            Thread.sleep(random.nextInt(1000));
            return String.valueOf(i);
                }));
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        String result = executorService.invokeAny(callables);

        System.out.println(result);
        executorService.shutdown();

    }



        }






