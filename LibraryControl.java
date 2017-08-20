package s3600251_A2;

import java.util.Scanner;

public class LibraryControl {

	public static void main(String[] args) {
		
		Holding holdings[] = new Holding[15];
		Member members[] = new Member[15];
		DateTime todaysDate = new DateTime();
		Scanner admin = new Scanner(System.in);
		Library lms = new Library();
		int input;
		
		
		
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
		char l = 76;
		System.out.println(l);
		if (holdings[1].getTitle().charAt(0) == l)
		System.out.println(holdings[1].getTitle().charAt(0));
		
		do
		{
			lms.getLibraryMenu();

			while (!admin.hasNextInt()) {
				lms.getLibraryMenu();
				admin.next(); // this is important!
			}
			input = admin.nextInt();

			System.out.println();

			switch (Integer.valueOf(input)) {

			case 1:
				lms.addHolding(holdings);
				break;

			case 2:
				lms.removeHolding(holdings);
				break;

			case 3:
				lms.addMember(members);
				break;

			case 4:
				lms.removeMember(members);
				break;

			case 5:
				lms.borrowHolding(holdings, members);
				break;

			case 6:
				lms.returnHolding(holdings, members);
				break;

			case 7:
				lms.printAll(holdings);;
				break;
				
			case 8:
				lms.printAll(members);;
				break;

			case 9:
				lms.PrintSpecific(holdings);
				break;

			case 10:
				lms.PrintSpecific(members);
				break;

			case 11:
				System.out.print("11");
				break;
			
			case 12:
				System.out.print("12");
				break;
				

			default:
				System.out.println("Error - invalid selection!");
			}
			System.out.println();

		}
		while (input != 11);
		
		

	}

}
