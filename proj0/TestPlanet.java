/**
 * Test that creates two planets and prints out the pairwise force between them
*/
public class TestPlanet {
	public static void main(String[] args) {
		checkPlanetClass();
	}

    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  expected    Expected double
     *  @param  actual      Double received
     *  @param  label       Label for the 'test' case
     *  @param  eps         Tolerance for the double comparison.
     */
    private static void checkEquals(double expected, double actual, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
	        System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
		} else {
			System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
		}
    }

    /**
     *  Checks Planet class to make sure they are calculating pairwise force correctly.
     */
	private static void checkPlanetClass() {
        System.out.println("Checking Planet class...");

        double xxPos_1 = 1.0,
               yyPos_1 = 2.0,
               xxVel_1 = 3.0,
               yyVel_1 = 4.0,
               mass_1 = 5.0,
               xxPos_2 = 2.0,
               yyPos_2 = 3.0,
               xxVel_2 = 4.0,
               yyVel_2 = 5.0,
               mass_2 = 6.0;

        String imgFileName_1 = "jupiter.gif",
        	   imgFileName_2 = "sun.gif";

        double r_sq = (xxPos_1-xxPos_2)*(xxPos_1-xxPos_2) + (yyPos_1-yyPos_2)*(yyPos_1-yyPos_2);
        double G = 6.67e-11;
        double pairwiseForce = 	G*mass_1*mass_2 / r_sq;

        Planet p1 = new Planet(xxPos_1, yyPos_1, xxVel_1, yyVel_1, mass_1, imgFileName_1);
        Planet p2 = new Planet(xxPos_2, yyPos_2, xxVel_2, yyVel_2, mass_2, imgFileName_2);

        checkEquals(pairwiseForce, p1.calcForceExertedBy(p2), "pairwise Force", 0.01);

	}
}