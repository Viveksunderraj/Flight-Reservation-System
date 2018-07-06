
public class AirCraft {
	
	private static int airCraftIDCounter = 100;
	private String airCraftName;
	private int airCraftID;
	private int totalNumberOfSeats;

	
	public AirCraft(String airCraftName) {

		this.airCraftName = airCraftName;
		this.totalNumberOfSeats = 27;
		this.airCraftID = getNextID();
	}
	
	private static int getNextID() {
		synchronized (AirCraft.class) {
			return airCraftIDCounter++;
		}
	}
	
	public int getAirCraftId() {
		return this.airCraftID;
	}

	@Override
	public String toString() {
		return " [Air Craft Name = " + airCraftName + ", Air Craft ID = " + airCraftID + ", Total Seats = " + totalNumberOfSeats +"] ";
	}

	
	
	
}
