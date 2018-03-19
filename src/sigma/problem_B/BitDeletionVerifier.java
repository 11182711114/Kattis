package sigma.problem_B;

import java.util.Scanner;

public class BitDeletionVerifier {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		int flips = Integer.parseInt(input.nextLine());
		
		String origBits = input.nextLine();
		String newBits = input.nextLine();
		
		boolean swapped = flips % 2 != 0;
		
		// Not the prettiest solution for if the bits are swapped, I would have 
		// just checked if (NOT newBits) equals origBits
		if (!swapped) {
			if (origBits.equals(newBits))
				System.out.println("Deletion succeeded");
			else
				System.out.println("Deletion failed");
		} else {
			for (int i = 0; i < origBits.length(); i++) {
				if (origBits.charAt(i) != newBits.charAt(i)) {
					if (i == origBits.length()-1)
						System.out.println("Deletion succeeded");	
					continue;
				} else {
					System.out.println("Deletion failed");
					break;
				}					
			}		
		}
		
		input.close();
	}

}
