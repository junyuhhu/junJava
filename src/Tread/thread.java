package Tread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class thread {
    static int number = 0;

    public static void main(String[] args) throws InterruptedException {

         ReentrantLock lock = new ReentrantLock();
         Condition c1 = lock.newCondition();
         Condition c2 = lock.newCondition();
         Condition c3 = lock.newCondition();

        new Thread(()->{
            while(number<100){
                lock.lock();

                System.out.println(Thread.currentThread().getName()+"--"+number);
                number++;
                c2.signal();
                try{
                    if(number<100) {
                        c1.await();
                        System.out.println(Thread.currentThread().getName());
                    }
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        },"AA").start();

        new Thread(()->{
            while(number<100){
                lock.lock();

                System.out.println(Thread.currentThread().getName()+"--"+number);
                number++;
                c3.signal();
                try{
                    if(number<100) {
                        c2.await();
                        System.out.println(Thread.currentThread().getName());
                    }
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();

                }
            }
        },"BB").start();

        new Thread(()->{
            while(number<100){
                lock.lock();

                System.out.println(Thread.currentThread().getName()+"--"+number);
                number++;
                c1.signal();
                try{
                    if(number<100) {
                        c3.await();
                        System.out.println(Thread.currentThread().getName());
                    }
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        },"CC").start();

        sleep(1000);
        lock.lock();
        c3.signal();
        System.out.println("!11");
        lock.unlock();

    }
}







