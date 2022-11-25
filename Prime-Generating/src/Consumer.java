import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Queue;

public class Consumer extends Thread {
    private String fName;
    private int max , cnt = 0;
    GUI g = new GUI();

    Consumer(String name)
    {
        fName = name;
    }

    public void run(Object obj) throws Exception{
        FileWriter stream = new FileWriter(fName);
        BufferedWriter out = new BufferedWriter(stream);
        synchronized (obj) {
            while(true){
                Producer producer = new Producer();
                if(producer.getBool()) {
                    break;
                }
                Queue<Integer> q1 = producer.getQ();
                while (q1.size() > 0) {
                    out.write(q1.peek().toString() + " ");
                    cnt++;

                    g.setCountOfLabel(cnt);
                    g.setMaxOfLabel(q1.element());

                    q1.remove();
                }
                obj.notify();
                obj.wait();
            }
        }
        out.close();
    }
    public int getCnt(){
        return cnt;
    }
}
