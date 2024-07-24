package tron2joueurs;


public class Grille  {
    int VIDE = 0;
    int COLONNES;    
    int LIGNES;
    Joueur joueurRouge;
    Joueur joueurBleu;
    Mur mur;
    int[][] tab; // tableau pour stocker l'état de chaque case de la grille
   
   
       // constructeur pour initialiser la grille et les positions des joueurs
        public Grille ( int COLONNES, int LIGNES,Joueur joueurRouge, Joueur joueurBleu,Mur mur){ 
        this.COLONNES = COLONNES;
        this.LIGNES = LIGNES;
        this.tab = new int[COLONNES][LIGNES];
        this.joueurRouge=joueurRouge;
        this.joueurBleu=joueurBleu;
        this.mur=mur;
        initializeGrille();
        }
           
           // Initialiser la grille avec les cases des joueurs, les cases vides, et les murs
        public void initializeGrille() {
            for (int j = 0; j < LIGNES; j++) {
                for (int i = 0; i < COLONNES; i++) {
                if (i == joueurRouge.x && j == joueurRouge.y) {
                tab[i][j] = joueurRouge.CASEJOUEUR;
                } else if (i == joueurBleu.x && j == joueurBleu.y) {
                    tab[i][j] = joueurBleu.CASEJOUEUR;
                }else if (mur.checkMur(i, j)) {  // Ajout des murs aux emplacements spécifiés
                    tab[i][j] = mur.CASEMUR;
                } else {
                    tab[i][j] = VIDE;
                }
                }
            }
        } 
               
   
// Compte le nombre de cases de trace appartenant au joueur spécifié dans la grille
    public int nbTrace(Joueur joueur) {
        int count = 1;
        for (int j = 0; j < LIGNES; j++) {
            for (int i = 0; i < COLONNES; i++) {
                if (tab[i][j] == joueur.CASETRACE) {
                    count++;
                }
            }
        }
        return count;
    }

    }
    
   