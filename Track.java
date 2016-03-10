import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Track extends Infrastructure{
	
	   private Lock trainLock;
	   private Condition con;
	   
	//	 Contructor 
	public Track(int length, String name, int capacity) {
		super(length, name, capacity);
		trainLock = new ReentrantLock();
		con = trainLock.newCondition() ;
	}
	
	public void enter (String trainName, int trainSpeed) throws InterruptedException {
		// lock
		trainLock.lock();
		//System.out.println(trainName+" has locked (***) "+this.getName());
		try { 
			//	Train Counter +1
			this.setTrainCount(this.getTrainCount()+1);
			this.updateStatus();			// set full signal
			this.registerAdd(trainName); 	// add name to the register
			
			//	Sleep
			int sleepTime = this.getLength()/trainSpeed;
			Thread.sleep(sleepTime*10);
			con.signalAll();
		    leave(trainName);

		} finally {
			// unlock
		    // System.out.println(trainName+" has (###) unlocked @ "+this.getName());
		    trainLock.unlock();
		}
	}

	public void leave(String trainName) {
		// decrement
		this.setTrainCount(this.getTrainCount()-1);
		this.updateStatus();				// set empty signal
		this.registerRemove(trainName);		// remove name from register
	}
}
