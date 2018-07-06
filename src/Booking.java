import java.util.Date;

public class Booking {
	
	private static int bookingIDCounter = 1;
	private int bookingID;
	private Date bookingDate;
	private String bookingCustomerName;
	private int numberOfSeatsBooked;
	private double bookingCost;
	private ScheduledAirCrafts scheduledAirCraft;
	
	
	public Booking(String bookingCustomerName, int numberOfSeatsBooked,
			ScheduledAirCrafts scheduledAirCraft, Date date) {
		this.bookingID = getNextID();
		this.bookingCustomerName = bookingCustomerName;
		this.numberOfSeatsBooked = numberOfSeatsBooked;
		this.bookingDate = date;
		this.scheduledAirCraft = scheduledAirCraft;
	}
	
	private static int getNextID() {
		synchronized (Booking.class) {
			return bookingIDCounter++;
		}
	}
	
	private synchronized static int getNextID1() {
		
			return bookingIDCounter++;
		
	}
	
	private synchronized  int getNextID2() {
		
		return bookingIDCounter++;
	}
	
	public void setBookingCost(double bookingCost) {
		this.bookingCost = bookingCost;
	}

	@Override
	public String toString() {
		return "Booking ID = " + bookingID + ",\nBooking Customer Name = " + bookingCustomerName + ",\nBooking Date = " + bookingDate
				+ ",\nNumber Of Seats Booked = " + numberOfSeatsBooked + ",\nBooking Cost = " + bookingCost
				+ ",\nFlight Details = " + scheduledAirCraft.toString();
	}
}
