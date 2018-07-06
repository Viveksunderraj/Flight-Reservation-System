
public class Seats {
	
	private SeatType seatType;
	private String seatNumber;
	private boolean isSeatBooked;
	private Passenger passenger;
	private double seatCost;
	private Booking bookingDetails;
	
	public static final char SEAT_LETTERS[] = {'A','B','C'};
	public static final int SEAT_NUMS[] = {1,2,3,4,5,6,7,8,9,10};
	
	public enum SeatType {
			ECONOMY_CLASS, BUISNESS_CLASS;
	}


	public Seats(SeatType seatType, String seatNumber, boolean isSeatBooked) {
		this.seatType = seatType;
		this.seatNumber = seatNumber;
		this.isSeatBooked = isSeatBooked;
		this.seatCost = 0;
		this.passenger = null;
	}
	
	public boolean getSeatStatus() {
		return this.isSeatBooked;
	}
	
	public SeatType getSeatType() {
		return this.seatType;
	}
	
	public void setSeatStatus(boolean status) {
		this.isSeatBooked = status;
	}
	
	public void addPassenger(Passenger newPassenger) {
		this.passenger = newPassenger;
	}
	
	public void setSeatPrice(double price) {
		this.seatCost = price;
	}
	
	public void addBookingDetails(Booking bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
	
	public Booking getBooking() {
		return this.bookingDetails;
	}
	
	public Passenger getPassenger() {
		return this.passenger;
	}
	
	public static void displaySeatMap(Seats[][] seat) {
		
		System.out.println("\n o - Seat Not Booked     x - Seat Booked");
		
		System.out.print("\n   "); 
		
		for(char i: SEAT_LETTERS){ 
				System.out.print("    " + i + "    ");
			}
			System.out.print("\n   ");
			
			for(int i = 0; i < 3; i++){
				System.out.print("   ---   ");
			}
			System.out.print("\n");
		
		 for(int i=0;i<seat.length;i++) {
			 System.out.print(" " + (i+1) + " ");
			for(int j=0;j<seat[0].length;j++) {
				if(seat[i][j].getSeatStatus() == false) {
					System.out.print(" | "+" o "+" | ");
				}
				else {
					System.out.print(" | "+" x "+" | ");
				}
				
			}
			System.out.print("\n   ");
			
			for(int j = 0; j < 3; j++){
				System.out.print("   ---   ");
			}
			if(i==2 || i==8) {
				System.out.println("  " + seat[i][2].getSeatType());
				System.out.println("-------------------------------------------------");
			}
			System.out.print("\n");
		}
		
		
		
	}

	@Override
	public String toString() {
		return  passenger.toString() + ", Seat Type = " + seatType + ", Seat Number = " + seatNumber + ", Seat Cost = "
				+ seatCost;
	}
	
	

}
