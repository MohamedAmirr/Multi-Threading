import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Queue;

public class Consumer extends ProducingAndConsuming {
    private String fName;
    private static int cnt = 0;
    private static long start = 0;
    public static GUI g;
    private static ProducingAndConsuming pc;


    Consumer(String name, GUI gui, long _start, ProducingAndConsuming pc1) {
        fName = name;
        g = gui;
        start = _start;
        pc = pc1;
    }

    public void run() throws Exception {
        FileWriter stream = new FileWriter(fName);
        BufferedWriter out = new BufferedWriter(stream);
        synchronized (obj) {
            while (!finished) {
                int max = 0;
                if (q.isEmpty()) {
                    obj.notify();
                    obj.wait();
                }
                while (q.size() > 0) {
                    out.write(q.peek().toString() + " ");
                    cnt++;
                    max = (int) q.remove();
                }
                g.l4.setText(String.valueOf(max));
                g.l5.setText(String.valueOf(cnt));
                long end = System.currentTimeMillis();
                g.l6.setText(end - start + " MS");
                obj.notify();
            }
        }
        out.close();
    }

}
