package com.spring.metrics.queue;

import com.spring.metrics.model.Student;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.*;

@Slf4j
public class BlockingQueueTest {
    public static void main(String[] args) {
        take();
    }

    private static void add() {
        BlockingQueue<Student> blockingQueue = new ArrayBlockingQueue<>(1);
        blockingQueue.add(new Student());
        log.info("blocking queue size is {}", blockingQueue.size());
        blockingQueue.add(new Student());
        log.info("total queue size is {}", blockingQueue.size());
    }

    private static void offer() {
        BlockingQueue<Student> blockingQueue = new ArrayBlockingQueue<>(1);
        blockingQueue.offer(new Student());
        log.info("blocking queue size is {}", blockingQueue.size());
        boolean success = blockingQueue.offer(new Student());
        log.info("add student {}", success);
        log.info("total queue size is {}", blockingQueue.size());
    }

    private static void put() {
        BlockingQueue<Student> blockingQueue = new ArrayBlockingQueue<>(1);
        try {
            blockingQueue.put(new Student());
            log.info("put element to blocking queue");
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.schedule(() -> {
                try {
                    blockingQueue.take();
                    log.info("take element from queue");
                } catch (InterruptedException e) {
                    log.error("thread interrupted", e);
                }
            }, 5, TimeUnit.SECONDS);
            blockingQueue.put(new Student());
            log.info("after put two element, total size is {}", blockingQueue.size());
        } catch (InterruptedException e) {
            log.error("thread interrupted", e);
        }
    }

    private static void remove() {
        BlockingQueue<Student> blockingQueue = new ArrayBlockingQueue<>(1);
        try {
            blockingQueue.put(new Student().setId(1).setName("shark").setGender('M'));
            log.info("put 1 element into queue");
            Student student = blockingQueue.remove();
            log.info("the removed student is {}", student);
            log.info("the removed student is {}", blockingQueue.remove());
        } catch (InterruptedException e) {
            log.error("thread interrupted", e);
        }
    }

    private static void poll() {
        BlockingQueue<Student> blockingQueue = new ArrayBlockingQueue<>(1);
        try {
            blockingQueue.put(new Student().setId(1).setName("shark").setGender('M'));
            log.info("put 1 element into queue");
            Student student = blockingQueue.poll();
            log.info("the polled student is {}", student);
            log.info("the polled student is {}", blockingQueue.poll());
        } catch (InterruptedException e) {
            log.error("thread interrupted", e);
        }
    }

    private static void take() {
        BlockingQueue<Student> blockingQueue = new ArrayBlockingQueue<>(1);
        try {
            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.schedule(() -> {
                try {
                    blockingQueue.put(new Student().setId(1).setName("shark").setGender('M'));
                    log.info("put 1 element into queue");
                } catch (InterruptedException e) {
                    log.error("thread interrupted", e);
                }
            },5, TimeUnit.SECONDS);
            Student student = blockingQueue.take();
            log.info("the taken student is {}", student);
        } catch (InterruptedException e) {
            log.error("thread interrupted", e);
        }
    }
}
