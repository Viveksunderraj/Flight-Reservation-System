
public class Route {

	private static int routeIDCounter = 1;
	private int routeID;
	private City departureCity;
	private City arrivalCity;
	private int travelDurationInMinutes;
	
	public enum City {
		CHENNAI(1), DELHI(2), BANGLORE(3), MUMBAI(4);
		
		private int code;
		private City(int code) {
			this.code = code;
		}
		
		public int getCityCode() {
			return this.code;
		}
	}

	public Route(City departureCity, City arrivalCity, int travelDurationInMinutes) {
		this.routeID = getNextID();
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.travelDurationInMinutes = travelDurationInMinutes;
		
	}
	
	private static int getNextID() {
		return routeIDCounter++;
	}
	
	public int getRouteID() {
		return this.routeID;
	}
	public City getDepartureCity() {
		return this.departureCity;
	}

	public void setDepartureCity(City departureCity) {
		this.departureCity = departureCity;
	}

	public City getArrivalCity() {
		return this.arrivalCity;
	}

	public void setArrivalCity(City arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public int getTravelTimeInMinutes() {
		return this.travelDurationInMinutes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalCity == null) ? 0 : arrivalCity.hashCode());
		result = prime * result + ((departureCity == null) ? 0 : departureCity.hashCode());
		result = prime * result + routeID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (arrivalCity != other.arrivalCity)
			return false;
		if (departureCity != other.departureCity)
			return false;
		if (routeID != other.routeID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ROUTE ID - " + routeID +"\nDeparture City = " + departureCity + ",\nArrival City = " + arrivalCity + ",\nTravel Duration In Minutes = "
				+ travelDurationInMinutes + "\n";
	}
	
	
	

}
