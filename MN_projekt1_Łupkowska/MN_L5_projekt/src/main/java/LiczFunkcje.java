public class LiczFunkcje implements Funkcja {

// wyznaczam E dla zadanego dnia w roku (T)
    @Override
    public double funkcjaZ(double E, double T, double ekscentrycznosc, double t) {
        double M=(2*Math.PI/T)*t;
        double wyliczoneE=M+ekscentrycznosc*Math.sin(E)-E;
        return wyliczoneE;
    }


    @Override
    public double trajektoriaX(double wyliczoneE, double ekscentrycznosc, double a ) {
        a=a*150*Math.pow(10,6);
        double x=a*Math.cos(wyliczoneE - ekscentrycznosc);
        return x;
    }

    @Override
    public double trajektoriaY(double wyliczoneE, double ekscentrycznosc, double a) {
        a=a*150*Math.pow(10,6);
        double y= a*Math.sqrt(1-Math.pow(ekscentrycznosc,2))*Math.sin(wyliczoneE);
        return y;
    }
}
