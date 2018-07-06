import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class FlightReservationController {
	
	private HashMap<Integer, Route> routeList = new HashMap<>();;
	private HashMap<Integer, AirCraft> airCraftList = new HashMap<>();
	private HashMap<Route, ArrayList<ScheduledAirCrafts>> scheduleList = new HashMap<Route, ArrayList<ScheduledAirCrafts>>();
	private ArrayList<Booking> bookingList = new ArrayList<>();
	
	
	//METHODS FOR ADDING OBJECTS INTO LIST
	public void addRoute(Route newRoute) {
		routeList.put(newRoute.getRouteID(), newRoute);
	}
	
	public void addAirCraft(AirCraft newAirCraft) {
		airCraftList.put(newAirCraft.getAirCraftId(), newAirCraft);
	}
	
	public void addSchedules(ScheduledAirCrafts newSchedule) {
		ArrayList<ScheduledAirCrafts> scheduleDetailsList;
		Route route = newSchedule.getRoute();
		if(scheduleList.containsKey(route)){
		    // if the key has already been used,
		    // we'll just grab the list and add the value to it
			scheduleDetailsList = scheduleList.get(route);
			scheduleDetailsList.add(newSchedule);
		} 
		else {
		    // if the key hasn't been used yet,
		    // we'll create a new Linked List object, add the value
		    // and put it in the list with the new key
			scheduleDetailsList = new ArrayList<ScheduledAirCrafts>();
			scheduleDetailsList.add(newSchedule);
		    scheduleList.put(route, scheduleDetailsList);
		}
	}
	
	public void addBooking(Booking newBooking) {
		bookingList.add(newBooking);
	}
	
	
	
	//METHODS FOR GETTING A PARTICULAR OBJECT FROM TEH LIST
	
	public Route getRoute(int routeID) {
		Route route = routeList.get(routeID);
		
		if(route != null) {
			return route;
		}
		return null;
	}
	
	public AirCraft getAirCraft(int aircraftID) {

		AirCraft airCraft = airCraftList.get(aircraftID);
		
		if(airCraft != null) {
			return airCraft;
		}
		return null;
		
	}
	
	public ScheduledAirCrafts getSchedule(Route route, int ScheduleID) {
		ArrayList<ScheduledAirCrafts> scheduleDetailsList = scheduleList.get(route);
		
		for(ScheduledAirCrafts schedule : scheduleDetailsList) {
			if(schedule.getScheduleID() == ScheduleID) {
				return schedule;
			}
		}
		return null;
	}
	
	
	//METHODS FOR DISPALYING THE LIST
	public void displayRoutes() {

		for(Entry<Integer, Route> entry : routeList.entrySet())  {
			Route route = entry.getValue();
			System.out.println(route.toString());
		}
	}
	
	public void displayScheduledAirCrafts(Route route) {
		ArrayList<ScheduledAirCrafts> scheduleDetailsList = scheduleList.get(route);
		if(scheduleDetailsList == null) {
			System.out.println("SORRY NO FLIGHTS HAVE BEEN SCHEDULED FOR THIS ROUTE");
			return;
		}
		
		for(ScheduledAirCrafts schedule : scheduleDetailsList) {
			System.out.println(schedule.toString());
		}
	}

}
