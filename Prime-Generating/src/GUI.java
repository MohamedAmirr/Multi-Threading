import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private JTextField Num;
    private JTextField Size;
    private JTextField File;
    private JPanel panel;
    private JLabel N;
    private JLabel Buffer;
    private JButton button;
    private JLabel Name;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel l5;
    private JLabel l6;


    public GUI() {
        setContentPane(panel);
        setTitle("Prime");
        setSize(550, 450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long start = System.currentTimeMillis();
        int n = Integer.parseInt(Num.getText());
        int bfSz = Integer.parseInt(Size.getText());
        String fName = File.getText();

        Object obj = new Object();
        Producer r1 = new Producer(n, bfSz);
        Consumer r2 = new Consumer(fName);

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

        l4.setText(String.valueOf(r1.getMax()));
        l5.setText(String.valueOf(r1.getCnt()));

        long end = System.currentTimeMillis();
        long elapsedTime = end - start;

        l6.setText(elapsedTime + " ms");
    }

    public static void main(String[] args) {
        new GUI();
    }
}
