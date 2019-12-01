import java.util.ArrayList;

public class Bisekcja {

    private Funkcja f;
    public Bisekcja(Funkcja f){this.f=f;}

    public double liczEa (double xrNew, double xrOld){
        return Math.abs((xrNew-xrOld)/xrNew)*100;
    }

    ArrayList<Double> listEa = new ArrayList<Double>();
    ArrayList<Double> listXr = new ArrayList<Double>();
    LiczFunkcje licz = new LiczFunkcje();
    double xl= -10000;
    double xu=  10000;

    public double solver(double zadanyBlad, double T, double ekscentrycznosc, double t, double a){

        double xrOld=10;
        double xr=0;
        double xrNew=0;
        double ea=1;

        while(ea>zadanyBlad){
            xrOld=xr;
            xr= (xl-xu)/2;
            xrNew=xr;
            ea= liczEa(xrNew,xrOld);
            listEa.add(ea);
            listXr.add(xr);


            if (licz.funkcjaZ(xl, T, ekscentrycznosc, t) * licz.funkcjaZ(xu, T, ekscentrycznosc, t)<0) {
                xu = xr;
            }else if (licz.funkcjaZ(xu, T, ekscentrycznosc,t)*licz.funkcjaZ(xu, T, ekscentrycznosc,t)>0) {
                xl = xr;
            }else if (licz.funkcjaZ(xl, T, ekscentrycznosc,t)*licz.funkcjaZ(xr, T, ekscentrycznosc,t)==0){
                System.out.println("MAM PIERWIASTEK");
                //break;
            }
        }
        //System.out.println(listEa + "bisekcja błąd ea");
        return listXr.get(listXr.size()-1);
    }

    public ArrayList trajektoriaX(double blad, double T, double ekscentrycznosc, double a){
        a=a*150*Math.pow(10,6);
        //wyliczoneE=solver(double x0, double zadanyBlad, double T, double ekscentrycznosc, int t)
        ArrayList<Double> trajektoriaX = new ArrayList<Double>();
        double wyliczoneE;
        for (int t=0; t<T;t++){ //zmieniam czas od 1 dnia do 365
            wyliczoneE=solver( blad, T, ekscentrycznosc,t,a);
            double x=licz.trajektoriaX(wyliczoneE,ekscentrycznosc,a);
            trajektoriaX.add(x);
        }
        return trajektoriaX;
    }

    public ArrayList trajektoriaY(double blad, double T, double ekscentrycznosc, double a){
        a=a*150*Math.pow(10,6);
        //wyliczoneE=solver(double x0, double zadanyBlad, double T, double ekscentrycznosc, int t)
        ArrayList<Double> trajektoriaY = new ArrayList<Double>();
        double wyliczoneE;
        for (int t=0; t<T;t++){ //zmieniam czas od 1 dnia do 365
            wyliczoneE=solver( blad, T, ekscentrycznosc,t,a);
            double x=licz.trajektoriaY(wyliczoneE,ekscentrycznosc, a);
            trajektoriaY.add(x);
        }
        return trajektoriaY;
    }
}
