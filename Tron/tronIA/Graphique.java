package tronIA;
import java.awt.*;
import javax.swing.*;

public class Graphique extends JPanel {
    Grille grille;
    int CASE ;
    int ARC ;
    int ESP ;
    Joueur joueurRouge;
    Joueur joueurBleu;
    Mur mur; // Déclare des variables

    public Graphique(Grille grille, Joueur joueurRouge, Joueur joueurBleu, Mur mur) {
    this.grille = grille;    
    this.CASE = 45; // Taille de case
    this.ARC = 10; // Rayon de l'arrondi des cases
    this.ESP = 4; // Espacement entre les cases
    this.joueurRouge=joueurRouge;
    this.joueurBleu=joueurBleu;
    this.mur=mur;
    setPreferredSize(new Dimension(grille.COLONNES * CASE + (grille.COLONNES - 1) * ESP, grille.LIGNES * CASE + (grille.LIGNES - 1) * ESP));
    setBackground(Color.BLACK); 
}

// Appelle la méthode paintComponent
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    dessinerCases(g2);
}

private void dessinerCases(Graphics2D g2) {
    for (int j = 0; j < grille.LIGNES; j++) {
    for (int i = 0; i < grille.COLONNES; i++) {
            int x = i * (CASE + ESP), y = j * (CASE + ESP);
            g2.setColor(Color.CYAN);//Dessine les cases vides
            g2.drawRoundRect(x, y, CASE, CASE, ARC, ARC);
            g2.drawRoundRect(x, y, CASE, CASE, ARC, ARC); 
            if (grille.tab[i][j] == mur.CASEMUR) { 
                g2.fillRoundRect(x, y, CASE, CASE, ARC, ARC);
            } else if (grille.tab[i][j] == joueurRouge.CASETRACE ) { 
                caseTrace(g2, x, y, joueurRouge);
            } else if (grille.tab[i][j] == joueurBleu.CASETRACE) { 
                caseTrace(g2, x, y, joueurBleu);
        }
    }
    caseJoueur( g2, joueurBleu.moto,joueurBleu,joueurBleu.x * (CASE + ESP),joueurBleu.y * (CASE + ESP), CASE, CASE); 
    caseJoueur( g2, joueurRouge.moto,joueurRouge,joueurRouge.x * (CASE + ESP),joueurRouge.y * (CASE + ESP), CASE, CASE);

}
}
    // Dessine la case des tracés des joeurs
    private void caseTrace(Graphics2D g2, int x, int y, Joueur Joueur) {
        g2.setColor(Joueur.couleur);
        g2.drawRoundRect(x , y , CASE, CASE, ARC, ARC);
        g2.setColor(new Color(Joueur.couleur.getRed(),Joueur.couleur.getGreen(),Joueur.couleur.getBlue(),50));
        g2.fillRoundRect(x, y, CASE, CASE, ARC, ARC);
    }

    // Dessine la case des joeurs avec leurs images
    private void caseJoueur(Graphics2D g2,Image img, Joueur Joueur, int x, int y, int height, int width) {
        g2.setColor(Joueur.couleur);
        g2.drawRoundRect(x , y , CASE, CASE, ARC, ARC);
        g2.fillRoundRect(x, y, CASE, CASE, ARC, ARC);
        g2.drawImage( img,x,y,width,height,this);
        }
    }



    