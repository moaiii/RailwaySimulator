import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public abstract class Infrastructure {

	private String name;
	private int capacity;
	private int length;					
	private int trainCount;
	private boolean isFull;
	private String[] register;
	
	// Constructor 
	public Infrastructure (int length, String name, int capacity) {
		this.length = length;		// station also has a small length for sleep operation
		this.name = name;
		this.capacity = capacity;
		this.trainCount = 0;		// initial train count at construction is zero
		this.isFull = false;
		this.register = new String[this.getCapacity()]; // for keeping track of what train is occupying
		
		for (int i = 0; i < this.getCapacity(); i++) {	// initialize register with blank strings
			this.register[i] = "-";
		}
	}

	/*
	 * 		Get and Set Methods
	 */
	public String getName() {return name;}

	public int getCapacity() {return capacity;}

	public int getLength() {return length;}

	public int getTrainCount() {return trainCount;}
	public void setTrainCount(int newTrainCount) {this.trainCount = newTrainCount;}

	public boolean getisFull() {
		this.updateStatus();
		return isFull;
	}
	public void setFull(boolean isFull) {this.isFull = isFull;}
	
	/*
	 * 		Methods to be implemented for journeying
	 * 		Different implementations for station and track
	 */
	public void enter (String trainName, int trainSpeed) throws InterruptedException {	}
	
	public void leave (String trainName) throws InterruptedException {	}

	/*
	 * 		Standard methods for printing and keeping track of status in each elemtn
	 * 		Same implementation for both stationand track
	 */
	public void updateStatus() {
		
		if(this.getTrainCount() == this.getCapacity()) {	// section is full
			this.setFull(true);
		}
		if(this.getTrainCount() < this.getCapacity()) {		// section is empty
			this.setFull(false);
		}
		if(this.getTrainCount() < 0 || this.getTrainCount() > this.getCapacity()) {		
			// Throw console error alert if a track or station goes over capacity
			// i.e there is a bug in the program logic
			System.out.println("*ERROR* * * * Train count error at "+this.getName()+" ("+this.getTrainCount()+"/"+this.getCapacity()+")");
		}
	}
	
	public void registerAdd (String trainName) {
		for (int i = 0; i < this.getCapacity(); i++) {
			if(this.register[i] == "-") {		// find first empty string " " and add name 
				this.register[i] = trainName; 	// add name to register
				break;						
			}
		}
		updateStatus();
	}
	
	public void registerRemove(String trainName) {
		for (int i=0; i <this.getCapacity(); i++) {
			if(this.register[i]==trainName) { 		// look for name on register
				this.register[i] = "-"; 			// delete name from register
				break;						
			}
		}
		updateStatus();		// update isFull condition
	}

	public String status() {		// console printer runs each time a train moves in to a new infrastructre section
		
		String statusPrint = "|-("+this.getName()+")("+this.getTrainCount()+"/"+this.getCapacity()+")-";
		
		for (int i=0; i <this.getCapacity(); i++) {
			statusPrint += this.register[i];
		}
		statusPrint += "-|";
		
		return statusPrint;
	}
}
