package com.relearn.practice.Practice;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class LearnExecutor {

    static ExecutorService executorService = null;

    @SneakyThrows
    public void learnThreads(){
        Future<?> future = executorService.submit(() -> {
            System.out.println("mee");
        });

        Future<?> future2 = executorService.submit(()->
        {});

        Future<Double> future1 = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return null;
            }
        });


        future1.get(100000, TimeUnit.MILLISECONDS);

        /*

        COULD HAVE DONE THIS FOR LOOP FOR BARTENDERS AND RETURN THE STATUS OF THE VALUES
        USING CALLABLE

        As well as allowing you to submit of a single Callable, the ExecutorService allows
         you to submit a Collection of Callable using the invokeAll method. As you might
         expect, instead of returning a single Future, a Collection of Futures is returned.
        A Future is returned representing the pending result of each submitted task.
         */



        /*
        can add collection of callable and returns a list of future tasks
        list of future events are returned in the same order as the callables were submitted
         */

        /*
        Note that submitting multiple Callable s will require the size of the thread pool to be tweaked if
        we want most or all of the submitted tasks can be executed in parallel. In the example below, we'd
        need a thread pool with 8 threads to run all tasks in parallel.
         */

        Collection<Callable<Double>> callables = new ArrayList<>();
        IntStream.rangeClosed(1, 8).forEach(i-> {
            callables.add(createCallable());
        });

        /* invoke all supplied Callables */
        List<Future<Double>> taskFutureList = executorService.invokeAll(callables);

        /* call get on Futures to retrieve result when it becomes available.
         * If specified period elapses before result is returned a TimeoutException
         * is thrown
         */
        for (Future<Double> futuretask : taskFutureList) {

            /* get Double result from Future when it becomes available */
            Double value = futuretask.get(4, TimeUnit.SECONDS);
            System.out.println(String.format("TaskFuture returned value %s", value));
        }






        /*
        After all the tasks have completed, its important to shut down the ExecutorService gracefully so that resources used can be
        reclaimed.
        There are two methods available, shutDown() and shutDownNow(). shutDown() triggers a shutdown of the ExecutorService,
         allowing currently processing tasks to finish but rejecting newly submitted tasks.

        shutDownNow() also triggers a shutdown of the ExecutorService, but does not allow currently executing tasks
         to complete and attempts to terminate them immediately. shutDownNow() returns a list of tasks that were queued
         for execution when the shutdown was initiated. To ensure the ExecutorService is shut down in all cases and to avoid potential
        resource leaks, it's important that shutDown() or shutDownNow() is called inside a finally block.
         */

        try{
            executorService = Executors.newFixedThreadPool(2);

            executorService.execute(()->{
                System.out.println(String.format("starting expensive task thread %s", Thread.currentThread().getName()));
               // doSomethingExpensive();
            });

        }
        finally{
            executorService.shutdown();
        }

    }

    private Callable<Double> createCallable() {

        return null;
    }


}
