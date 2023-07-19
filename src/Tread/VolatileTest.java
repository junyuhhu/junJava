package Tread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest {
    static class  Task implements Runnable{
        private int number = 0;
        private ReentrantLock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        @Override
        public void run(){
            while(number<100){
                lock.lock();

                System.out.println(Thread.currentThread().getName()+"--"+number);
                number++;
                condition.signal();
                try{
                    if(number<100)
                        condition.await();
                    System.out.println(Thread.currentThread().getName());
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();

                }


            }

        }
    }
    public static void main(String[] args) throws InterruptedException {
        Task t = new Task();
        Thread t1 = new Thread(t,"A");
        Thread t2 = new Thread(t,"B");
        Thread t3 = new Thread(t,"C");

        t1.start();
        t2.start();
        t3.start();

    }
}







