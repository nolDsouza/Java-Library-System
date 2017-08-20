package s3600251_A2;

public class Book extends Holding {

	private int lateFee;
	private DateTime borrowDate;
	
	public Book(String holdingId, String title) {
		super(holdingId, title);
		
	}
	
	public int getDefaultLoanFee() {
		return 10;
	}

	public int getMaxLoandPeriod() {
		return 28;
	}
	
	@Override
	public int calculateLateFee(DateTime dateReturned) {
		if (DateTime.diffDays(dateReturned, getBorrowDate()) > 28)
			lateFee = (DateTime.diffDays(dateReturned, getBorrowDate()) - 28) * 2;
		return lateFee;
	}
	
	public boolean activate() {
		if (getID().charAt(0) != 98)
			return false;
		super.activate();
		return true;
	}
	
}

