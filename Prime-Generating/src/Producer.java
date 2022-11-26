import java.util.Vector;

public class Producer extends Buffer {
    private static int N, bfSz;
    private static Buffer pc;


    Producer(int n, int sz, Buffer pc1) {                               // constructor
        N = n;
        bfSz = sz;
        pc = pc1;
    }

    Producer() {                                                        // default constructor
    }

    Vector<Boolean> GeneratePrimes() {                                  // function to generate primes
        Vector<Boolean> Primes = new Vector<>();
        Primes.setSize(N + 2);
        for (int i = 0; i <= N; i++) {
            Primes.set(i, true);                                        // assuming all numbers is prime
        }
        for (int i = 2; i * i <= N; i++) {
            if (!Primes.get(i)) {                                       // if not prime number then continue
                continue;
            }
            for (int j = 2 * i; j <= N; j += i) {
                Primes.set(j, false);                                   // mark the multiples of number i as not prime
            }
        }
        return Primes;
    }

    public void run() throws Exception {
        Vector<Boolean> Primes = GeneratePrimes();
        synchronized (obj) {
            for (int i = 1; i <= N; i++) {
                if (Primes.get(i)) {
                    q.add(i);                                           // adding prime numbers to queue
                }
                if (q.size() == bfSz) {                                 // if the buffer is full
                    obj.notify();                                       // notify the consumer
                    obj.wait();                                         // waiting for empty space
                }
                if (q.size() > 0) {
                    obj.notify();                                       // notify consumer if queue is not empty
                    obj.wait();
                }
            }
            finished = true;
            obj.notify();
        }
    }
}