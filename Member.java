package s3600251_A2;

public abstract class Member {

	private String memberId;
	private String fullName;
	private int credit;
	// Each member should have their credit set to maximum available credit limit for that type of member when created
	private Holding[] holdings = new Holding[15];
	protected boolean isInactive;
	protected int loanFee;
	
	public Member(String memberId, String fullName, int credit) {
		this.memberId = memberId;
		this.fullName = fullName;
		this.credit = credit;
	}
	
	public String getID() {
		return memberId;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public boolean getStatus() {
		if (isInactive) {
			System.out.println("Inactive");
			return false;
		}
		else if (calculateRemainingCredit() < 0) {
			System.out.println("In debt");
			return false;
		}
		else if (calculateRemainingCredit() >= 4) {
			System.out.println("Can borrow holdings");
			return true;
		}
		System.out.println("Active");
		return false;
	}
	
	public abstract int getMaxCredit();
	
	public void resetCredit() {
		this.credit = getMaxCredit();
	}
	
	public Holding[] getCurrentHoldings() {
		return holdings;
	}
	
	public int calculateRemainingCredit() {
		return credit;
	}
	
	public boolean updateRemainingCredit(int loanFee) {
		if (loanFee > 0) {
			this.credit -= loanFee;
			return true;
		}
		return false;
	}
	
	public boolean checkAllowedCreditOverdraw(int loanFee) {
		if (calculateRemainingCredit() > loanFee)
			return true;
		return false;
	}
			
	
	public boolean borrowHolding(Holding holding) {
		int i = 0;
		if (!getStatus() || !holding.getStatus()) {
			return false;
		}
		else if (checkAllowedCreditOverdraw(holding.getDefaultLoanFee())) { 
			boolean borrowReq = true;
			do {
			    if (holdings[i] == null) {
			    	holdings[i] = holding;
			    	borrowReq = false;
			    }
			    i++;
			} while(borrowReq && i < 14);
			if (!borrowReq) {
				holding.borrowHolding();
				this.loanFee = holding.getDefaultLoanFee();
				updateRemainingCredit(this.loanFee);
				System.out.println("Successfully borrowed " + holdings[i-1].toString() + " for $" + holdings[i-1].getDefaultLoanFee());
				return true;
			}
			else
				System.out.println("Cannot borrow as Member already has too many holdings");
			
		}
		else if (checkAllowedCreditOverdraw(holding.getDefaultLoanFee()) == false) 
			System.out.println("insufficient funds");
		return false;
		
	}
	
	public abstract boolean returnHolding(Holding holding, DateTime returnDate);
		
	public void print() {
		System.out.print("ID:");
		System.out.printf("%17s", "");
		System.out.println(memberId);
		System.out.print("Name:");
		System.out.printf("%15s", "");
		System.out.println(fullName);
		System.out.print("Credit: $");
		System.out.printf("%11s", "");
		System.out.println(credit);
		System.out.print("Holdings:");
		System.out.printf("%11s", "");
		for(int i = 0; i < holdings.length; i++) {
			if (getCurrentHoldings()[i] == null)
				System.out.print("");
			else 
				System.out.print(getCurrentHoldings()[i].getTitle() + " ");
		}
		System.out.println();
		System.out.print("Status:");
		System.out.printf("%13s", "");
		getStatus();
		System.out.println();
	}
	
	public String toString() {
		return (memberId + ":" + fullName + ":" + credit);
	}
	
	public boolean isEmpty() {
		int count = 0;
		for(int i = 0; i < holdings.length; i++) {
			if (getCurrentHoldings()[i] == null)
				count++;
		}
		if (count == holdings.length)
				return true;
		return false;
	}
	
	public boolean activate() {
		if (getID().length() != 7)
			return false;
		this.isInactive = false;
		return true;
	}
	
	public boolean deactivate() {
		if (!isEmpty())
			return false;
		this.isInactive = true;
		return true;
	}

}
