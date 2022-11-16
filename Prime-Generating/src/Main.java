import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args)  {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int bfSz = in.nextInt();
        Object obj = new Object();
        Producer r1 = new Producer(n, bfSz);
        Consumer r2 = new Consumer();
        Thread th1 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    r1.run(obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread th2 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    r2.run(obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        th1.start();
        th2.start();
    }
}