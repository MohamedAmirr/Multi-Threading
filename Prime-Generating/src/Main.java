import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main extends JFrame implements ActionListener {

    JTextField Num , Size, File;
    JLabel N , Buffer , Outfile ;
    JLabel l1,l2,l3;
    JLabel n1,n2,n3;
    JButton button;
    JPanel t1;

    public Main() {
        setTitle("Prime Number Calculations");

        Num = new JTextField(10);
        N = new JLabel("N");
        add(Num);
        add(N);

        Size = new JTextField(10);
        Buffer = new JLabel("Buffer Size");
        add(Size);
        add(Buffer);

        File = new JTextField(10);
        Outfile = new JLabel("Output File");
        add(File);
        add(Outfile);

        button = new JButton("Start Produce");
        add(button);
        button.addActionListener(this);

        t1 = new JPanel();
        add(t1);

        l1 = new JLabel("The largest prime number");
        n1 = new JLabel("999991");
        l2 = new JLabel("# of elements (prime number) generated");
        n2 = new JLabel("ay 7aga");
        l3 = new JLabel("Time elapsed since the start of processing");
        n3 = new JLabel("ay 7aga brdo");

        add(l1);
        add(n1);
        add(l2);
        add(n2);
        add(l3);
        add(n3);

        setLayout(new FlowLayout(FlowLayout.LEFT,200,15));

        setSize(400,300);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int n = Integer.parseInt(Num.getText());
        int bfSz = Integer.parseInt(Size.getText());
        String fName = File.getText();
        Object obj = new Object();
        Producer r1 = new Producer(n,bfSz);
        Consumer r2 = new Consumer(fName);
        Thread th1 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    r1.run(obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread th2 =new Thread(new Runnable() {
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

    }
    public static void main(String[] args) {
        new Main();
    }
}