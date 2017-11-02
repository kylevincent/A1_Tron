import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameScreen extends JPanel implements Runnable
{
    public static final int Width = 800, Height = 800;
    private Thread thread;
    private boolean running = false;

    private final double UPDATE_CAP = 1.0/20.0;

    private ObjectPiece b;
    private ArrayList<ObjectPiece> bikesBody;

//    TODO: private int numberOfPlayers; (total number of players connectected
//    to the server, loops through drawing method)
    private int xPos = 10, yPos = 40;
    private int size = 1;
    private boolean up, down, left, right;

    private MulticastClient socketClient;
    private MulticastServer socketServer;

    private int ticks = 0;

    private userKey userKey;

    public GameScreen()
    {
        this.right = true;
        this.left = false;
        this.up = false;
        this.down = false;
        setFocusable(true);
        userKey = new userKey();
        addKeyListener(userKey);

        setPreferredSize(new Dimension(Width, Height));

        bikesBody = new ArrayList<ObjectPiece>();

        start();
    }

    public boolean getUp()
    {
        return this.up;
    }


    public void tick()
    {
        if(bikesBody.size() == 0)
        {
            b = new ObjectPiece(xPos, yPos, 10);
            bikesBody.add(b);
        }

        /// stops the game if the bike collides with itself
        for(int i = 0; i < bikesBody.size(); i++)
        {
            if(xPos == bikesBody.get(i).getxPos() && yPos == bikesBody.get(i).getyPos())
            {
                if(i != bikesBody.size() - 1)
                {
                    stop();
                }
            }
        }

        /// stops the game if the bike goes out of bounds
        if(xPos < 1 || xPos > 78 || yPos < 1 || yPos > 78)
        {
            stop();
        }

        ticks++;

        if(ticks > 1)
        {
            if(right) xPos += 2;
            if(left) xPos -= 2;
            if(up) yPos -= 2;
            if(down) yPos += 2;

            ticks = 0;

            b = new ObjectPiece(xPos, yPos, 10);
            bikesBody.add(b);

            if(bikesBody.size() > size)
            {
                bikesBody.remove(0);
            }
        }
    }

    public void paint(Graphics g)
    {
        g.clearRect(0, 0, Width, Height);

        ///draws a number of lines over the screen, for testing purposes
        g.setColor(Color.BLACK);
        for(int i = 0; i < Width / 10; i++)
        {
            g.drawLine(i * 10, 0, i * 10, Height);
        }
        for(int i = 0; i < Height / 10; i ++)
        {
            g.drawLine(0, i * 10, Width, i * 10);
        }

        for(int i = 0; i < bikesBody.size(); i ++)
        {
            bikesBody.get(i).draw(g);
        }

        socketClient.sendData("ping".getBytes());
    }

    public void start()
    {
        ///Asks the user if they want to host the server
        if(JOptionPane.showConfirmDialog(this, "Do you want to run the server?")==0)
        {
            socketServer = new MulticastServer();
            socketServer.start();
        }
        socketClient = new MulticastClient( "localHost");
        socketClient.start();

        running = true;
        thread = new Thread(this, "Game boop");
        thread.start();



    }

    public void stop()
    {
        running = false;
        try
        {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    ///game loop
    public void run()
    {
        boolean render = false;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;


        while(running)
        {
            render = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;



            while(unprocessedTime >= UPDATE_CAP)
            {
                unprocessedTime -= UPDATE_CAP;
                render = true;
                if(frameTime >= 1.0)
                {
                    frameTime = 0;
                    ///fps = frames;
                    frames = 0;
                    ///System.out.println(fps);
                }
                tick();

            }

            if(render)
            {
                repaint();
                frames ++;
            }
            else
                {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        }
    }

    ///Gets User KeyBoard Input
    private class userKey implements KeyListener
    {
        public void keyTyped(KeyEvent e)
        {
        }

        public void keyPressed(KeyEvent e)
        {
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_RIGHT && !left)
            {
                up = false;
                down = false;
                left = false;
                right = true;
            }
            if(key== KeyEvent.VK_LEFT && !right)
            {
                down = false;
                up = false;
                left = true;
                right = false;
            }

            if(key == KeyEvent.VK_UP && !down)
            {
                up = true;
                down = false;
                left = false;
                right = false;
            }

            if(key == KeyEvent.VK_DOWN && !up)
            {
                up = false;
                down = true;
                left = false;
                right = false;

            }


        }

        public void keyReleased(KeyEvent e)
        {
        }
    }

}
