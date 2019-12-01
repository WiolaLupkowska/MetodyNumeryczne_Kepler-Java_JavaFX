import java.util.ArrayList;

public class MetodaStycznych {

    private Funkcja f;
    public MetodaStycznych(Funkcja f){
        this.f = f;
    }

    public double liczEa (double xrNew, double xrOld){
        return Math.abs((xrNew-xrOld)/xrNew)*100;
    }

    ArrayList<Double> listEa = new ArrayList<Double>();
    ArrayList<Double> listXr = new ArrayList<Double>();
    LiczFunkcje licz = new LiczFunkcje();
    double x0=0;
    public double solver(double zadanyBlad, double T, double ekscentrycznosc, double t, double a){


        double xrOld=0;
        double xr=x0;
        double xrNew=0;
        double ea=1;

        while(ea>zadanyBlad){
            xrOld=xr;
            xr= xr-(licz.funkcjaZ(xr, T, ekscentrycznosc,t)/(-Math.exp(-xr)-1));  //przyblizenie rozwiazania
            xrNew=xr;
            double et = Math.abs(xr-0.56714329)/0.56714329;
            ea= liczEa(xrNew,xrOld);
            listEa.add(ea);
            listXr.add(xr);
        }
        //System.out.println(listEa + "  błąd Ea, styczne");
        //System.out.println();
        //System.out.println("Miejsce zerowe z styczne:");
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
