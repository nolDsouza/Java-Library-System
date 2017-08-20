package s3600251_A2;

import java.util.Scanner;

public class Library {
	
	private Scanner admin = new Scanner(System.in);
	private String input;  
	private int HoldingRef;
	private int MemberRef;
	
	public static String getOutput() {			//Standard input and output to get theoretical user response
		Scanner admin = new Scanner(System.in);
		String output = admin.next();  
		return output;
	}
	
	
	
	public static boolean again() {				//This Method will be entered at the end of sections so the User can return to the menu or repeat an action
		boolean invalid = true;
		do {
		if (getOutput().equalsIgnoreCase("e") || getOutput().equalsIgnoreCase("exit")) {	//Trying to exit implies no action repetition
			invalid = false;
			return false;
		}
		else if (getOutput().equalsIgnoreCase("yes")) {		//Yes input will repeat any loop this boolean is used in 
			invalid = false;
			return true;
		}
		} while (invalid);
		return false;							//The Default return false should never happen
	}
	
	
	
	public void getLibraryMenu() {		//Entire method prints a visual menu representation to the console
		System.out.println("Library Management System");
		for(int i = 0; i < 80; i++)
		{
		    System.out.print("-");
		}
		System.out.println();
		System.out.println("1.  Add Holding\n" + "2.  Remove Holding\n" + "3.  Add Member\n" + "4.  Remove Member\n" + "5.  Borrow Holding\n"
		+ "6.  Return Holding\n" + "7.  Print all Holdings\n" + "8.  Print all Members\n" + "9.  Print specific Holding\n" + "10. Print specific Member\n"
		+ "11. System Operations (Activate/Deactivate)\n" + "12. Save to file\n" + "13. Load from file\n" + "12. Exit\n");
		
		for(int i = 0; i < 80; i++)
		{
		    System.out.print("-");
		}
		System.out.println("\nEnter an option:");
	}
	
	public String createId(String refChar, int i) {
		i += 8;
		return (refChar + "0000" + i);
	}
	
	public boolean validate(String tempString)  {
		if (tempString.length() >= 1)
			return true;
		return false;
	}
	
	public int getVideoFee() {
		int loanFee;
		do {
			System.out.println("Set default loan fee to $4 or $6? (input response)");
			Scanner chosen = new Scanner(System.in);
			loanFee = chosen.nextInt();
			if (loanFee == 4 || loanFee == 6)
				return loanFee;
			else
				System.out.println("Invalid price, must be $4 or $6");
			} while (loanFee != 4 && loanFee != 6);
		return 0;
	}
	
	public int getReference(Holding[] holdings) {
		String input;
		boolean invalid = true;
		do {
			input = admin.next();;
			for(int i = 0; i < holdings.length; i++) {
				if (holdings[i] != null && input.equals(holdings[i].getID())) {
					return i;
					}
			}
			
			if (invalid = true && input.equals("e")) {
				invalid = false;;
			}
			else {
				invalid = true;
				System.out.println("Could not find reference, try again or print e to exit");
			}
		} while (invalid);
		return 16;
	}
	
	public int getReference(Member[] members) {
		String input;
		boolean invalid = true;
		do {
			input = admin.next();;
			for(int i = 0; i < members.length; i++) {
				if (members[i] != null && input.equals(members[i].getID())) {
					return i;
					}
			}
			
			if (invalid = true && input.equals("e")) {
				invalid = false;;
			}
			else {
				invalid = true;
				System.out.println("Could not find reference, try again or print e to exit");
			}
		} while (invalid);
		return 16;
	}
	
	
	public void addHolding(Holding[] holdings) {
		for(int i = 0; i < 15; i++) {
		    if (holdings[i] == null) {
		    	System.out.println("Print 1 for book or 2 for video");
		    	input = admin.next(); 
		    	if (input.equalsIgnoreCase("book") || input.equals("1")) {
		    		do {
			    		System.out.println("Print book name");
			    		input = admin.next();
			    		validate(input);
		    		} while (!validate(input));
		    		holdings[i] = new Book(createId("b", i), input);
		    		System.out.println("Created Book: " + holdings[i].getTitle());
		    		break;
		    	}
		    	else if (input.equalsIgnoreCase("video") || input.equals("2")) {
		    		do {
			    		System.out.println("Print video name");
			    		input = admin.next();
			    		validate(input);
	    			} while (!validate(input));
		    		do {
		    			HoldingRef = getVideoFee();
		    		} while (HoldingRef != 4 && HoldingRef != 6);
		    		holdings[i] = new Video(createId("v", i), input, HoldingRef);
		    		System.out.println("Created Video: " + holdings[i].getTitle());
			    	break;
		    	}
		    }
		    else if(i == 14) {
		    	System.out.println("Cannot add holding, database full");
		    }
	    }
	}
	
	public void addMember(Member[] members) {
		for(int i = 0; i < 15; i++) {
		    if (members[i] == null) {
		    	System.out.println("Print 1 for standard or 2 for premium");
		    	input = admin.next(); 
		    	if (input.equalsIgnoreCase("standard") || input.equals("1")) {
		    		do {
			    		System.out.println("Print standard member name?");
			    		input = admin.next();
			    		validate(input);
		    		} while (!validate(input));
		    		members[i] = new StandardMember(createId("s", i), input);
		    		System.out.println("Created Standard Member:" + members[i].getFullName());
		    		break;
		    	}
		    	else if (input.equalsIgnoreCase("premium") || input.equals("2")) {
		    		do {
			    		System.out.println("Print premium member name");
			    		input = admin.next();
			    		validate(input);
	    			} while (!validate(input));
		    		members[i] = new PremiumMember(createId("p", i), input);
		    		System.out.println("Created Premium Member:" + members[i].getFullName());
			    	break;
		    	}
		    }
		    else if(i == 14) {
		    	System.out.println("Cannot add member, database full");
		    }
		}
	
	}
	
	public void removeHolding(Holding[] holdings) {
		for(int i = 0; i < 15; i++) {
		    if (holdings[i] != null) {
		    	System.out.println(holdings[i].getID() + " " + holdings[i].getTitle());
		    }
		}
		System.out.println("Remove which holding? (print ID e.g. b000002)");
		HoldingRef = getReference(holdings);
		if (HoldingRef != 16) {
			System.out.println("Removing " + holdings[HoldingRef].getTitle() + " Print e to cancel");
			input = admin.next();
			if(input.equalsIgnoreCase("e")) {}
			else {
				holdings[HoldingRef] = null;
				System.out.println("Removed Holding position "+HoldingRef + ", print e to exit");	
				again();
			}
		}
	}
	
	public void removeMember(Member[] members) {
		for(int i = 0; i < 15; i++) {
		    if (members[i] != null) {
		    	System.out.println(members[i].getID() + " " + members[i].getFullName());
		    }
		}
		System.out.println("Remove which Member? (print ID e.g. p000002)");
		MemberRef = getReference(members);
		if (MemberRef != 16) {
			System.out.println("Removing " + members[MemberRef].getFullName() + " Print e to cancel");
			input = admin.next();
			if(input.equalsIgnoreCase("e")) {}
			else {
				members[MemberRef] = null;
				System.out.println("Removed Holding position "+ MemberRef + ", print e to exit");	
				again();
			}
		}
	}
	
	public void borrowHolding(Holding[] holdings, Member[] members) {
		for(int i = 0; i < 15; i++) {
		    if (members[i] != null) {
		    	System.out.println(members[i].getID() + " " + members[i].getFullName());
		    }
		}
		System.out.println("Which Member is borrowing? (print ID e.g. p000002)");
		MemberRef = getReference(members);
		if (MemberRef == 16) 
			return;
		for(int i = 0; i < 15; i++) {
		    if (holdings[i] != null) {
		    	System.out.println(holdings[i].getID() + " " + holdings[i].getTitle());
		    }
		}
		System.out.println("Borrow which holding? (print ID e.g. b000002)");
		HoldingRef = getReference(holdings);
		if (HoldingRef == 16) 
			return;
		System.out.println(members[MemberRef].getFullName() + " is Borrowing " + holdings[HoldingRef].getTitle() + " Print e to cancel");
		input = admin.next();
		if(input.equalsIgnoreCase("e")) {}
		else {
			members[MemberRef].borrowHolding(holdings[HoldingRef]);
		}
		
		
	}
	
	public void returnHolding(Holding[] holdings, Member[] members) {
		DateTime todaysDate = new DateTime();
		for(int i = 0; i < 15; i++) {
		    if (members[i] != null && members[i].getCurrentHoldings() != null) {
		    	System.out.println(members[i].getID() + " " + members[i].getFullName());
		    }
		}
		System.out.println("Which Member is returning? (print ID e.g. p000002)");
		MemberRef = getReference(members);
		if (MemberRef == 16)
			return;
		HoldingRef = 0;
		for(int i = 0; i < members[MemberRef].getCurrentHoldings().length; i++) {
			if (members[MemberRef].getCurrentHoldings()[i] == null) {
				HoldingRef++;
			}
			else if (members[MemberRef].getCurrentHoldings()[i] != null)
				System.out.println(members[MemberRef].getCurrentHoldings()[i].toString() + " ");
		}
		if(HoldingRef == members[MemberRef].getCurrentHoldings().length) {
			System.out.println(members[MemberRef].getFullName() + " Does not have any hodlings to return");
			return;
		}
		System.out.println("Return which holding? (print ID e.g. b000002)");
		HoldingRef = getReference(members[MemberRef].getCurrentHoldings());
		if (HoldingRef == 16)
			return;
		System.out.println(members[MemberRef].getFullName() + " is Returning " + members[MemberRef].getCurrentHoldings()[HoldingRef].getTitle() + " Print e to cancel");
		input = admin.next();
		if(input.equalsIgnoreCase("e")) {}
		else {
			members[MemberRef].returnHolding(members[MemberRef].getCurrentHoldings()[HoldingRef], todaysDate);
			System.out.println("would you like to reset " + members[MemberRef].getFullName() + " credit?");
		}
		if(again())
			members[MemberRef].resetCredit();
	}
	
	public void printAll(Holding[] holdings) {
		for(int i = 0; i < 15; i++) {
		    if (holdings[i] != null) {
		    	holdings[i].print();  	
		    }
		}
		input = admin.next();
	}
	
	public void printAll(Member[] members) {
		for(int i = 0; i < 15; i++) {
		    if (members[i] != null) {
		    	members[i].print();
		    }
		}
		input = admin.next();
	}
	
	public void PrintSpecific(Holding[] holdings) {
		for(int i = 0; i < 15; i++) {
		    if (holdings[i] != null) {
		    	System.out.println(holdings[i].getID() + " " + holdings[i].getTitle());
		    }
		}
		System.out.println("Print which holding? (print ID e.g. b000002)");
		HoldingRef = getReference(holdings);
		if (HoldingRef == 16)
			return;
		holdings[HoldingRef].print();
		input = admin.next();
	}
	
	public void PrintSpecific(Member[] members) {
		for(int i = 0; i < 15; i++) {
		    if (members[i] != null) {
		    	System.out.println(members[i].getID() + " " + members[i].getFullName());
		    }
		}
		System.out.println("Print which member? (print ID e.g. s000002)");
		MemberRef = getReference(members);
		if (MemberRef == 16)
			return;
		members[MemberRef].print();
		input = admin.next();
	}
}
