package dev.struchkov.haiti.utils.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

public class ForkJoinUtils {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ForkJoinUtils.class);

    private ForkJoinUtils() {
        utilityClass();
    }

    public static <T> List<T> pullTaskResults(List<ForkJoinTask<List<T>>> tasks) {
        final List<T> results = new ArrayList<>();
        Iterator<ForkJoinTask<List<T>>> iterator = tasks.iterator();
        while (!tasks.isEmpty()) {
            while (iterator.hasNext()) {
                final ForkJoinTask<List<T>> task = iterator.next();
                if (task.isDone()) {
                    final List<T> jsons;
                    try {
                        jsons = task.get();
                        results.addAll(jsons);
                    } catch (InterruptedException | ExecutionException e) {
                        log.error(e.getMessage(), e);
                        Thread.currentThread().interrupt();
                    }
                    iterator.remove();
                }
            }
            iterator = tasks.iterator();
        }
        return results;
    }

    public static <T> List<T> pullTaskResult(List<ForkJoinTask<T>> tasks) {
        final List<T> results = new ArrayList<>();
        Iterator<ForkJoinTask<T>> iterator = tasks.iterator();
        while (!tasks.isEmpty()) {
            while (iterator.hasNext()) {
                final ForkJoinTask<T> task = iterator.next();
                if (task.isDone()) {
                    try {
                        results.add(task.get());
                    } catch (InterruptedException | ExecutionException e) {
                        log.error(e.getMessage(), e);
                        Thread.currentThread().interrupt();
                    }
                    iterator.remove();
                }
            }
            iterator = tasks.iterator();
        }
        return results;
    }

}
