import javax.swing.*;
import java.util.Random;

///testing to see if I can get random variables between x and y size. Will be useful for putting players in random positions on the grid.
public class DummyTest_RandomizePositions
{
    private int xPosition, yPosition;

    private DummyTest_RandomizePositions(int x, int y)
    {
        this.xPosition = x;
        this.yPosition = y;
        printDetails();
    }



    private void printDetails()
    {
        System.out.println("your random x "+xPosition+"\nyour random y "+yPosition);
    }

    public static void main(String[] args) {
        Random rand = new Random();
        String wow;
        Integer players = Integer.parseInt(wow = JOptionPane.showInputDialog("Enter number of players:"));
        for (int i = 0; i < players; i++)
        {
            int x = rand.nextInt(100) + 10;
            int y = rand.nextInt(100) + 10;
            System.out.println("Player "+(i)+":");
            DummyTest_RandomizePositions testRand = new DummyTest_RandomizePositions(x, y);
        }
    }

}
