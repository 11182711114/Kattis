package sigma.problem_C;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Tester {
	
	
	public static void main(String[] args) {
		int iterations = 500_000;
		for (int j = 0; j < iterations; j++) {
			InputStream old = System.in;
			
			ThreadLocalRandom random = ThreadLocalRandom.current();
	
			List<String> taxBands = new ArrayList<>();
	
			double prevTax = 0;
			taxBands.add(random.nextDouble(1, 1E6 + 0.000_000_1) + " " + 0); // First band starts at zero
			for (int i = 0; i < 19; i++) {
				boolean cont = random.nextInt(0, 21) <= 15;
				if (!cont)
					break;
				
				
				String band = "";
				band += String.format("%.6f", random.nextDouble(1, 1E6 + 0.000_000_1));
				band += " ";
				
				prevTax = random.nextDouble(prevTax, 100.000_000_1);
				band += String.format("%.6f", prevTax);
				
				taxBands.add(band);
			}
			taxBands.add(String.format("%.6f", random.nextDouble(0, 99.999_000_1)));
//			taxBands.add(String.format("%.6f", 0.0));
			
			
			List<String> people = new ArrayList<>();
			
			for (int i = 0; i <= 20; i++) {
				if (i > 0) {
					boolean cont = random.nextInt(0, 100) <= 90;
					if (!cont)
						break;
				}
				
				String person = "";
				
				person += String.format("%.6f", random.nextDouble(0, 1E6+1));
				person += " ";
				person += String.format("%.6f", random.nextDouble(0, 1E6+1));
				
				people.add(person);
			}
	
			String input = "";
			
			input += taxBands.size()-1 + "\n";
			for (String s : taxBands) {
				input += s + "\n";
			}
			
			input += people.size() + "\n";
			for (String s : people) {
				input += s + "\n";
			}
			
			try {
				InputStream newIn = new ByteArrayInputStream( input.getBytes("UTF-8") );
				System.setIn(newIn);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			
			List<String> output = new ArrayList<>();
			
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream oldOut = System.out;
		    System.setOut(new java.io.PrintStream(out));
			
			UKIrelandPC16A.main(new String[0]);
	
			for (String s : out.toString().split("\n")) {
				output.add(s);
			}
			
			try {
				InputStream newIn = new ByteArrayInputStream( input.getBytes("UTF-8") );
				System.setIn(newIn);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			Taxation.main(new String[0]);
			

			List<String> finalOutput = new ArrayList<>();
			boolean failure = false;
			String[] tmpOut = out.toString().split("\n");
			for (int i = 0; i < output.size(); i++) {
				if (!output.get(i).equals(tmpOut[i])) {
					finalOutput.add(String.format("%-20.6f", Double.parseDouble(output.get(i))) + String.format("%-20.6f", Double.parseDouble(tmpOut[i])) + output.get(i).equals(tmpOut[i]));
					failure = true;
				}
			}	

			System.setIn(old);
			System.setOut(oldOut);
			if (failure) {
				System.out.println("Input:");
				System.out.println(input);
				output.forEach(System.out::println);
				System.out.println();
			}
			
			
		}
		System.out.println("Finished");
	}
		

}
