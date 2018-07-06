
public class RateRules {
	
	private final double increaseRatePercent;
	private final int seatLimit;
	public RateRules(double increaseRatePercent, int seatLimit) {
		this.increaseRatePercent = increaseRatePercent;
		this.seatLimit = seatLimit;
	}
	public double getIncreaseRatePercent() {
		return increaseRatePercent;
	}
	public int getSeatLimit() {
		return seatLimit;
	}
	
	public double getPercentageIncrease(int bookedSeats) {
		int increaseTimes = bookedSeats/this.getSeatLimit();
		return increaseTimes*this.getIncreaseRatePercent();
	}
	
	
	
	

}
