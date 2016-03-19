public class NBody {

	public static void main(String[] args){

	double T, dt;
	String filename;
	int time = 0;
	int i = 0;


	T = Double.parseDouble(args[0]);
	dt = Double.parseDouble(args[1]);
	filename = args[2];
	In in = new In(filename);
	int N = in.readInt();
	Double R = in.readDouble();
	
	Planet[] arrayPlanet = new Planet[N]; // 

	StdDraw.setScale(-R, R);
	StdDraw.picture(0,0, "images/starfield.jpg"); // draw the background image


	while (i < N){
		arrayPlanet[i] = getPlanet(in);
		i = i + 1;
		}
	for (Planet p : arrayPlanet){
		p.draw();
	}

	while (time < T){
		StdDraw.picture(0,0, "images/starfield.jpg"); // draw the background image

		for (Planet p : arrayPlanet){
			p.setNetForce(arrayPlanet);

		}

		for (Planet p : arrayPlanet){
			p.update(dt);
						p.draw();


		}
		StdDraw.show(10);
		time += dt;
	}
		printPlanets(arrayPlanet, N,  R);


}

	public static void printPlanets(Planet[] arrayPlanet, int N, double R){

		StdOut.printf("d\n", N);
		StdOut.printf("%.2e\n", R);
		for (int q = 0; q < N; q++){
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
				arrayPlanet[q].x, arrayPlanet[q].y, 
				arrayPlanet[q].xVelocity, arrayPlanet[q].yVelocity,
				arrayPlanet[q].mass, arrayPlanet[q].img);
		}
	}

	public static Planet getPlanet(In in){
		double gstartX = in.readDouble(); 
		double gstartY = in.readDouble();
		double gstartxVelocity = in.readDouble();
		double gstartyVelocity = in.readDouble();
		double gstartMass = in.readDouble();
		String gname = in.readString();

		return new Planet(gstartX, gstartY, gstartxVelocity,
				      gstartyVelocity, gstartMass, gname);
			}
	}


