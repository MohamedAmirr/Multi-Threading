import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Queue;

public class Consumer extends Thread {
    private String fName;
    private static int cnt = 0;
    public static GUI g;


    Consumer(String name, GUI gui) {
        fName = name;
        g = gui;
    }

    public void run(Object obj) throws Exception {
        FileWriter stream = new FileWriter(fName);
        BufferedWriter out = new BufferedWriter(stream);
        synchronized (obj) {
            int max = 0;
            while (true) {
                Producer producer = new Producer();
                if (producer.getBool()) {
                    break;
                }
                Queue<Integer> q1 = producer.getQ();
                while (q1.size() > 0) {
                    out.write(q1.peek().toString() + " ");
                    cnt++;
                    max = q1.remove();
                }
                g.l4.setText(String.valueOf(max));
                g.l5.setText(String.valueOf(cnt));
                sleep(100);
                obj.notify();
                obj.wait();
            }
        }
        out.close();
    }

}
