package s3600251_A2;

public abstract class Holding implements SystemOperations {

	protected String holdingId;
	protected String title;
	private int loanPeriod;
	private int lateFee;
	private boolean isRemoved;
	private boolean isInactive;
	private DateTime borrowDate;
	
	public Holding(String holdingId, String title) {
		this.holdingId = holdingId;
		this.title = title;
	}
	
	public String getID() {
		return holdingId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public boolean getStatus() {
		if (isInactive) {
			System.out.println("Inactive");
			return false;
		}
		else if (isOnLoan()) {
			System.out.println("On Loan");
			return false;
		}
		else {
			System.out.println("Available for loan");
			return true;
		}
	}
	
	public boolean isOnLoan() {
		return isRemoved;
	}
	
	
	
	public abstract int getDefaultLoanFee();
	
	public abstract int getMaxLoandPeriod();
	
	public abstract int calculateLateFee(DateTime dateReturned);
	
	public boolean borrowHolding() {
		if (!isInactive && !isOnLoan()) {
			this.isRemoved = true;
			this.borrowDate = new DateTime();
			return true;
		}
		else
			return false;
	}
	
	public void print() {
		System.out.print("ID:");
		System.out.printf("%17s", "");
		System.out.println(holdingId);
		System.out.print("Title:");
		System.out.printf("%14s", "");
		System.out.println(title);
		System.out.print("Loan Fee:");
		System.out.printf("%11s", "");
		System.out.println(getDefaultLoanFee());
		System.out.print("Max Loan Period:");
		System.out.printf("%4s", "");
		System.out.println(getMaxLoandPeriod());
		System.out.print("Status:");
		System.out.printf("%13s", "");
		getStatus();
		System.out.println();
		
	}
	
	public String toString() {
		return (holdingId + ":" + title + ":" + getDefaultLoanFee() + ":" + getMaxLoandPeriod());
	}
	public DateTime getBorrowDate() {
		return borrowDate;
	}
	
	public void returnHolding() {
		isRemoved = false;
	}
	
	public boolean activate() {
		if (getID().length() != 7)
			return false;
		this.isInactive = false;
		return true;
	}
	
	public boolean deactivate() {
		if (isOnLoan())
			return false;
		this.isInactive = true;
		return true;
	}
}
