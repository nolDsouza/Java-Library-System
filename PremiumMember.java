package s3600251_A2;

public class PremiumMember extends Member {

	public PremiumMember(String memberId, String fullName) {
		super(memberId, fullName, 45);
	}
	
	public int getMaxCredit() {
		return 45;
	}
	
	@Override
	public boolean returnHolding(Holding holding, DateTime returnDate) {
		boolean notReturned = true;
		int i = 0;
		if (isInactive) {
			System.out.println("Inactive member cannot return holdings");
			return false;
		}
		else  {
			do {
				if (getCurrentHoldings()[i] == holding) {
					this.loanFee = holding.calculateLateFee(returnDate);
					updateRemainingCredit(this.loanFee);
					System.out.println("Successfully returned " + holding.getTitle() + " for $" +  this.loanFee);
					getCurrentHoldings()[i] = null;
					holding.returnHolding();
					notReturned = false;
					return true;
				}
				i++;
			} while(notReturned && i < 3);
		}
		System.out.print("Member does not own this holding");
		return false;
		
	}
}
