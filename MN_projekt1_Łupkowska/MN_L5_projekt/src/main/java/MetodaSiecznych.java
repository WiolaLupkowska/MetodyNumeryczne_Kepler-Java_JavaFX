import java.util.ArrayList;

public class MetodaSiecznych {

    private Funkcja f;
    public MetodaSiecznych(Funkcja f){
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


        double xrOld=1;
        double xrOlder;
        double xr=x0;
        double xrNew=0;
        double ea=1;
        double xminusjeden=0;

        while(ea>zadanyBlad){
            xrOlder=xrOld;
            xrOld=xr;
            xr= xr-((licz.funkcjaZ(xr, T, ekscentrycznosc,t)*(xrOlder-xr))/(licz.funkcjaZ(xrOlder, T, ekscentrycznosc,t)-licz.funkcjaZ(xr, T, ekscentrycznosc,t)));  //przyblizenie rozwiazania
            xrNew=xr;
            ea= liczEa(xrNew,xrOld);
            listEa.add(ea);
            listXr.add(xr);
        }

        return listXr.get(listXr.size()-1);

    }

    public ArrayList trajektoriaX( double blad, double T, double ekscentrycznosc, double a){
        a=a*150*Math.pow(10,6);
        ArrayList<Double> trajektoriaX = new ArrayList<Double>();
        double wyliczoneE;
        for (int t=0; t<T;t++){
            wyliczoneE=solver(blad, T, ekscentrycznosc,t,a);
            double x=licz.trajektoriaX(wyliczoneE,ekscentrycznosc,a);
            trajektoriaX.add(x);
        }
        return trajektoriaX;
    }

    public ArrayList trajektoriaY( double blad, double T, double ekscentrycznosc, double a){
        a=a*150*Math.pow(10,6);
        ArrayList<Double> trajektoriaY = new ArrayList<Double>();
        double wyliczoneE;
        for (int t=0; t<T;t++){
            wyliczoneE=solver(blad, T, ekscentrycznosc,t,a);
            double x=licz.trajektoriaY(wyliczoneE,ekscentrycznosc, a);
            trajektoriaY.add(x);
        }
        return trajektoriaY;
    }


}
