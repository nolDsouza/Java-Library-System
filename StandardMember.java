package s3600251_A2;

public class StandardMember extends Member {
	
	
	
	public StandardMember(String memberId, String fullName) {
		super(memberId, fullName, 35);
	}
	
	public int getMaxCredit() {
		return 35;
	}

	@Override
	public boolean returnHolding(Holding holding, DateTime returnDate) {
		boolean notReturned = true;
		int i = 0;
		if (isInactive) {
			System.out.println("Inactive member cannot return holdings");
			return false;
		}
		else if(checkAllowedCreditOverdraw(holding.calculateLateFee(returnDate))) {
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
		else if(!checkAllowedCreditOverdraw(holding.calculateLateFee(returnDate))) {
			System.out.println("Insufficient funds");
			return false;
		}
		System.out.print("Member does not own this holding");
		return false;
	}
	
	public boolean activate() {
		if (getID().charAt(0) != 115)
			return false;
		super.activate();
		return true;
	}

}
