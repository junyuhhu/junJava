package Tread;

public class PrintNumberV2  {
    public static class PrintNumber implements Runnable {
        private static int cnt = 0;
        private int id;

        public PrintNumber(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (cnt < 100) {
                synchronized (this) {
                    cnt++;
                    System.out.println("Thread"+Thread.currentThread().getName() + " num:" + cnt);
                    this.notify();
                    try {
                        if (cnt < 100)
                            this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
    public static void main(String[] args) {
        PrintNumber thread0 = new PrintNumber(0);
//        PrintNumber thread1 = new PrintNumber(1);
        new Thread(thread0,"1").start();
        new Thread(thread0,"2").start();
    }
}




