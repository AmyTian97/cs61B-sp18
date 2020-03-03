public class NBody {
	public static void main(String[] args) {
		/** Drawing the initial universe state */
		/* Collect all needed input */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double r = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		/* Drawing the background */
		/* Sets up the universe so it goes from -2.50e+11, -2.50e+11 up to 2.50e+11, 2.50e+11 */
		StdDraw.setScale(-r, r);
		/* Clears the drawing window. */
		StdDraw.clear();
		/* Draw starfield as the background */
		StdDraw.picture(0, 0, "./images/starfield.jpg");
		/* Drawing all of the planets */
		for (Planet p : planets) {
			p.draw();
		}


		/** Creating a animation */
		StdDraw.enableDoubleBuffering();

		double t = 0;
		while (t <= T) {
			double[] xForces = new double[5],
					 yForces = new double[5];

			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			/* Draw starfield as the background */
			StdDraw.clear();
		    StdDraw.picture(0, 0, "./images/starfield.jpg");
		    /* Drawing all of the planets */
			for (Planet p : planets) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			t = t + dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}

	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int n = in.readInt();
		double r = in.readDouble();
		return r;
	}

	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int n = in.readInt();
		double r = in.readDouble();

		Planet[] planets = new Planet[n];
		for (int i = 0; i < planets.length; i++) {
			planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
				                    in.readDouble(), in.readDouble(), in.readString());
		}
		return planets;
	}
}