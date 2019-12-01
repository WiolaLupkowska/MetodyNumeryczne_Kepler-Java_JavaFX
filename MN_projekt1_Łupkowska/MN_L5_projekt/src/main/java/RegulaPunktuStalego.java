import java.util.ArrayList;

public class RegulaPunktuStalego {

    private Funkcja f;

    public RegulaPunktuStalego(Funkcja f) { //tym ustawiam funkcję jakiej miejsc zerowych chce szukac
        this.f = f;
    }

    public double liczEa (double xrNew, double xrOld){
        return Math.abs((xrNew-xrOld)/xrNew)*100;
    }

    ArrayList<Double> listEa = new ArrayList<Double>();
    ArrayList<Double> listXr = new ArrayList<Double>();
    LiczFunkcje licz = new LiczFunkcje();
    double x0= 0;
    public double solver(double zadanyBlad, double T, double ekscentrycznosc, double t, double a){ //bylo jeszcze xrDokladne

        double xrOld=0; // tu bylo xrDokladne
        double xr=x0;
        double xrNew=0;
        double ea=1;

        while(ea>zadanyBlad){
            xrOld=xr;
            xr= licz.funkcjaZ(xr, T, ekscentrycznosc,t)+xr;  //przyblizenie rozwiazania
            xrNew=xr;
            ea= liczEa(xrNew,xrOld);
            listEa.add(ea);
            listXr.add(xr);
        }
        return listXr.get(listXr.size()-1);
    }

    public ArrayList trajektoriaX( double blad, double T, double ekscentrycznosc, double a){
        a=a*150*Math.pow(10,6);
        //wyliczoneE=solver(double x0, double zadanyBlad, double T, double ekscentrycznosc, int t)
        ArrayList<Double> trajektoriaX = new ArrayList<Double>();
        double wyliczoneE;
        for (int t=0; t<T;t++){ //zmieniam czas od 1 dnia do 365
            wyliczoneE=solver(blad, T, ekscentrycznosc,t,a);
            double x=licz.trajektoriaX(wyliczoneE,ekscentrycznosc,a);
            trajektoriaX.add(x);
        }
        return trajektoriaX;
    }

    public ArrayList trajektoriaY( double blad, double T, double ekscentrycznosc, double a){
        a=a*150*Math.pow(10,6);
        //wyliczoneE=solver(double x0, double zadanyBlad, double T, double ekscentrycznosc, int t)
        ArrayList<Double> trajektoriaY = new ArrayList<Double>();
        double wyliczoneE;
        for (int t=0; t<T;t++){ //zmieniam czas od 1 dnia do 365
            wyliczoneE=solver(blad, T, ekscentrycznosc,t,a);
            double x=licz.trajektoriaY(wyliczoneE,ekscentrycznosc, a);
            trajektoriaY.add(x);
        }
        return trajektoriaY;
    }


}
