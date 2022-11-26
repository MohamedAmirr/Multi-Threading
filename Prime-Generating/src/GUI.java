import javax.swing.*;
import java.awt.*;
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
        Main m = new Main();
        try {
            m.go();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
//        button.setText("Exit");
//        button.addActionListener(e.dispose());
    }

}
