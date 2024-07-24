package tron2joueurs;
import java.awt.*;  

public class Joueur {
    Color couleur;
    int x, y; //colonnes,lignes du joeurs
    boolean mort;
    int CASEJOUEUR;
    int CASETRACE;
    Image moto; //déclare des variables

    public Joueur(Color couleur,int x, int y,  int CASEJOUEUR, int CASETRACE, Image moto) {
        this.couleur = couleur;
        this.x = x;
        this.y = y;
        this.CASEJOUEUR = CASEJOUEUR; //la case joueur représentée correspond à un chiffre
        this.CASETRACE = CASETRACE;   //la case trace représentée coorespond à un chiffre
        this.moto = moto; // Initialise des valeurs
    }

}