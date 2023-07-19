package Tread;

import java.util.LinkedList;
import java.util.Queue;

public class BoundedBlocking {
    static class BoundedBlockingQueue<T> {
        //limit: 队列最大长度，如果在达到最大长度时插入元素，则阻塞在push函数中，直到队列中有空位为止。
        //waitMs: 队列为空时最长等待时长，单位ms
        private Queue<T> queue;
        private int limit;
        private int waitMs;

        public BoundedBlockingQueue(int limit, int waitMs) {
            this.queue = new LinkedList<>();
            this.limit = limit;
            this.waitMs = waitMs;
        }
        //插入元素到队列尾部
        public synchronized void push(T obj) throws InterruptedException {
            while (queue.size() == limit) {
                wait();
            }
            queue.add(obj);
            notifyAll();
        }
        //从队列头部取出元素，如果没有元素等待timeoutMs后弹出null
        public synchronized T pop() throws InterruptedException {
            if (queue.isEmpty()) {
                wait(waitMs);
                if (queue.isEmpty())
                    return null;
            }
            T obj = queue.poll();
            notifyAll();
            return obj;
        }
    }
    public static void main(String[] args) {
        BoundedBlockingQueue<Integer> queue = new BoundedBlockingQueue<>(10, 1000);

        // 创建并启动十个线程，每个线程插入20个元素
        for (int i = 0; i < 10; i++) {
            int threadNumber = i + 1;
            new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    int element = threadNumber * 100 + j;  // 示例插入的元素为线程编号 * 100 + 元素编号
                    try {
                        queue.push(element);
                        System.out.println("Thread " + threadNumber + " pushed: " + element);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }).start();
        }

        // 创建并启动一个线程，取数并打印200个元素
        new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                try {
                    Integer element = queue.pop();
                    System.out.println("Popped: " + element);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }).start();
    }
}