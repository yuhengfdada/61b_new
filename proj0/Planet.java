import java.lang.Math;
public class Planet {
    private static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        return Math.sqrt(Math.pow(xxPos - p.xxPos,2) + Math.pow(yyPos - p.yyPos,2));
    }
    public double calcForceExertedBy(Planet p){
        double dist = calcDistance(p);
        double m1 = mass;
        double m2 = p.mass;
        return G * m1 * m2 / Math.pow(dist,2);
    }
    public double calcForceExertedByX(Planet p){
        double force = calcForceExertedBy(p);
        double dist = calcDistance(p);
        double distX = p.xxPos - xxPos;
        return force * distX / dist;
    }
    public double calcForceExertedByY(Planet p){
        double force = calcForceExertedBy(p);
        double dist = calcDistance(p);
        double distY = p.yyPos - yyPos;
        return force * distY / dist;
    }
    public double calcNetForceExertedByX(Planet[] pArray){
        double sum = 0;
        for (Planet p : pArray){
            if (!this.equals(p)){
                sum += calcForceExertedByX(p);
            }
        }
        return sum;
    }
    public double calcNetForceExertedByY(Planet[] pArray){
        double sum = 0;
        for (Planet p : pArray){
            if (!this.equals(p)){
                sum += calcForceExertedByY(p);
            }
        }
        return sum;
    }
    public void update(double dt, double fX, double fY){
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += dt * aX;
        yyVel += dt * aY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}
