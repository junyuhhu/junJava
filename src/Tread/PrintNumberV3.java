package Tread;

import java.util.concurrent.locks.LockSupport;

public class PrintNumberV3 {


        static volatile int cnt = 0;

    public static void main(String[] args) {
        Thread b = null;

         new Thread(()->{
             while (cnt<=100)
                 if(cnt%2==0) {
                     System.out.println(cnt);
                     cnt++;
                 }
        }).start();


        new Thread(()->{
            while (cnt<=100)
                if(cnt%2==1) {
                    System.out.println(cnt);
                    cnt++;
                }
        }).start();
    }

}




