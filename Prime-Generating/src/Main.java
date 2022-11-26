// Mohamed Amir Mohamed         20200429
// Nermin Mostafa Madbouly      20200598

public class Main {
    public static GUI g;

    void go() throws InterruptedException {
        long start = System.currentTimeMillis();                // capture start time
        int n = Integer.parseInt(g.Num.getText());              // get num input from GUI TextField
        int bfSz = Integer.parseInt(g.Size.getText());          // get size input from GUI TextField
        String fName = g.File.getText();                        // get file name input from GUI TextField

        Buffer pc = new Buffer();
        Producer r1 = new Producer(n, bfSz, pc);
        Consumer r2 = new Consumer(fName, g, start, pc);

        Thread th1 = new Thread(new Runnable() {                // first thread to run producer
            @Override
            public void run() {
                try {
                    r1.run();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread th2 = new Thread(new Runnable() {                // second thread to run consumer
            @Override
            public void run() {
                try {
                    r2.run();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        th1.start();
        th2.start();
    }
    public static void main(String[] args) {
        g = new GUI();                                          // creating object from GUI
    }
}