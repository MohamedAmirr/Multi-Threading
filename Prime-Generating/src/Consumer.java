import java.util.Queue;

public class Consumer extends Thread {

    public void run(Object obj) throws Exception{
        synchronized (obj) {
            while(true){
                Producer producer = new Producer();
                if(producer.getBool())
                    break;
                Queue<Integer> q1 = producer.getQ();
                while (q1.size() > 0) {
                    System.out.println(q1.peek());
                    q1.remove();
                }
                obj.notify();
                obj.wait();
            }
        }
    }
}
