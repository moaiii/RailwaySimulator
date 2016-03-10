import java.util.LinkedList;
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition; 

public class Train implements Runnable {
	
	private String name;
	private int speed;
	private Railway r1;
	
	// 	Constructor
	public Train(String name, int speed, Railway r1) {
		this.name = name;
		this.speed = speed;
		this.r1 = r1;			// train is passed a railway network to travel on (i.e iterate array)
	}
	 
   public void run()
   {	   
      try
      {
    	  int size = r1.getSize();		//	rename for code readability
    	  
    	  for(int i = 0; i < size; i++) {		// train to loop through specified railway route
    		  Infrastructure position = r1.getNetwork()[i]; //	rename for code readability
    		  position.enter(this.name, this.speed);	// enter track and begin journey at network position
    		  
    		  // print full network line status (CONSOLE)
    		  String lineStatus = "";
    		  for(int j = 0; j < size; j++) {
    			  String foo = r1.getNetwork()[j].status();
    			  lineStatus += foo;
    		  }
    		  System.out.println(lineStatus);	// full track status prints every time a train moves from piece to piece
    		  
    		  //	print if erminated at the end of the line (CONSOLE)
    		  if(i==size-1) {
    			  System.out.println(name+" has terminated @ "+position.getName());
    		  }
    	  }
      }
      catch (InterruptedException exception) {}
   }
}  
