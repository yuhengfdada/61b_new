public class debug {
    public static void main(String[] args) {
        StdDraw.setScale(-2.5e+11, 2.5e+11);

        /* Clears the drawing window. */
        StdDraw.clear();

        /* Stamps three copies of advice.png in a triangular pattern. */
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /* Shows the drawing to the screen, and waits 2000 milliseconds. */
        StdDraw.show();
        Planet p = new Planet(1.4960e+11, 0.0000e+00, 0.0000e+00, 2.9800e+04, 5.9740e+24, "earth.gif");
        p.draw();
    }
}
