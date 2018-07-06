import java.util.Date;
import java.util.Scanner;

public class FlightReservationSystem {
	
	public  FlightReservationController flight = new FlightReservationController();
	
	private static final int BOOKING_LIMIT = 6;
	static Scanner sc = new Scanner(System.in);
	
	private void createRoutes(Route.City deptCity, Route.City arrivalCity, int duration) {
		Route newRoute = new Route(deptCity, arrivalCity, duration);
		flight.addRoute(newRoute);
	}
	
	private void createAirCrafts(String airCraftName) {
		AirCraft newAirCraft = new AirCraft(airCraftName);
		flight.addAirCraft(newAirCraft);
	}
	
	private void createSchedules(int routeID, int airCraftID, int BC_PRICE, int EC_PRICE, int departureTime, int arrivalTime, String date) {
		ScheduledAirCrafts newSchedule = new ScheduledAirCrafts(flight.getRoute(routeID), flight.getAirCraft(airCraftID), BC_PRICE, EC_PRICE, departureTime, arrivalTime, date);
		flight.addSchedules(newSchedule);
	}
	
	/*private void createBooking(String bookingCustomerName, int numberOfSeatsBooked,ScheduledAirCrafts scheduledAirCraft) {
		Booking newBooking = new Booking(bookingCustomerName, numberOfSeatsBooked, scheduledAirCraft);
		flight.addBooking(newBooking);
		
	}*/
	
	private int convertCharToNum(char charIn){
		   //HERE WE RETURN -1 IF IT IS NOT A VALID INPUT(i.e input outside the box)
			int number = -1;
			
			for(int i = 0; i < Seats.SEAT_LETTERS.length; i++){
				
				if(Seats.SEAT_LETTERS[i] == charIn){
					number = i;
				}
			}
			return number;
		}
	
	private int convertCharNumtoNum(char charIn){ 
		//HERE WE RETURN -1 IF IT IS NOT A VALID INPUT(i.e input outside the box)
		int number = -1;
		int convertedNum = Character.getNumericValue(charIn);
		
		for(int i: Seats.SEAT_NUMS){
			if(i == convertedNum){
				number = convertedNum;   
			}
		}
		return number;
	}
	
	private void printBookingSummary(Seats[][] seat, Booking booking) {
		
		System.out.println(booking.toString());
		System.out.println();
		for(int i=0;i<9;i++) {
			for(int j=0;j<3;j++) {
				if(seat[i][j].getBooking() == booking) {
					System.out.println(seat[i][j].toString());
				}
			}
		}
		printSeparator();
	}
	
	
	private void showBookingMenu() {
		
		
		int routeID, flightID, numberOfSeats;
		String bookingCustomerName;
		double bookingCost = 0;
		System.out.println("PLEASE ENTER YOUR NAME");
		bookingCustomerName = sc.nextLine();
		System.out.println("\nSELECT A ROUTE IN WHICH YOU WANT TO TRAVEL\n");
		printSeparator();
		flight.displayRoutes();
		printSeparator();
		routeID = sc.nextInt();
		Route selectedRoute = flight.getRoute(routeID);
		if(selectedRoute == null) {
			System.out.println("PLEASE ENTER A VALID ROUTE ID");
			return;
		}
		System.out.println("YOUR SELECTED ROUTE IS " + selectedRoute.toString());
		
		System.out.println("FLIGHTS AVAILABLE IN THE SELECTED ROUTE ARE");
		flight.displayScheduledAirCrafts(selectedRoute);
		printSeparator();
		System.out.println("SELECT A FLIGHT ID");
		flightID = sc.nextInt();
		ScheduledAirCrafts selectedSchedule = flight.getSchedule(selectedRoute, flightID);
		if(selectedSchedule == null) {
			System.out.println("PLEASE ENTER A VALID FLIGHT ID");
		}
		
		double increasePercent = selectedSchedule.getRateRule().getPercentageIncrease(selectedSchedule.getBookedSeats());
		
		System.out.println("HOW  MANY SEATS DO YOU WANT TO BOOK?");
		numberOfSeats = sc.nextInt();
		selectedSchedule.updateBookedSeats(numberOfSeats);
		
		Booking newBooking = new Booking(bookingCustomerName, numberOfSeats, selectedSchedule, new Date());
		flight.addBooking(newBooking);
		
		sc.nextLine();
		if(numberOfSeats <= BOOKING_LIMIT) {
		
		for(int i=0; i< numberOfSeats; i++) {
			String passengerName, seatNumber;
			int passengerAge;
			double seatPrice = 0, buisnessClassSeatPrice = selectedSchedule.getBuisnessClassRate(), economyCLassSeatPrice = selectedSchedule.getEconomyClassRate();
			
			System.out.println("Enter the Passenger Name");
			passengerName = sc.nextLine();
			System.out.println("Enter the Passenger Age");
			passengerAge = sc.nextInt();
			
			Seats.displaySeatMap(selectedSchedule.getSeats());
			
			//HERE WE CLACULATE THE NEW PRICE
			buisnessClassSeatPrice += increasePercent*buisnessClassSeatPrice;
			economyCLassSeatPrice += increasePercent*economyCLassSeatPrice;
			System.out.println("BUISNESS CLASS PRICE = " + buisnessClassSeatPrice + "\nECONOMY CLASS PRICE = " + economyCLassSeatPrice);
			
			
			System.out.println("Enter the Seat Number that you want to book");
			sc.nextLine();
			seatNumber = sc.nextLine();
			
			if(!seatNumber.isEmpty() && seatNumber.length() <= 2 && !(seatNumber.contains(" ") || seatNumber.contains("\t"))){
				
				//making sure first char is a digit and making sure the second is a char
				if(Character.isDigit(seatNumber.charAt(0)) && !Character.isDigit(seatNumber.charAt(1))){	
					
					int row, col;
		
					if((row = convertCharNumtoNum(seatNumber.charAt(0))) != -1 && (col = convertCharToNum(Character.toUpperCase(seatNumber.charAt(1)))) != -1){
						
						Seats seat = selectedSchedule.getSeat(row-1, col);
						Passenger newPassenger = new Passenger(passengerName, passengerAge);
						
						if(seat.getSeatType() == Seats.SeatType.BUISNESS_CLASS) {
							seatPrice = buisnessClassSeatPrice;
						}
						else if(seat.getSeatType() == Seats.SeatType.ECONOMY_CLASS){
							seatPrice = economyCLassSeatPrice;
						}
					
						seat.setSeatStatus(true);
						seat.addPassenger(newPassenger);
						seat.setSeatPrice(seatPrice);
						seat.addBookingDetails(newBooking);
						
						bookingCost += seatPrice;
						System.out.println("SUCCESSFULLY BOOKED SEAT");
					}
					else {
						System.out.println("ENTER A VALID SEAT NUMBER");
						return;
					}
				}
				else {
					System.out.println("ENTER A VALID SEAT NUMBER");
					return;
				}
			}
			
			printSeparator();
		}
		
		}
		else {
			System.out.println("ONLY 6 TICKETS CAN BE BOOKED AT ONCE");
			return;
		}
		
		newBooking.setBookingCost(bookingCost);
		
		printBookingSummary(selectedSchedule.getSeats(), newBooking);
		
	}
	
	public static void printSeparator() {
		for(int i=0; i<80; i++) {
			System.out.print("=");
		}
		System.out.println("");
	}
	
	public static void main(String[] args) {
		
		FlightReservationSystem newFlightReservationSystem = new FlightReservationSystem();
		
		//CREATING SOME ROUTES INITIALLY
		newFlightReservationSystem.createRoutes(Route.City.CHENNAI, Route.City.BANGLORE, 120); //Route id = 1
		newFlightReservationSystem.createRoutes(Route.City.CHENNAI, Route.City.DELHI, 180); //Route id = 2
		newFlightReservationSystem.createRoutes(Route.City.CHENNAI, Route.City.MUMBAI, 160); //Route id = 3
		newFlightReservationSystem.createRoutes(Route.City.DELHI, Route.City.BANGLORE, 200); //Route id = 4
		newFlightReservationSystem.createRoutes(Route.City.MUMBAI, Route.City.BANGLORE, 190); //Route id = 5
		
		
		//CREATING SOME AIRCRATFS INITIALLY
		newFlightReservationSystem.createAirCrafts("AIR INDIA"); // id = 100
		newFlightReservationSystem.createAirCrafts("AIR INDIA"); // id = 101
		newFlightReservationSystem.createAirCrafts("JET AIRWAYS"); // id = 102
		newFlightReservationSystem.createAirCrafts("JET AIRWAYS"); // id = 103
		newFlightReservationSystem.createAirCrafts("KINGFISHER"); // id = 104
		newFlightReservationSystem.createAirCrafts("KINGFISHER"); // id = 105
		newFlightReservationSystem.createAirCrafts("SPICE JET"); // id = 106
		newFlightReservationSystem.createAirCrafts("SPICE JET"); // id = 107
		newFlightReservationSystem.createAirCrafts("INDIGO"); // id = 108
		newFlightReservationSystem.createAirCrafts("INDIGO"); // id = 109
		
		
		//CREATING NEW SCHEDULES
		newFlightReservationSystem.createSchedules(1, 100, 2000, 1000, 600, 800, "21/12/2018");
		newFlightReservationSystem.createSchedules(1, 102, 2000, 1000, 700, 900, "21/12/2018");
		newFlightReservationSystem.createSchedules(1, 104, 2000, 1000, 715, 915, "22/12/2018");
		newFlightReservationSystem.createSchedules(1, 106, 2000, 1000, 920, 1120, "22/12/2018");
		newFlightReservationSystem.createSchedules(1, 108, 2000, 1000, 830, 1030, "21/12/2018");
		
		newFlightReservationSystem.createSchedules(2, 101, 5000, 2500, 1300, 1400, "20/12/2018");
		newFlightReservationSystem.createSchedules(2, 103, 5000, 2500, 1330, 1430, "20/12/2018");
		
		newFlightReservationSystem.createSchedules(3, 105, 3000, 1500, 1600, 1700, "20/12/2018");
		newFlightReservationSystem.createSchedules(3, 107 ,3000, 1500, 1630, 1730, "21/12/2018");
		
		newFlightReservationSystem.createSchedules(4, 109, 2150, 1580, 1800, 1900, "22/12/2018");
		
		System.out.println("<<<<<<<< WELCOME TO FLIGHT RESERVATION SITE >>>>>>>>");
		System.out.println();
		char option = '\0';
		do {
		newFlightReservationSystem.showBookingMenu();
		
		System.out.println("DO YOU WANT TO CONTINUE BOOKING\n 1) YES 2)NO");
		option = sc.next().charAt(0);
		
		}while(option != '1' || option != '2');
		
		

	}

}
