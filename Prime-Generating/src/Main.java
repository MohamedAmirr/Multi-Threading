public class Main {
    public static GUI g;

    void go() {
        long start = System.currentTimeMillis();
        int n = Integer.parseInt(g.Num.getText());
        int bfSz = Integer.parseInt(g.Size.getText());
        String fName = g.File.getText();

        Object obj = new Object();
        Producer r1 = new Producer(n, bfSz);
        Consumer r2 = new Consumer(fName, g);

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    r1.run(obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread th2 = new Thread(new Runnable() {
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

        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        g.l6.setText(elapsedTime + " ms");
    }

    public static void main(String[] args) {
        g = new GUI();
    }
}