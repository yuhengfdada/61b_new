public class test {
    public static int haha = 0;
    public int nonstatic = 0;
    public void nohaha(){
        this.haha += 1;
    }

    public static void doNothing(){
        haha = 0;
        nonstatic = 0;
        System.out.println("I'm doing nothing");
    }
    public static void main(String[] args) {
        test x = new test();
        test.doNothing();
        x.doNothing();
        System.out.println(test.haha);
        x.nohaha();
        System.out.println(x.haha);
        System.out.println(test.haha);

        test y = new test();
        System.out.println(y.haha);
        y.nohaha();
        System.out.println(x.haha);

    }
}