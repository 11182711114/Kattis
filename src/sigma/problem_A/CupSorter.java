package sigma.problem_A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CupSorter {
	
	
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numCups = Integer.parseInt(input.nextLine());
		
		ArrayList<Cup> cups = new ArrayList<>(numCups+1);
		
		for (int i = 0; i < numCups; i++) {
			if (input.hasNextLine())
				cups.add(Cup.parseCup(input.nextLine()));
		}
		
		Collections.sort(cups);
		
		cups.forEach(cup -> System.out.println(cup.getColor()));
		
		input.close();
	}
	
	

}


class Cup implements Comparable<Cup> {
	
	private int radius;
	private String color;
	
	public Cup(int rad, String color) {
		this.radius = rad;
		this.color = color;
	}
	
	
	public static Cup parseCup(String input) {
		int radius = 0;
		String color;
		
		String[] splitInput = input.split(" ");
		
		try {
			radius = Integer.parseInt(splitInput[0])/2;
			color = splitInput[1];
		} catch (NumberFormatException e) {
			radius = Integer.parseInt(splitInput[1]);
			color = splitInput[0];
		}
		
		return new Cup(radius, color);
	}

	public String getColor() {
		return color;
	}

	@Override
	public int compareTo(Cup other) {
		return Integer.compare(this.radius, other.radius);
	}
	
	
}