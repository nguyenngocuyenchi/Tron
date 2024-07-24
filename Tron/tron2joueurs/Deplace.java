package tron2joueurs;
import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Deplace extends KeyAdapter {
    Tron tron;
    Joueur joueurRouge;
    Joueur joueurBleu;
    Grille grille;
    Graphique graphique;
    Configuration configuration; // Déclare des variables

    public Deplace(Joueur joueurRouge, Joueur joueurBleu, Grille grille, Graphique graphique, Configuration configuration,Tron tron) {
        this.tron = tron; 
        this.joueurRouge = joueurRouge;
        this.joueurBleu = joueurBleu;
        this.grille = grille;
        this.graphique = graphique;
        this.configuration = configuration; // Initialise des valeurs
    }

    public void keyPressed(java.awt.event.KeyEvent e) {
        int keyCode = e.getKeyCode();// Récupère le code de la touche pressée
        if (Tron.coupJoueurRouge) {
            if (deplacerJoueur(joueurRouge, keyCode, "R", "Joueur Rouge")) {
                Tron.coupJoueurRouge = false; // Le coup est valide, passe au joueur suivant
            }
        } else {
            if (deplacerJoueur(joueurBleu, keyCode, "B", "Joueur Bleu")) {
                Tron.coupJoueurRouge = true; // Le coup est valide, passe au joueur suivant
            }
        }
        checkGameOver(joueurRouge, "Joueur Rouge", joueurRouge.x, joueurRouge.y);// Vérifie si les joueurs ont perdu la partie
        checkGameOver(joueurBleu, "Joueur Bleu", joueurBleu.x, joueurBleu.y);
}
    

    public boolean deplacerJoueur(Joueur joueur, int keyCode, String lettre, String nomJoueur) {
        if (actionLegale(joueur.x, joueur.y,keyCode)) {
            grille.tab[joueur.x][joueur.y] = joueur.CASETRACE;
            if (keyCode == KeyEvent.VK_UP) {
                //change les coordonées et les images en fonction de la touche pressée
                joueur.y--;
                joueur.moto = new ImageIcon(getClass().getResource("moto/" + lettre + "M3U.png")).getImage();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                joueur.y++;
                joueur.moto = new ImageIcon(getClass().getResource("moto/" + lettre + "M3D.png")).getImage();
            } else if (keyCode == KeyEvent.VK_LEFT) {
                joueur.x--;
                joueur.moto = new ImageIcon(getClass().getResource("moto/" + lettre + "M3L.png")).getImage();
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                joueur.x++;
                joueur.moto = new ImageIcon(getClass().getResource("moto/" + lettre + "M3R.png")).getImage();
            }
            grille.tab[joueur.x][joueur.y] = joueur.CASEJOUEUR;
            graphique.repaint();
            configuration.updateLabel();
            return true; // Le coup est valide
        } else {
            JOptionPane.showMessageDialog(null, nomJoueur + ", vous êtes sur la mauvaise voie ヽ(°〇°)ﾉ", "Attention", JOptionPane.WARNING_MESSAGE);
            return false; // Le coup n'est pas valide

        }
    }


        //Vérifie si les actions sont légales; cases vides et dans la grille    
    public boolean actionLegale(int x, int y, int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            return y > 0 && grille.tab[x][y - 1] == grille.VIDE;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            return y < grille.LIGNES - 1 && grille.tab[x][y + 1] == grille.VIDE;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            return x > 0 && grille.tab[x - 1][y] == grille.VIDE;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            return x < grille.COLONNES - 1 && grille.tab[x + 1][y] == grille.VIDE;
        }
        return false;
    }  
    
        //Vérifie si le joueur est bloqué de tous les côtés 
    public boolean checkGameOver(Joueur joueur, String nomJoueur, int x, int y) {   
        if (((x == 0) || grille.tab[x - 1][y] != grille.VIDE) && 
        ((x == grille.COLONNES - 1) || grille.tab[x + 1][y] != grille.VIDE) && 
        ((y == 0) || grille.tab[x][y - 1] != grille.VIDE) && 
        ((y == grille.LIGNES - 1) || grille.tab[x][y + 1] != grille.VIDE)) {
            JOptionPane.showMessageDialog(null, nomJoueur + " est mort ( ×_× ) !", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            arret();
            return true;
        }
        return false;
    }

    // Arrête le jeu, affiche le message de fin de partie 
    //Propose une autre partie ou de quitter le jeu
    public void arret(){
        String[] options= {"Rejouer ＼(≧▽≦)／", "Quitter o(╥﹏╥)o"};
        int choice = JOptionPane.showOptionDialog(null, "Voulez vous rejouer une autre partie ?", "Choix de jeu",
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    if (choice == 0) {
        tron.restartGame();
    } else {
        System.exit(0);
    }
    }
}
