package fr.iut2.Jeu3;

public class Resultat {

    private int nombreVictoire = 0;
    private int nombreEgalite = 0;
    private int nombreDefaite = 0;

    public int getNombreVictoire() {
        return nombreVictoire;
    }

    public int getNombreEgalite() {
        return nombreEgalite;
    }

    public int getNombreDefaite() {
        return nombreDefaite;
    }


    //////////////////////////////////
    public void addGagne() {
        nombreVictoire++;
    }

    public void addPerdu() {
        nombreDefaite++;
    }

    public void addEgalite() {
        nombreEgalite++;
    }
}
