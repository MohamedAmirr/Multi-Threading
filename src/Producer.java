package src;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Producer {
    private static Queue<Integer> q = new LinkedList<>();
    private static int N, bfSz;
    private static boolean finished = false;


    Producer(int n, int sz) {
        N = n;
        bfSz = sz;
    }

    Producer() {
    }


    public void run(Object obj) throws Exception {
        synchronized (obj) {
            boolean firstTime = true;
            for (int i = 1; i <= N; i++) {
                boolean prime = true;
                for (int j = 2; j * j <= i; j++) {
                    if (i % j == 0) {
                        prime = false;
                        break;
                    }
                }
                if (prime) {
                    q.add(i);
                }
                if (q.size() == bfSz || i == N) {
                    if (!firstTime)
                        obj.notify();
                    firstTime = false;
                    obj.wait();
                }
            }
            obj.notify();
            finished = true;
        }
    }

    boolean getBool() {
        return finished;
    }

    Queue<Integer> getQ() {
        return q;
    }
}
