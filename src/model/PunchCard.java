package model;

public class PunchCard {
	//TODO: Fill out this class based on the UML diagram
	public int numVisits = 0;

	public void addVisit() {
		// TODO Auto-generated method stub
		numVisits++;
		
	}

	public boolean isNthVisit(int n) {
		// TODO Auto-generated method stub
		int disc = LoyaltyProgram.VISITS_FOR_DISCOUNT;
		if (n%disc==0) {
			return true;
		}
		return false;
	}
	
	
}
