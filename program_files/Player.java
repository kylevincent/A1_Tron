import java.util.Random;

public class Player
{
    private int x, y, score, colour;
    private String playerName;
    private boolean trailStatus;


    Player(int x, int y, int score, int colour, String playerName, boolean trailStatus)
    {
        this.x = x;
        this.y = y;
        this.score = score;
        this.colour = colour;
        this.playerName = playerName;
        this.trailStatus = trailStatus;
        setStartingPos();
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getScore()
    {
        return score;
    }

    public int getColour()
    {
        return colour;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public Boolean getTrailStatus()
    {
        return trailStatus;
    }

    public void setX(int a)
    {
        this.x = a;
    }

    public void setY(int c)
    {
        this.y = c;
    }

    public void setScore(int b)
    {
        this.score = b;
    }

    public void setColour(int d)
    {
        this.colour = d;
    }

    public void setPlayerName(String e)
    {
        this.playerName = e;
    }

    public void setTrailStatus(Boolean f)
    {
        this.trailStatus = f;
    }


    //this needs to be out in the main or something, where they first create the player
    public void setStartingPos()
    {
        Random rand = new Random();
        x = rand.nextInt(650) + 150;
        y = rand.nextInt(650) + 150;
        System.out.println("random x = "+x+"\nrandom y = "+y);
    }
    public static void main(String[]args)
    {
        new Player(2,2,2,2,"ye",true);
    }

}
