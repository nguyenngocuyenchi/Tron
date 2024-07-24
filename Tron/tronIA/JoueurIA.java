package tronIA;
import java.util.Random;


// Note : Le déplacement réel de l'IA n'est pas déclenché automatiquement dans ce code. 
// L'IA ne se déplace pas automatiquement, il faut cliquer sur une touche pour qu'elle puisse avancer.

public class JoueurIA {
    int[][] casesVides;
    int count;
    Grille grille;
    Graphique graphique;
    Configuration configuration;
    Joueur joueurRouge;
    Joueur joueurBleu;// Déclare des variables


    public JoueurIA(Grille grille,Graphique graphique,Configuration configuration,Joueur joueurRouge,Joueur joueurBleu) {
        this.casesVides = new int[4][2];
        this.count=0;
        this.grille=grille;
        this.graphique=graphique;
        this.configuration=configuration;
        this.joueurRouge=joueurRouge;
        this.joueurBleu=joueurBleu;// Initialise des valeurs
    }


    public void calculerCasesIa(Joueur joueur) {
        count = 0; 
        // Une case est vides et dans les limites de la grille
        // Incrémente count à chaque fois qu'une case vide est trouvée et stocke ses coordonnées dans le tableau casesVides.
        if (joueur.x - 1 >= 0 && grille.tab[joueur.x - 1][joueur.y] == grille.VIDE) {
            casesVides[count][0] = joueur.x - 1;
            casesVides[count][1] = joueur.y;
            count++;
        }
        if (joueur.x + 1 < grille.COLONNES && grille.tab[joueur.x + 1][joueur.y] == grille.VIDE) {
            casesVides[count][0] = joueur.x + 1;
            casesVides[count][1] = joueur.y;
            count++;
        }
        if (joueur.y - 1 >= 0 && grille.tab[joueur.x][joueur.y - 1] == grille.VIDE) {
            casesVides[count][0] = joueur.x;
            casesVides[count][1] = joueur.y - 1;
            count++;
        }
        if (joueur.y + 1 < grille.LIGNES && grille.tab[joueur.x][joueur.y + 1] == grille.VIDE) {
            casesVides[count][0] = joueur.x;
            casesVides[count][1] = joueur.y + 1;
            count++;
        }
    }
    

    // Fait avancer l'IA en choisissant aléatoirement l'une des cases vides adjacentes au joueur spécifié.
    // Si count est supérieur est 0 ça veut dire que des cases vides sont disponibles
    // Choisit aléatoirement une case parmi les cases vides.
    public void deplacerIa(Joueur joueur) {
        calculerCasesIa(joueur);
        if (count > 0) {
            Random random = new Random();
            int caseRandom = random.nextInt(count);
            int iaX = casesVides[caseRandom][0];
            int iaY = casesVides[caseRandom][1];
            grille.tab[joueur.x][joueur.y] = joueur.CASETRACE;
            joueur.x = iaX;
            joueur.y = iaY;
            grille.tab[joueur.x][joueur.y] = joueur.CASEJOUEUR;// Met à jour la grille en remplaçant la case choisie par la case du joueur
            graphique.repaint();
            configuration.updateLabel();
        }
    
}}