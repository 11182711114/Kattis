package sigma.problem_C;

import java.io.PrintWriter;
import java.util.Scanner;

// From: https://github.com/RonCruz/CompetitiveProgramming/blob/d4b3227ca292be202ec95b741c19b08b7ec625c8/RandomCollection/UKIrelandPC16A.java
public class UKIrelandPC16A {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int B = in.nextInt();
        double[] bandSize = new double[B];
        double[] percentage = new double[B];

        for(int i = 0; i < B; ++i) {
            bandSize[i] = in.nextDouble();
            percentage[i] = 1 - in.nextDouble() / 100;
        }

        double percentageRest = 1 - in.nextDouble() / 100;
        int F = in.nextInt();
        double[] friendEarnings = new double[F];
        double[] moneyAfterTax = new double[F];

        for(int i = 0; i < F; ++i) {
            friendEarnings[i] = in.nextDouble();
            moneyAfterTax[i] = in.nextDouble();
        }

        for(int i = 0; i < F; ++i) {
            double curr = 0;
            double goal = moneyAfterTax[i];
            double earnings = friendEarnings[i];
            int counter = -1;

            while(++counter < B) {
                earnings -= bandSize[counter];

                if(earnings <= 0) {
                    if(earnings + (goal / percentage[counter]) < 0) {
                        curr += goal / percentage[counter];
                        goal = 0;
                        break;
                    } else {
                        curr -= earnings;
                        goal += earnings * percentage[counter];
                        earnings = 0;
                    }
                }
            }

            if(goal > 0) {
                curr += goal / percentageRest;
            }

            out.printf("%.6f\n", curr);
        }

        out.close();

    }
}