import java.text.DecimalFormat;


public class TimeFormat {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		// TODO Auto-generated method stub
	      long start = System.currentTimeMillis();
	      
	      Thread.sleep(11193);
	      
	      long end = System.currentTimeMillis();
	      
	      double timeTaken = new Double(end - start).doubleValue();
	      DecimalFormat df = new DecimalFormat("#.##");
	      
	      System.out.println("Output = " + df.format(timeTaken/1000));
	}

}
