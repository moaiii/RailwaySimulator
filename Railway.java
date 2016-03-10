import java.util.LinkedList;

public class Railway {
	
	private Infrastructure I1;
	private Infrastructure I2;
	private Infrastructure I3;
	private Infrastructure I4;
	private Infrastructure I5;
	private Infrastructure I6;
	private Infrastructure I7;
	private Infrastructure I8;
	private Infrastructure[] network;	// array to store station or track objects
	
	// 	Constructor
	//	if expansion or other tracks built of different sizes, new constructors must be added
	public Railway (int size, Station s1, Track t1, Station s2, Track t2, Station s3, Track t3, Station s4, Station home) {
		
		this.I1 = s1;
		this.I2 = t1;
		this.I3 = s2;
		this.I4 = t2;
		this.I5 = s3;
		this.I6 = t3;
		this.I7 = s4;
		this.I8 = home;
		
		// build the railway network array
		network = new Infrastructure[size];
		network[0] = I1;
		network[1] = I2;
		network[2] = I3;
		network[3] = I4;
		network[4] = I5;
		network[5] = I6;
		network[6] = I7;
		network[7] = I8;
	}

	/*
	 * 		Get and Set Methods
	 * 		Only getters required, setting done in constructor
	 */
	public int getSize() 					{return 8;}
	public Infrastructure getI1() 			{return I1;}
	public Infrastructure getI2() 			{return I2;}
	public Infrastructure getI3() 			{return I3;}
	public Infrastructure getI4() 			{return I4;}
	public Infrastructure getI5() 			{return I5;}
	public Infrastructure getI6() 			{return I6;}
	public Infrastructure getI7() 			{return I7;}
	public Infrastructure[] getNetwork() 	{return network;}	
}