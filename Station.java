import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Station extends Infrastructure {
	
	   private Lock trainLock;
	   private Condition con;

	// constructor 
	public Station (int length, String name, int capacity) {
		super(length, name, capacity);
		trainLock = new ReentrantLock();	
		con = trainLock.newCondition();
	}
	
	public void enter (String trainName, int trainSpeed) throws InterruptedException {		
		if(this.getTrainCount()+1 >= this.getCapacity()) {	// this train is the last before it becomes full, then got to lock
			try{
				//	lock 
				trainLock.lock();
				//System.out.println(trainName+" has (***)locked @ "+this.getName());
				journey(trainName, trainSpeed);	// go to journey logic. sleeping and updating registers
				con.signalAll();		// backlog of trains (above capacity) waiting for lock, reawaken
			} finally {
				// 	unlock
				//System.out.println(trainName+" has unlocked(###) @ "+this.getName());	
				trainLock.unlock();
			}
		} else {
			journey(trainName, trainSpeed);
		}		
	}
	
	public void journey(String trainName, int trainSpeed) throws InterruptedException{
		// train counter +1
		this.setTrainCount(this.getTrainCount()+1);
		this.updateStatus();
		this.registerAdd(trainName);
		
		// travelling ......
		int sleepTime = (this.getLength()/trainSpeed)+1000;
		Thread.sleep(sleepTime);

		// leaving section
		leave(trainName);
	}

	public void leave(String trainName) {
		// 		decrease the counter by one
		if(this.getName() != "Home"){ 			// ie if we are at home we let names build up
			this.registerRemove(trainName);
			this.setTrainCount(this.getTrainCount()-1);
			this.updateStatus();
		}
	}
}
