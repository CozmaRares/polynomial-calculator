package assignment1;

public class Main {
    public static void main(String[] args) {
        var p1 = new Polynomial("x^2+1");
        var p2 = new Polynomial("-2x");

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.integrate());
        System.out.println(p2.differentiate());

        // String[] strs = {
        // "+2.2x^2",
        // "-2.2x^2",
        // "2.2x^2",
        // "+x^2",
        // "-x^2",
        // "x^2",
        // "+2.2x",
        // "-2.2x",
        // "2.2x",
        // "+x",
        // "-x",
        // "x",
        // };

        // for (String str : strs) {
        // for (String s : str.split("x\\^?"))
        // System.out.println(s + ", " + s.length());

        // System.out.println();
        // }

    }
}
