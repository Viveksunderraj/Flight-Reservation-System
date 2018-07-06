import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduledAirCraft {
	private static int scheduleIDCounter = 1;
	private int scheduleID;
	private Route route;
	private Date scheduleDate;
	private int deparTureTime;
	private int arrivalTime;
	private AirCraft airCraft;
	private Seats[][] seats;
	private int bookedSeats;
	private RateRules rateRules;
	private double buisnessClassRate;
	private double economyClassrate;
	
	public ScheduledAirCraft(Route route, AirCraft airCraft, int buisnessClassRate, int economyClassrate, int departureTime, int arrivalTime, String date) {
		this.scheduleID = getNextID();
		this.route = route;
		try {
			this.scheduleDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		this.deparTureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.airCraft = airCraft;
		this.seats = fillSeats(9,3);
		this.bookedSeats = 0;
		this.rateRules = new RateRules(0.05, 3);
		this.buisnessClassRate = buisnessClassRate;
		this.economyClassrate = economyClassrate;
	}
	
	private static int getNextID() {
		synchronized (ScheduledAirCraft.class) {
			return scheduleIDCounter++;
		}
	}
	
	
	private Seats[][] fillSeats(int row, int col) {
		Seats[][] newSeats = new Seats[row][col];
		//FOR FILLING BUISNESS CLASS SEATS
		for(int i=0; i<3; i++) {
			for(int j=0;j<col;j++) {
				String tempSeatNumber = Integer.toString(Seats.SEAT_NUMS[i]) + Seats.SEAT_LETTERS[j];
				newSeats[i][j] = new Seats(Seats.SeatType.BUISNESS_CLASS, tempSeatNumber, false);
			}
		}
		
		//FOR FILLING ECONOMIC CLASS SEATS
		for(int i=3; i<row; i++) {
			for(int j=0;j<col;j++) {
				String tempSeatNumber = Integer.toString(Seats.SEAT_NUMS[i]) + Seats.SEAT_LETTERS[j]; 
				newSeats[i][j] = new Seats(Seats.SeatType.ECONOMY_CLASS, tempSeatNumber, false);
			}
		}
		return newSeats;
	}
	
	
	public Route getRoute() {
		return this.route;
	}
	
	public double getBuisnessClassRate() {
		return this.buisnessClassRate;
	}

	public double getEconomyClassRate() {
		return this.economyClassrate;
	}
	
	public int getScheduleID() {
		return this.scheduleID;
	}
	
	public Seats[][] getSeats() {
		return this.seats;
	}
	
	
	public Seats getSeat(int row, int col) {
		return this.seats[row][col];
	}
	
	
	public void updateBookedSeats(int numberOfSeats) {
		this.bookedSeats += numberOfSeats;
		
	}
	
	public RateRules getRateRule() {
		return this.rateRules;
	}
	
	public int getBookedSeats() {
		return this.bookedSeats;
	}
	
	@Override
	public String toString() {

		return  "Flight ID = " + scheduleID + ", " + airCraft.toString() + ", Date = " + scheduleDate.toString() +", Departure Time = " +deparTureTime
				+ ", Arrival Time = " + arrivalTime;
	}
}
