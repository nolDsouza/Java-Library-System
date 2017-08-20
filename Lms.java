package s3600251_A2;

import java.util.Scanner;

public class Lms {

	
	public static void main(String[] args) {
		
		Holding holdings[] = new Holding[15];
		Member members[] = new Member[15];
		DateTime todaysDate = new DateTime();
		Scanner admin = new Scanner(System.in);
		String input = null;
		int aHoldingRef;
		int aMemberRef;
		
		
		holdings[0] = new Book("b000001", "Intro to Java");
		holdings[1] = new Book("b000002", "Learning UML");
		holdings[2] = new Book("b000003", "Design Patterns");
		holdings[3] = new Book("b000004", "Advanced Java");
		holdings[4] = new Video("v000001", "Java 1", 4);
		holdings[5] = new Video("v000002", "Java 2", 6);
		holdings[6] = new Video("v000003", "UML 1", 6);
		holdings[7] = new Video("v000004", "UML 2", 4);
		members[0] = new StandardMember("s000001", "Joe Bloggs");
		members[1] = new StandardMember("s000002", "Jane Smith");
		members[2] = new PremiumMember("p000001", "Fred Bloggs");
		members[3] = new PremiumMember("p000002", "Fred Smith");
		
		while (input != "13") {
		
			getLibraryMenu();
			input = admin.next(); 
			
			
			if (input.equals("1")) {
				for(int i = 0; i < 15; i++) {
				    if (holdings[i] == null) {
				    	System.out.println("Add book or video?");
				    	input = admin.next(); 
				    	if (input.equalsIgnoreCase("book")) {
				    		do {
					    		System.out.println("What is the book name?");
					    		input = admin.next();
					    		validate(input);
				    		} while (validate(input) == false);
				    		holdings[i] = new Book(createId("b", i), input);
				    		System.out.println("Created Book: " + holdings[i].getTitle());
				    		break;
				    	}
				    	else if (input.equalsIgnoreCase("video")) {
				    		do {
					    		System.out.println("What is the video name?");
					    		input = admin.next();
					    		validate(input);
			    			} while (validate(input) == false);
				    		do {
				    			aHoldingRef = getVideoFee();
				    		} while (aHoldingRef != 4 && aHoldingRef != 6);
				    		holdings[i] = new Video(createId("v", i), input, aHoldingRef);
				    		System.out.println("Created Video: " + holdings[i].getTitle());
					    	break;
				    	}
				    }
				    else if(i == 14) {
				    	System.out.println("Cannot add holding, database full");
				    }
			    
			    }
			    do { 
				System.out.println("print e to exit");
			    } while(again()); 
					 
			} //end of case 1
			
			
			
			else if (input.equals("2")) {
				
					for(int i = 0; i < 15; i++) {
					    if (holdings[i] != null) {
					    	System.out.println(i + ". " + holdings[i].toString());
					    }
					}
					System.out.println("Remove which holding? (print ID e.g. b000002)");
					aHoldingRef = getReference(holdings);
					if (aHoldingRef == 16)
						continue;
					System.out.println("Removing " + holdings[aHoldingRef].getTitle() + " Print e to cancel");
					input = admin.next();
					if(input.equalsIgnoreCase("e")) {}
					else {
						holdings[aHoldingRef] = null;
						System.out.println("Removed Holding position "+aHoldingRef + ", print e to exit");	
						again();
					}
				
				continue;
			} //end of case 2
			
			
			
			else if (input.equals("3")) {
				for(int i = 0; i < 15; i++) {
				    if (members[i] == null) {
				    	System.out.println("Standard or Premium?");
				    	input = admin.next(); 
				    	if (input.equalsIgnoreCase("standard")) {
				    		do {
					    		System.out.println("What is the Standard Member name?");
					    		input = admin.next();
					    		validate(input);
				    		} while (validate(input) == false);
				    		members[i] = new StandardMember(createId("s", i), input);
				    		System.out.println("Created Standard Member:" + members[i].getFullName());
				    		break;
				    	}
				    	else if (input.equalsIgnoreCase("premium")) {
				    		do {
					    		System.out.println("What is the Premium Member name?");
					    		input = admin.next();
					    		validate(input);
			    			} while (validate(input) == false);
				    		members[i] = new PremiumMember(createId("p", i), input);
				    		System.out.println("Created Premium Member:" + members[i].getFullName());
					    	break;
				    	}
				    }
				    else if(i == 15) {
				    	System.out.println("Cannot add member, database full");
				    }
				}
				System.out.println("print e to exit");
				do {
					again();
				} while (again());
			} //end of case 3
			
			
			
			else if (input.equals("4")) {
				for(int i = 0; i < 15; i++) {
				    if (members[i] != null) {
				    	System.out.println(members[i].toString());
				    }
				}
				System.out.println("Remove which Member? (print ID e.g. p000002)");
				aMemberRef = getReference(members);
				if (aMemberRef == 16)
					continue;
				System.out.println("Removing " + members[aMemberRef].getFullName() + " Print e to cancel");
				input = admin.next();
				if(input.equalsIgnoreCase("e")) {}
				else {
					members[aMemberRef] = null;
					System.out.println("Removed Holding position "+ aMemberRef + ", print e to exit");	
					again();
				}
			
			continue;
			} //end of case 4
			
			
			else if (input.equals("5")) {
				for(int i = 0; i < 15; i++) {
				    if (members[i] != null) {
				    	System.out.println(members[i].toString());
				    }
				}
				System.out.println("Which Member is borrowing? (print ID e.g. p000002)");
				aMemberRef = getReference(members);
				if (aMemberRef == 16)
					continue;
				
				for(int i = 0; i < 15; i++) {
				    if (holdings[i] != null) {
				    	System.out.println(holdings[i].toString());
				    }
				}
				System.out.println("Borrow which holding? (print ID e.g. b000002)");
				aHoldingRef = getReference(holdings);
				if (aHoldingRef == 16)
					continue;
				System.out.println(members[aMemberRef].getFullName() + " is Borrowing " + holdings[aHoldingRef].getTitle() + " Print e to cancel");
				input = admin.next();
				if(input.equalsIgnoreCase("e")) {}
				else {
					members[aMemberRef].borrowHolding(holdings[aHoldingRef]);
					System.out.println(members[aMemberRef].getFullName() + " successfully borrowed " + holdings[aHoldingRef].toString() + " for $" + holdings[aHoldingRef].getDefaultLoanFee());
				}
				continue;
			} //end of case 5
			
			
			
			else if (input.equals("6")) {
				for(int i = 0; i < 15; i++) {
				    if (members[i] != null && members[i].getCurrentHoldings() != null) {
				    	System.out.println(members[i].toString());
				    }
				}
				System.out.println("Which Member is returning? (print ID e.g. p000002)");
				aMemberRef = getReference(members);
				if (aMemberRef == 16)
					continue;
				aHoldingRef = 0;
				for(int i = 0; i < 3; i++) {
					if (members[aMemberRef].getCurrentHoldings()[i] == null) {
						aHoldingRef++;
					}
					else if (members[aMemberRef].getCurrentHoldings()[i] != null)
						System.out.println(members[aMemberRef].getCurrentHoldings()[i].toString() + " ");
				}
				if(aHoldingRef == 3) {
					System.out.println(members[aMemberRef].getFullName() + " Does not have any hodlings to return");
					continue;
				}
				System.out.println("Return which holding? (print ID e.g. b000002)");
				aHoldingRef = getReference(members[aMemberRef].getCurrentHoldings());
				if (aHoldingRef == 16)
					continue;
				System.out.println(members[aMemberRef].getFullName() + " is Returning " + members[aMemberRef].getCurrentHoldings()[aHoldingRef].getTitle() + " Print e to cancel");
				input = admin.next();
				if(input.equalsIgnoreCase("e")) {}
				else {
					members[aMemberRef].returnHolding(members[aMemberRef].getCurrentHoldings()[aHoldingRef], todaysDate);
					System.out.println("would you like to reset " + members[aMemberRef].getFullName() + " credit?");
				}
				if(again())
					members[aMemberRef].resetCredit();
				continue;
			} //end of case 6
			
			
			
			else if (input.equals("7")) {
				for(int i = 0; i < 15; i++) {
				    if (holdings[i] != null) {
				    	holdings[i].print();  	
				    }
				}
				
				input = admin.next();
				continue;
				
			} //end of case 7
			
			
			
			else if (input.equals("8")) {
				for(int i = 0; i < 15; i++) {
				    if (members[i] != null) {
				    	members[i].print();
				    }
				}
				
				input = admin.next();
				continue;
			} //end of case 8
			
			
			
			else if (input.equals("9")) {
				for(int i = 0; i < 15; i++) {
				    if (holdings[i] != null) {
				    	System.out.println(holdings[i].toString());
				    }
				}
				System.out.println("Print which holding? (print ID e.g. b000002)");
				aHoldingRef = getReference(holdings);
				if (aHoldingRef == 16)
					continue;
				holdings[aHoldingRef].print();
				input = admin.next();
				continue;
			}
			
			
			else if (input.equals("13")) {
				System.exit(0);
			}
			else {
				System.out.println("Invalid input, please try again");
				continue;
			}
		}
       
	}
	
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

	
	public static void getLibraryMenu() {		//Entire method prints a visual menu representation to the console
		System.out.println("Library Management System");
		for(int i = 0; i < 80; i++)
		{
		    System.out.print("-");
		}
		System.out.println();
		System.out.println("1.  Add Holding\n" + "2.  Remove Holding\n" + "3.  Add Member\n" + "4.  Remove Member\n" + "5.  Borrow Holding\n"
		+ "6.  Return Holding\n" + "7.  Print all Holdings\n" + "8.  Print all Members\n" + "9.  Print specific Holding\n" + "10. Print specific Member"
		+ "11. Save to file\n" + "12. Load from file\n" + "13. Exit\n");
		
		for(int i = 0; i < 80; i++)
		{
		    System.out.print("-");
		}
		System.out.println("\nEnter an option:");
	}
	
	
	public static String createId(String refChar, int i) {
		i++;
		if (i > 10)
			return (refChar + "0000" + i);
		return (refChar + "00000" + i);
	}

	public static boolean validate(String tempString)  {
		if (tempString.length() >= 1)
			return true;
		else
			System.out.println("Name must be at least one character long");
		return false;
	}
	
	public static int getVideoFee() {
		int loanFee;
		do {
			System.out.println("Set default loan fee to $4 or $6? (input response)");
			Scanner chosen = new Scanner(System.in);
			loanFee = chosen.nextInt();
			if (loanFee == 4 ||  loanFee == 6)
				return loanFee;
			else
				System.out.println("Invalid price, must be $4 or $6");
			} while (loanFee == 4 ||  loanFee == 6);
		return 0;
	}
	
	
	public static int getReference(Holding[] holdings) {
		String input;
		boolean invalid = true;
		do {
			input = getOutput();
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
	
	public static int getReference(Member[] members) {
		String input;
		boolean invalid = true;
		do {
			input = getOutput();
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
	
}
