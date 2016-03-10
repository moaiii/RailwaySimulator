import java.util.Random;

public class TrainCreator {
	
    private final int NUM_TRAINS = Runme.NUM_TRAINS;	// number of train threads created, global variable
    private Railway r1;
    private final int slow = 250;		// speed of slow train
    private final int fast = 500;		// speed of fast train
	
    public TrainCreator (Railway r1) {
    	this.r1 = r1;	// railway network for new train to travel on 
    }
    
	public void create () {
		Random random = new Random();		// random numbers for creating delays
		
		for (int i = 1; i <= NUM_TRAINS; i++)	// loop to create trains 1 to n
	      {
			// initialise each trains parameters 
			// name, speed and starting lag (delay)
		    int speed = 0;
			int delay = random.nextInt(1000);
			if(delay>500) {
				speed = fast;		// if delay large then create fast train 
			} else {
				speed = slow;		// if delay small then create slow train 
			}
			String name = "t"+i;
			
			// create and start train on its own thread
			System.out.println("Train created - "+name+", speed: "+speed+" m/s");
			Train t = new Train(name, speed, r1);
	        Thread trainThread = new Thread(t);
	        trainThread.start();
	         
	        // execute delay defore creating next train
	         try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	      }
	}
}
