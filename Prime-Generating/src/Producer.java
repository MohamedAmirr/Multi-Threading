import java.util.Vector;

import static java.lang.Thread.sleep;

public class Producer extends Buffer {
    private static int N, bfSz;
    private static Buffer pc;


    Producer(int n, int sz, Buffer pc1) {
        N = n;
        bfSz = sz;
        pc = pc1;
    }

    Producer() {
    }

    Vector<Boolean> GeneratePrimes() {
        Vector<Boolean> Primes = new Vector<>();
        Primes.setSize(N + 2);
        for (int i = 0; i <= N; i++) {
            Primes.set(i, true);
        }
        for (int i = 2; i * i <= N; i++) {
            if (!Primes.get(i)) {
                continue;
            }
            for (int j = 2 * i; j <= N; j += i) {
                Primes.set(j, false);
            }
        }
        return Primes;
    }

    public void run() throws Exception {
        Vector<Boolean> Primes = GeneratePrimes();
        synchronized (obj) {
            for (int i = 2; i <= N; i++) {
                if (Primes.get(i)) {
                    q.add(i);
                }
                if (q.size() == bfSz) {
                    obj.notify();
                    obj.wait();
                }
                if (q.size() > 0) {
                    obj.notify();
                }
            }
            obj.notify();
            finished = true;
        }
    }
}