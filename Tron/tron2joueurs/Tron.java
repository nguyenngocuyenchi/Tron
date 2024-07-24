package tron2joueurs;
import java.awt.*;
import javax.swing.*;



public class Tron extends JFrame{
    Joueur joueurRouge;
    Joueur joueurBleu;
    Grille grille;
    Graphique graphique;
    Configuration configuration;
    Mur mur;
    Deplace deplace;
    static boolean coupJoueurRouge;


    public Tron() { 
        //Instance des différentes classes
        joueurRouge = new Joueur(Color.RED,2,2,1,3,new ImageIcon(getClass().getResource("moto/RM3R.png")).getImage());
        joueurBleu = new Joueur(Color.BLUE,19,9,2,4,new ImageIcon(getClass().getResource("moto/BM3L.png")).getImage());
        mur = new Mur (new int[][]{{4, 4}, {4, 5}, {4, 6}, {4, 7},{4, 8}, {17, 4}, {17, 5}, {17, 6}, {17, 7},{17, 8}},5);
        grille = new Grille( 22,12,joueurRouge,joueurBleu,mur);
        graphique= new Graphique (grille,joueurRouge,joueurBleu,mur);
        configuration= new Configuration(grille,joueurRouge,joueurBleu,graphique);
        deplace = new  Deplace(joueurRouge, joueurBleu,grille,graphique,configuration,this);
        this.addKeyListener(new Deplace(joueurRouge, joueurBleu,grille,graphique,configuration,this));
        this.setLayout(new BorderLayout());
        this.add(graphique, BorderLayout.CENTER);
        this.add(configuration, BorderLayout.EAST);
        setTitle("TRON !");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setup();
    }

    public static void main(String[] args) {
        new Tron(); // Instance de la classe Tron
    }

    //Méthode pour choisir le premier joueur 
    public void setup() {
        String[] options = {"Joueur Rouge", "Joueur Bleu"};
        int choice = JOptionPane.showOptionDialog(null, "Quel joueur voulez-vous être ?", "Choix du joueur",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (choice == 0) {
                    coupJoueurRouge = true;
                } else {
                    coupJoueurRouge = false;
                }                
    }

    //Méthode pour rejouer
    public void restartGame() {
        this.dispose(); // Ferme la fenêtre de jeu actuelle
        new Tron(); //Nouvelle instance de la classe Tron
    } 
}

