import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    public JTextField Num;
    public JTextField Size;
    public JTextField File;
    private JPanel panel;
    public JLabel N;
    private JLabel Buffer;
    private JButton button;
    private JLabel Name;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    public JLabel l4;
    public JLabel l5;
    public JLabel l6;

    public GUI() {                                          // setting GUI content
        setContentPane(panel);
        setTitle("Prime Numbers Generator");
        setSize(550, 450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {            // when we press on button start produce
        Main m = new Main();                                // create new object from main
        try {
            m.go();                                         // calling go function in main
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
