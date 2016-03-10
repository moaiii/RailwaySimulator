import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runme {
	
	/*
	 * 		Specify railway system global variables
	 */
	public static final int NUM_TRAINS = 10;
	
	public static void main (String[] args) throws Exception {

		/*
		 * 		Initialize stations and tracks
		 * 		(length, name, capacity, initial train count=0, initial isFull=false)
		 */
		Station Glasgow = new Station(1000, "Glasgow", 5);			// user variable
		Station Stirling = new Station(1000, "Stirling", 2);		// user variable
		Station Perth = new Station(1000, "Perth", 1);				// user variable
		Station Inverness = new Station(1000, "Inverness", 2);		// user variable
		Station Home = new Station (1000, "Home", NUM_TRAINS);	// capacity = num of trains being run
		
		Track t1 = new Track(30000, "GLA>STR", 1);					// user variable	
		Track t2 = new Track(10000, "STR>PER", 1);					// user variable
		Track t3 = new Track(40000, "PER>INV", 1);					// user variable
		
		/*
		 *  the pieces of the railway must be passed to the constructor 
		 *  in correct geographical (construction) order. Ends with home
		 */
		Railway r1 = new Railway (8, Glasgow, t1, Stirling, t2, Perth, t3, Inverness, Home);
		
		// 		create train creator object
		TrainCreator tc = new TrainCreator(r1);
		
		//		Run trains 
		tc.create();
	}
}
