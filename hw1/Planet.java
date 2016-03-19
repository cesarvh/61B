public class Planet {
	public double x, y, xVelocity, yVelocity, mass, xNetForce, yNetForce, xAccel, yAccel;
	public String img;
	public double gConstant = 6.67e-11;
	public double netForce = 0;


	public Planet(double startX, double startY, double startxVelocity,
				  double startyVelocity, double startMass, String name){
		x = startX;
		y = startY;
		xVelocity = startxVelocity;
		yVelocity = startyVelocity;
		mass = startMass;
		img = name;
	}
	public double calcDistance(Planet planet2){ //Calculates distance
		double distance = 0;

		double xd = (planet2.x - this.x) * (planet2.x - this.x);
		double yd = (planet2.y - this.y) * (planet2.y - this.y);

		double radius = xd + yd;

		return Math.sqrt(radius);

		// return distance;
	}

	public double calcPairwiseForce(Planet p2){
		//should call calcDistance
		// F = G * m1 * m2 / r2
		double force = 0;
		double r = 0;

		r = calcDistance(p2) * calcDistance(p2) ;

		force = (gConstant * (this.mass * p2.mass) / r);
		return force;
	}

	public double calcPairwiseForceX(Planet p2){// Fx = F * dx/r
		double dx = 0;
		double fX = 0;
		double r = 0;
		double forceX = 0;

		dx = p2.x - this.x;
		forceX = calcPairwiseForce(p2); 
		r = calcDistance(p2);
		fX = (forceX * dx) / r;

		return fX;
	} 

	public double calcPairwiseForceY(Planet p2){// Fx = F * dx/r
		double dy = 0;
		double fY = 0;
		double r = 0;
		double forceY = 0;

		dy = p2.y - this.y;
		forceY = calcPairwiseForce(p2); 
		r = calcDistance(p2);
		fY = (forceY * dy) / r;

		return fY;
	}

	public void setNetForce(Planet[] planets){

		int arraySize = planets.length;
		xNetForce = 0;
		yNetForce = 0;
		int	i = 0;

		while (i < arraySize){
					Planet current = planets[i];

			if (current == this){
				i = i + 1;
			}
			else {
				xNetForce =  xNetForce + calcPairwiseForceX(planets[i]);
				yNetForce = yNetForce + calcPairwiseForceY(planets[i]);
				netForce = netForce + xNetForce + yNetForce;
				i = i + 1;

			}
		}
	}

	public void draw(){ //Should draw a planet in approx position
		StdDraw.picture(this.x, this.y, "images/" + img);
	}

	public void update(double dt){
		xAccel = xNetForce / this.mass;
		yAccel = yNetForce / this.mass;

		xVelocity = xVelocity + xAccel * dt;
		yVelocity = yVelocity + yAccel * dt;

		x = x + (dt * xVelocity);
		y = y + (dt * yVelocity);

		xNetForce = 0.0;
		yNetForce = 0.0;

	}
}