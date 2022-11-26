public class Main {
    public static GUI g;

    void go() throws InterruptedException {
        long start = System.currentTimeMillis();
        int n = Integer.parseInt(g.Num.getText());
        int bfSz = Integer.parseInt(g.Size.getText());
        String fName = g.File.getText();
        Buffer pc = new Buffer();
        Producer r1 = new Producer(n, bfSz, pc);
        Consumer r2 = new Consumer(fName, g, start, pc);

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    r1.run();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread th2 = new Thread(new Runnable() {
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
        g = new GUI();
    }
}