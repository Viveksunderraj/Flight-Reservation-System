
public class Passenger {
	private String passengerName;
	private int passengerAge;
	
	public Passenger(String passengerName, int passengerAge) {
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
	}

	@Override
	public String toString() {
		return "Passenger Name = " + passengerName + ", Passenger Age = " + passengerAge;
	}
	
	

}
