import java.io.BufferedWriter;
import java.io.FileWriter;

public class Consumer extends Buffer {
    private final String fName;
    private static int cnt = 0;
    private static long start = 0;
    public static GUI g;
    private static Buffer pc;


    Consumer(String name, GUI gui, long _start, Buffer pc1) {       // constructor
        fName = name;
        g = gui;
        start = _start;
        pc = pc1;
    }

    public void run() throws Exception {
        FileWriter stream = new FileWriter(fName);                  // create file writer
        BufferedWriter out = new BufferedWriter(stream);            // create object to write on the file
        synchronized (obj) {
            while (!finished) {
                int max = 0;
                if (q.isEmpty()) {
                    if(!finished)
                        obj.notify();
                    obj.wait();
                }
                while (q.size() > 0) {
                    out.write('"'+ q.peek().toString() + '"' + ", ");
                    cnt++;
                    int u = q.remove();
                    if (u > 0)
                        max = u;
                }
                if (max > 0)
                    g.l4.setText(String.valueOf(max));              // updating the largest value
                g.l5.setText(String.valueOf(cnt));                  // updating number of prime generated
                long end = System.currentTimeMillis();              // capture end time
                g.l6.setText(end - start + " MS");                  // calculate and update time
                if(!finished)
                    obj.notify();
            }
        }
        out.close();                                                // close the file
    }
}
