import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class DummyTest_JMovingFrame extends JFrame implements ActionListener
{
    Container con = getContentPane();
    final int MAX = 8;
    JPanel[] panel = new JPanel[8];
    JButton button = new JButton("Press");
    JLabel[] blankLabel = new JLabel[MAX];
    int x = 0, y;
    public DummyTest_JMovingFrame()
    {
        con.setLayout(new GridLayout(3,3));
        setTitle("Moving Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        con.add(button);
        button.addActionListener(this);
        for(y = 0; y < MAX; ++y)
        {
            blankLabel[y] =  new JLabel("        ");
            panel[y] = new JPanel();
            con.add(panel[y]);
            panel[y].add(blankLabel[y]);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        blankLabel[x].setText("        ");
        ++x;
        if(x == MAX)
            x = 0;
        blankLabel[x].setText("I'm here");
    }

    public static void main(String[] args)
    {
        DummyTest_JMovingFrame mFrame = new DummyTest_JMovingFrame();
        mFrame.setSize(250, 300);
        mFrame.setVisible(true);
    }
}