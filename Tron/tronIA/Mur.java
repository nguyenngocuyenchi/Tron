package tronIA;

public class Mur{
    int[][] tabMur;
    int CASEMUR; // Déclare des variables

    public Mur(int[][] tabMur,int CASEMUR){
        this.tabMur=tabMur; // Initialise l'attribut tabMur avec les emplacements passés en paramètre
        this.CASEMUR=CASEMUR;
    }

    public boolean checkMur(int x ,int y) {
        for (int i=0; i<tabMur.length;i++){
            if (tabMur[i][0]==x && tabMur[i][1]==y) { // Vérifie si les coordonnées du mur correspondent à celles passées en paramètre
                return true;
            }
        }
        return false; // Renvoie false si il ne faut pas mettre de mur 
    }

}