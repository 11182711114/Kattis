package sigma.problem_C;

import java.util.Scanner;

public class GiftTaxRatioCalculator {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// Input
		int numBands = input.nextInt();
		TaxBand[] bands = new TaxBand[numBands];

		for (int i = 0; i < bands.length; i++) {
			bands[i] = new TaxBand(input.nextDouble(), input.nextDouble());
		}
		TaxBand finalTax = new TaxBand();
		finalTax.percent = input.nextDouble();

		int numPersons = input.nextInt();
		Person[] persons = new Person[numPersons];

		for (int i = 0; i < numPersons; i++) {
			persons[i] = new Person(input.nextDouble(), input.nextDouble());
		}

		input.close();

		// Calculation
		for (Person person : persons) {
			double totalGiftReq = 0;
			double giftLeft = person.giftAmount;
			double incomeLeft = person.income;

			for (TaxBand band : bands) {

				if (incomeLeft >= band.size) {
					incomeLeft -= band.size;
					continue;
				} else {
					double tmpBandLeft = (band.size - incomeLeft) * (1 - band.percent/100) ;
					incomeLeft = 0;
					if (tmpBandLeft >= giftLeft) {
						totalGiftReq += band.getReqAmountForGift(giftLeft);
						giftLeft = 0;
						break;
					} else {
						giftLeft -= tmpBandLeft;
						totalGiftReq += band.getReqAmountForGift(tmpBandLeft);
					}
				}
			}

			if (giftLeft > 0) {
				totalGiftReq += finalTax.getReqAmountForGift(giftLeft);
			}

			System.out.format("%.6f%n", totalGiftReq);

		}
	}
}

class TaxBand {

	double percent;
	double size;

	public TaxBand(double size, double percent) {
		this.percent = percent;
		this.size = size;
	}

	public TaxBand(double size) {
		this(size, 0);
	}

	public TaxBand() {
	}

	public double getReqAmountForGift(double i) {
		return i / (1 - (percent / 100));
	}

}

class Person {

	double income;
	double giftAmount;

	public Person(double income, double giftAmount) {
		this.income = income;
		this.giftAmount = giftAmount;
	}

}