public class NBody {
    public static double readRadius(String input) {
        In in = new In(input);
        in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String input) {
        In in = new In(input);
        int n = in.readInt();
        in.readDouble();
        Planet[] pArray = new Planet[n];
        for (int i=0; i<n; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            pArray[i] = new Planet(xP,yP,xV,yV,m,img);
        }
        return pArray;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] pArray = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        StdDraw.enableDoubleBuffering();
        double time = 0;
        int n = pArray.length;
        while (time < T){
            double[] xForces = new double[n];
            double[] yForces = new double[n];
            for (int i = 0; i < n; i++) {
                xForces[i] = pArray[i].calcNetForceExertedByX(pArray);
                yForces[i] = pArray[i].calcNetForceExertedByY(pArray);
            }
            for (int i = 0; i < n; i++) {
                pArray[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : pArray){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", pArray.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < pArray.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    pArray[i].xxPos, pArray[i].yyPos, pArray[i].xxVel,
                    pArray[i].yyVel, pArray[i].mass, pArray[i].imgFileName);
        }
    }
}
