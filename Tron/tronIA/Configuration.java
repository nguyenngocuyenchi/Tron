package tronIA;

import java.awt.*;
import javax.swing.*;
import java.io.*;


public class Configuration extends JPanel {
    Grille grille;
    Joueur joueurRouge;
    Joueur joueurBleu;
    JPanel contentPanel;
    JLabel label;
    Font font;
    Font font60;
    Font font30;
    Font font20;

    public Configuration(Grille grille, Joueur joueurRouge, Joueur joueurBleu, Graphique graphique) {
        this.grille = grille;
        this.joueurRouge = joueurRouge;
        this.joueurBleu = joueurBleu;
        setPreferredSize(new Dimension(200, grille.LIGNES * graphique.CASE + (grille.LIGNES - 1) * graphique.ESP));
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        loadFont();
        createPanel();
        add(contentPanel);
    }

    // Charge la police de caractères à partir d'un fichier ttf
    private void loadFont() {
        try (InputStream is = getClass().getResourceAsStream("tronfont.ttf")) {
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            font60 = font.deriveFont(60f);
            font30 = font.deriveFont(30f);
            font20 = font.deriveFont(20f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    // Crée le panneau de contenu avec le label "TRON"
    private void createPanel() {
        contentPanel = new JPanel(new GridLayout(0, 1));
        contentPanel.setBackground(Color.BLACK);

        label = new JLabel("TRON");
        label.setForeground(Color.CYAN);
        label.setFont(font60);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        contentPanel.add(label);
    }

    // Crée le panneau d'informations pour un joueur spécifique
    private void createPlayerLabel(String playerName, Color color, int x, int y, int cellCount) {
        JPanel playerLabel = new JPanel(new GridLayout(3, 1));
        playerLabel.setBackground(Color.BLACK);

        JLabel label1 = new JLabel(playerName);
        label1.setForeground(color);
        label1.setFont(font30);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        playerLabel.add(label1);

        JLabel label2 = new JLabel("x ( " + x + " )  y ( " + y + " )");
        label2.setForeground(Color.CYAN);
        label2.setFont(font20);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        playerLabel.add(label2);

        JLabel label3 = new JLabel("cells: " + cellCount);
        label3.setForeground(Color.CYAN);
        label3.setFont(font20);
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        playerLabel.add(label3);

        contentPanel.add(playerLabel);
    }

    // Met à jour le label d'informations des joueurs
    public void updateLabel() {
        contentPanel.removeAll();
        createPlayerLabel("RED BIKE", joueurRouge.couleur, joueurRouge.x, joueurRouge.y, grille.nbTrace(joueurRouge));
        createPlayerLabel("BLUE BIKE", joueurBleu.couleur, joueurBleu.x, joueurBleu.y, grille.nbTrace(joueurBleu));
        revalidate();
        repaint();
    }
}