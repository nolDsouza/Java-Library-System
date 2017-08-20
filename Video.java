package s3600251_A2;

public class Video extends Holding {

	private int loanFee;
	private int lateFee;
	
	
	public Video(String holdingId, String title, int loanFee) {
		super(holdingId, title);
		this.loanFee = loanFee;
	}
	
	public int getDefaultLoanFee() {
		return loanFee;
	}
	
	public int getMaxLoandPeriod() {
		return 7;
	}
	
	
	public int calculateLateFee(DateTime dateReturned) {
		if (DateTime.diffDays(dateReturned, getBorrowDate()) > 7) {
			lateFee = (DateTime.diffDays(dateReturned, getBorrowDate() ) - 7) * (getDefaultLoanFee() / 2);
			return lateFee;
		}
		return 0;
	}
	
	public boolean activate() {
		char refChar = super.getTitle().charAt(0);
		if (getID().length() != 6)
			return false;
		super.activate();
		return true;
	}
}


