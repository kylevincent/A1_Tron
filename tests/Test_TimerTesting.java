public class Test_TimerTesting extends Thread
{

    public static void main (String[] args) throws Exception
    {
        long startTime = System.nanoTime();
        Thread.sleep(5000);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

        System.out.println(duration);
    }
}

