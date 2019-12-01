import com.sun.javafx.geom.BaseBounds;

import java.util.ArrayList;

public class Main {



    public static void main(String[] args) {


        RegulaPunktuStalego regulaPunktuStalego = new RegulaPunktuStalego(new LiczFunkcje());
        MetodaStycznych metodaStycznych = new MetodaStycznych((new LiczFunkcje()));
        MetodaSiecznych metodaSiecznych = new MetodaSiecznych((new LiczFunkcje()));
        Bisekcja bisekcja = new Bisekcja((new LiczFunkcje()));


        //double solutionRegulaPunktuStalego = regulaPunktuStalego.solver(0,0.001, 365, 0.0167,0);
        //System.out.println(solutionRegulaPunktuStalego+ " E punkt stały");
        ArrayList xPunkty=regulaPunktuStalego.trajektoriaX( 0.001,365 ,0.0167 ,1);
        ArrayList yPunkty=regulaPunktuStalego.trajektoriaY(0.001,365 ,0.0167, 1 );

        System.out.println(xPunkty);
        System.out.println(yPunkty);
        System.out.println();

//
//        double solutionMetodaStycznych = metodaStycznych.solver(0  , 0.001, 365, 0.0167,0);
//        System.out.println(solutionMetodaStycznych + " E styczne");
//        double xStyczne=metodaStycznych.trajektoriaX(365, solutionMetodaStycznych, 0.0167);
//        double yStyczne=metodaStycznych.trajektoriaY(365, solutionMetodaStycznych, 0.0167);
//        System.out.println(xStyczne);
//        System.out.println(yStyczne);
//        System.out.println();
//
//
//        double solutionMetodaSiecznych = metodaSiecznych.solver(0.1 , 0.001, 365, 0.0167,0);
//        System.out.println(solutionMetodaSiecznych + " E sieczne");
//        double xSieczne=metodaSiecznych.trajektoriaX(365, solutionMetodaSiecznych, 0.0167);
//        double ySieczne=metodaSiecznych.trajektoriaY(365, solutionMetodaSiecznych, 0.0167);
//        System.out.println(xSieczne);
//        System.out.println(ySieczne);
//        System.out.println();
//
//
//        double solutionBisekcja = bisekcja.solver(-100000000,1000000000,0.001, 365, 0.0167,0);
//        System.out.println(solutionBisekcja + " E bisekcja");
//        double xBisekcja=bisekcja.trajektoriaX(365, solutionBisekcja, 0.0167);
//        double yBisekcja=bisekcja.trajektoriaY(365, solutionBisekcja, 0.0167);
//        System.out.println(xBisekcja);
//        System.out.println(yBisekcja);
//
//


//        System.out.println("lista xr dla reguly punktu stalego 'zadania do wykonania' x0=1, m=0.5");
//        double solutionRegulaPunktuStalegoA = regulaPunktuStalego.solverA(1,0.5,5, 0.56714329);
//        System.out.println("lista xr dla reguly punktu stalego 'zadania do wykonania' x0=1, m=2");
//        double solutionRegulaPunktuStalegoA1 = regulaPunktuStalego.solverA(1,2,5, 0.56714329);
//        System.out.println();
//        double solutionRegulaPunktuStalegoB = regulaPunktuStalego.solverB(0,0.56714329, 0.001);
//        System.out.println(solutionRegulaPunktuStalegoB);
//
//        double solutionMetodaStycznych = metodaStycznych.solver(0,   0.56714329, 0.001);
//        System.out.println(solutionMetodaStycznych + "solution styczne");
//        System.out.println();
//
//        double solutionMetodaSiecznych = metodaSiecznych.solver(0.1,   0.56714329, 0.001);
//        System.out.println(solutionMetodaSiecznych + "solution sieczne");
//        System.out.println();
//
//        double solutionBisekcja = bisekcja.solver(0,2,0.56714329,0.001);
//        System.out.println(solutionBisekcja + "solution bisekcja");
//        System.out.println();
//
//        System.out.println(regulaPunktuStalego.listEt + "punkt et");
//        System.out.println(metodaSiecznych.listEt + "sieczne et");
//        System.out.println(metodaStycznych.listEt + " styczne et");
//        System.out.println(bisekcja.listEt+ "bisekcja et");



    }

}
