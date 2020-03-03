public class Planet {
	final static double G = 6.67e-11;
	// final static: create a CONSTANT; must initialize before class loading
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos) + (yyPos-p.yyPos)*(yyPos-p.yyPos));
	} // Math.pow() will result in slower code

	public double calcForceExertedBy(Planet p) {
		return (G*mass*p.mass) / (calcDistance(p)*calcDistance(p));
	}

	public double calcForceExertedByX(Planet p) {
		return calcForceExertedBy(p) * (p.xxPos-xxPos) / calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		return calcForceExertedBy(p) * (p.yyPos-yyPos) / calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] ap) {
        double sumX = 0;
        for (Planet x : ap) {
        	if (this.equals(x)) {continue;}
        	else {sumX = sumX + calcForceExertedByX(x);}
        	}
        return sumX;
	}

	public double calcNetForceExertedByY(Planet[] ap) {
        double sumY = 0;
        for (Planet x : ap) {
        	if (this.equals(x)) {continue;}
        	else {sumY = sumY + calcForceExertedByY(x);}
        	}
        return sumY;
	}

	public void update(double dt, double fX, double fY) {
		xxVel = xxVel + dt*fX/mass;
		yyVel = yyVel + dt*fY/mass;
		xxPos = xxPos + dt*xxVel;
		yyPos = yyPos + dt*yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
	}


}