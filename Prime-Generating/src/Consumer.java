import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Queue;

public class Consumer extends Thread {
    String fName;
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
                    //System.out.println("Max: "+producer.getMax());
                    //System.out.println("cnt: "+producer.getCnt());
                    break;
                }
                Queue<Integer> q1 = producer.getQ();
                while (q1.size() > 0) {
                    out.write(q1.peek().toString() + " ");
                    q1.remove();
                }
                obj.notify();
                obj.wait();
            }
        }
        out.close();
    }
}
