package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import jeu.pion.Pion;
import jeu.Couleur;
import jeu.Case;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import controleur.Controleur;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

/**
*cette classe permet de créer et de gerer un panel et tous ses composant
*le panel contient un plateau de 11x11 de CustomButton avec image, un label contenant les infos du joueur qui joue
*et 4 boutons : save, regle, quitter et musique
*@author Q.Le Lan
*/
public class Partie{

  /**
  *le panel construit par cette classe
  */
  private ImagePanel plateau;

  /**
  *le panel seulement avec les images représentant la grille de jeu
  */
  private JPanel grille;

  /**
  *le bouton enregistrer
  */
  private JButton save;

  /**
  *le bouton regle
  */
  private JButton regle;

  /**
  *le bouton quitter
  */
  private JButton quitter;

  /**
  *bouton qui gere la musique
  */
  private JButton musique;

  /**
  *le label d'information pour le joueur
  */
  private JLabel info;

  /**
  *le label contenant le nom du joueur à jouer
  */
  private JLabel etat;

  /**
  *un double tableau pour sauvegarder tous les boutons de la grille
  */
  private CustomButton[][] plat;

  /**
  *le construteur de partie
  *@param casse le tableau contenant toute les cases du jeu
  *@throws IllegalArgumentException si le parametre est null
  */
  public Partie(Case[][] casse){
    if(casse==null)throw new IllegalArgumentException("Partie.le parametre est null");
    initComponents(casse);
  }

  /**
  *permet d'initialiser le panel et tous ses elements
  *@param casse le tableau contenant toute les cases du jeu
  */
  private void initComponents(Case[][] casse){
    this.plat=new CustomButton[11][11];

    Font font = new Font("Arial",Font.BOLD,16);
    this.save=new JButton("<html> <u>sauvegarder</u></html>");
    this.save.setBorderPainted(false);
    this.save.setContentAreaFilled(false);
    this.save.setForeground(Color.YELLOW);
    this.save.setFont(font);

    this.musique=new JButton("<html> <u>arr\u00eater la musique</u></html>");
    this.musique.setBorderPainted(false);
    this.musique.setContentAreaFilled(false);
    this.musique.setForeground(Color.YELLOW);
    this.musique.setFont(font);

    this.regle=new JButton("<html> <u>r\u00e8gles</u></html>");
    this.regle.setBorderPainted(false);
    this.regle.setContentAreaFilled(false);
    this.regle.setForeground(Color.YELLOW);
    this.regle.setFont(font);

    this.quitter=new JButton("<html> <u>quitter</u></html>");
    this.quitter.setBorderPainted(false);
    this.quitter.setContentAreaFilled(false);
    this.quitter.setForeground(Color.YELLOW);
    this.quitter.setFont(font);

    this.info=new JLabel();
    this.info.setForeground(Color.YELLOW);
    this.info.setFont(font);
    this.etat=new JLabel("<html>Cliquez sur une case pour selectionner le pion \u00e0 deplacer</html>");
    this.etat.setForeground(Color.YELLOW);
    this.etat.setFont(font);

    this.grille=new JPanel();
    this.plateau=new ImagePanel(new ImageIcon(getClass().getResource(Path.FOND.getPath())).getImage());
    this.plateau.setLayout(new BorderLayout());
    setCustom(casse);
    this.plateau.add(this.grille,BorderLayout.CENTER);

    JPanel pan1=new JPanel();
    JPanel pan2=new JPanel();
    JPanel pan3=new JPanel();
    JPanel pan4=new JPanel();
    JPanel pan5=new JPanel();
    JPanel pan6=new JPanel();

    pan1.setOpaque(false);
    pan2.setOpaque(false);
    pan3.setOpaque(false);
    pan4.setOpaque(false);
    pan5.setOpaque(false);
    pan6.setOpaque(false);

    JPanel droite=new JPanel();
    droite.setOpaque(false);
    droite.setLayout(new GridLayout(7,1));
    droite.add(this.musique);
    droite.add(pan6);
    droite.add(this.save);
    droite.add(pan1);
    droite.add(this.regle);
    droite.add(pan2);
    droite.add(this.quitter);

    JPanel droite2=new JPanel(new GridLayout(2,1));
    droite2.setOpaque(false);
    droite2.add(pan3);
    droite2.add(droite);

    JPanel nord=new JPanel(new GridLayout(4,1));
    nord.setOpaque(false);
    nord.add(pan4);
    nord.add(this.info);
    nord.add(pan5);
    nord.add(this.etat);

    this.plateau.add(droite2,BorderLayout.EAST);
    this.plateau.add(nord,BorderLayout.NORTH);
  }

  /**
  *permet d'initialiser la partie "plateau" avec tous les boutons et leur images
  *garde les référence des boutons dans un tableau en 2d pour les retrouver facilement
  *@param casse les cases contenant les pions qui servent pour la couleur des boutons
  */
  private void setCustom(Case[][] casse){
    GridLayout grid=new GridLayout(11,11);
    this.grille=new JPanel();

    this.grille.setLayout(grid);

    for(int pos_y=0; pos_y<11; pos_y++){
      for(int pos_x=0; pos_x<11; pos_x++){
        String path="";
        if(((pos_y%2==0)&&(pos_x%2==0)) || ((pos_y%2!=0) && (pos_x%2!=0))){
          if(casse[pos_x][pos_y].estOccupe()){
              if(casse[pos_x][pos_y].getPion().getCouleur()==Couleur.NOIR){
                path=Path.NOIRN.getPath();
              }else if(casse[pos_x][pos_y].getPion().getCouleur()==Couleur.BLANC){
                path=Path.BLANCN.getPath();
              }else{
                path=Path.ROUGEN.getPath();
              }
            }else{
              path=Path.VIDEN.getPath();
            }
          }else{
            if(casse[pos_x][pos_y].estOccupe()){
              if(casse[pos_x][pos_y].getPion().getCouleur()==Couleur.NOIR){
                path=Path.NOIR.getPath();
              }else if(casse[pos_x][pos_y].getPion().getCouleur()==Couleur.BLANC){
                path=Path.BLANC.getPath();
              }else{
                path=Path.ROUGE.getPath();
              }
            }else{
              path=Path.VIDE.getPath();
            }
          }
          CustomButton button = new CustomButton(getClass().getResource(path));
          button.setName(pos_y+":"+pos_x);
          //utton.addActionListener(new boutonListener());
          button.setBorder(BorderFactory.createLineBorder(Color.black));
          //button.setBackground(Color.white);
          this.grille.add(button);
          this.plat[pos_x][pos_y]=button;
      }
    }
    this.grille.setOpaque(false);
    this.plateau.add(this.grille,BorderLayout.CENTER);
  }

  /**
  *ajoute le controleur en ecouteur d'action
  *ajoute a tous les boutons du panel : plateau + les 3 autres
  *@param ctrl le controleur qui écoutera
  *@throws IllegalArgumentException si le param est null
  */
  public void addAction(Controleur ctrl){
    if(ctrl==null)throw new IllegalArgumentException("Partie.addAction. le param ne peut pas etre null");
    this.regle.addActionListener(ctrl);
    this.quitter.addActionListener(ctrl);
    this.save.addActionListener(ctrl);
    this.musique.addActionListener(ctrl);
    for(int i=0;i<11;i++){
      for(int j=0;j<11;j++){
        this.plat[i][j].addActionListener(ctrl);
      }
    }
  }

  /**
  *permet d'update les images des boutons qui constituent le "plateau"
  *update seulement les images de 2 boutons, celui en coord[0],coord[1] le set en image vide
  *et en coord[2],coord[3] avec la couleur du pion déplacé
  *@param plateau le tableau contenant toutes les cases
  *@param coord le tableau contenant les coordonnes du déplacement [0]=x1 [1]=y1 [2]=x2 [3]=y2
  *@throws IllegalArgumentException si les param sont null
  */
  public void updateCustom(Case[][] plateau,int[] coord){
    if(plateau==null || coord==null)throw new IllegalArgumentException("updateCustom.les params sont null");
    Couleur couleur=plateau[coord[2]][coord[3]].getPion().getCouleur();
    if(((coord[0]%2==0)&&(coord[1]%2==0)) || ((coord[0]%2!=0) && (coord[1]%2!=0))){
      this.plat[coord[0]][coord[1]].setImageBackground(getClass().getResource(Path.VIDEN.getPath()));
    }else{
      this.plat[coord[0]][coord[1]].setImageBackground(getClass().getResource(Path.VIDE.getPath()));
    }
    if(((coord[2]%2==0)&&(coord[3]%2==0)) || ((coord[3]%2!=0) && (coord[2]%2!=0))){
      if(couleur==Couleur.NOIR){
        this.plat[coord[2]][coord[3]].setImageBackground(getClass().getResource(Path.NOIRN.getPath()));
      }else if(couleur==Couleur.BLANC){
        this.plat[coord[2]][coord[3]].setImageBackground(getClass().getResource(Path.BLANCN.getPath()));
      }else{
        this.plat[coord[2]][coord[3]].setImageBackground(getClass().getResource(Path.ROUGEN.getPath()));
      }
    }else{
      if(couleur==Couleur.NOIR){
        this.plat[coord[2]][coord[3]].setImageBackground(getClass().getResource(Path.NOIR.getPath()));
      }else if(couleur==Couleur.BLANC){
        this.plat[coord[2]][coord[3]].setImageBackground(getClass().getResource(Path.BLANC.getPath()));
      }else{
        this.plat[coord[2]][coord[3]].setImageBackground(getClass().getResource(Path.ROUGE.getPath()));
      }
    }
  }

  /**
  *permet de rendre l'image du bouton à la place x,y selectionné
  *@param x la position x
  *@param y la position y
  *@param carre la case qui contient le pion
  *@throws IllegalArgumentException si x et y par dans les coordonnes et si carre est null
  */
  public void setSelected(int x, int y, Case carre){
    if(carre==null)throw new IllegalArgumentException("setSelected.le param ne peut pas etre null");
    if(x<0 || x>10)throw new IllegalArgumentException("setSelected.parametre non valide x<0 || x>10 :"+" "+x);
    if(y<0 || y>10)throw new IllegalArgumentException("setSelected.parametre non valide y<0 || y>10 :"+" "+y);

    if(carre.estOccupe()){
      Couleur couleur=carre.getPion().getCouleur();

      if(couleur==Couleur.NOIR){
          this.plat[x][y].setImageBackground(getClass().getResource(Path.NOIRS.getPath()));
      }else if(couleur==Couleur.BLANC){
        this.plat[x][y].setImageBackground(getClass().getResource(Path.BLANCS.getPath()));
      }else{
        this.plat[x][y].setImageBackground(getClass().getResource(Path.ROUGES.getPath()));
      }
    }else{
      this.plat[x][y].setImageBackground(getClass().getResource(Path.VIDES.getPath()));
    }
  }

  /**
  *permet de rendre l'image du bouton à la place x,y normal
  *@param x la position x
  *@param y la position y
  *@param carre la case qui contient le pion
  *@throws IllegalArgumentException si x et y par dans les coordonnes et si carre est null
  */
  public void setNotSelected(int x, int y, Case carre){
    if(carre==null)throw new IllegalArgumentException("setNotSelected.le param ne peut pas etre null");
    if(x<0 || x>10)throw new IllegalArgumentException("setNotSelected.parametre non valide x<0 || x>10 :"+" "+x);
    if(y<0 || y>10)throw new IllegalArgumentException("setNotSelected.parametre non valide y<0 || y>10 :"+" "+y);
    if(((y%2==0)&&(x%2==0)) || ((y%2!=0) && (x%2!=0))){
      if(carre.estOccupe()){
        Couleur couleur=carre.getPion().getCouleur();

        if(couleur==Couleur.NOIR){
            this.plat[x][y].setImageBackground(getClass().getResource(Path.NOIRN.getPath()));
        }else if(couleur==Couleur.BLANC){
          this.plat[x][y].setImageBackground(getClass().getResource(Path.BLANCN.getPath()));
        }else{
          this.plat[x][y].setImageBackground(getClass().getResource(Path.ROUGEN.getPath()));
        }
      }else{
        this.plat[x][y].setImageBackground(getClass().getResource(Path.VIDEN.getPath()));
      }
    }else{
      if(carre.estOccupe()){
        Couleur couleur=carre.getPion().getCouleur();

        if(couleur==Couleur.NOIR){
            this.plat[x][y].setImageBackground(getClass().getResource(Path.NOIR.getPath()));
        }else if(couleur==Couleur.BLANC){
          this.plat[x][y].setImageBackground(getClass().getResource(Path.BLANC.getPath()));
        }else{
          this.plat[x][y].setImageBackground(getClass().getResource(Path.ROUGE.getPath()));
        }
      }else{
        this.plat[x][y].setImageBackground(getClass().getResource(Path.VIDE.getPath()));
      }
    }
  }

  /**
  *permet de grisser les cases dont le déplacement est possible sur celle-ci
  *@param coord le tableau avec tous les déplacement possible
  *@param plateau le plateau pour avoir la couleur des pions
  *@throws IllegalArgumentException si les params sont nulls
  */
  public void afficheMove(int[][] coord, Case[][] plateau){
    if(coord==null ||plateau==null)throw new IllegalArgumentException("afficheMove. les params ne peuvent pas etre null");
    for(int i=0;i<coord.length;i++){
      if(coord[i][1]!=-1 && coord[i][0]!=-1){
        int x=coord[i][0];
        int y=coord[i][1];
        if(plateau[x][y].estOccupe()){
          Couleur couleur=plateau[x][y].getPion().getCouleur();

          if(couleur==Couleur.NOIR){
              this.plat[x][y].setImageBackground(getClass().getResource(Path.NOIRS.getPath()));
          }else if(couleur==Couleur.BLANC){
            this.plat[x][y].setImageBackground(getClass().getResource(Path.BLANCS.getPath()));
          }else{
            this.plat[x][y].setImageBackground(getClass().getResource(Path.ROUGES.getPath()));
          }
        }else{
          this.plat[x][y].setImageBackground(getClass().getResource(Path.VIDES.getPath()));
        }
      }
    }
  }

  /**
  *permet de remettre la bonne couleur sur les cases grissé dont le mouvement était possible
  *@param coord le tableau qui contient les coodonnées des cases grisés
  *@param plateau le plateau pour avoir les couleurs
  *@throws IllegalArgumentException si les params sont nulls
  */
  public void supprMove(int[][] coord, Case[][] plateau){

    if(coord==null ||plateau==null)throw new IllegalArgumentException("supprMove. les params ne peuvent pas etre null");
    for(int i=0;i<coord.length;i++){
      if(coord[i][1]!=-1 && coord[i][0]!=-1){
        int x=coord[i][0];
        int y=coord[i][1];

        if(((y%2==0)&&(x%2==0)) || ((y%2!=0) && (x%2!=0))){
          if(plateau[x][y].estOccupe()){
            Couleur couleur=plateau[x][y].getPion().getCouleur();

            if(couleur==Couleur.NOIR){
                this.plat[x][y].setImageBackground(getClass().getResource(Path.NOIRN.getPath()));
            }else if(couleur==Couleur.BLANC){
              this.plat[x][y].setImageBackground(getClass().getResource(Path.BLANCN.getPath()));
            }else{
              this.plat[x][y].setImageBackground(getClass().getResource(Path.ROUGEN.getPath()));
            }
          }else{
            this.plat[x][y].setImageBackground(getClass().getResource(Path.VIDEN.getPath()));
          }
        }else{
          if(plateau[x][y].estOccupe()){
            Couleur couleur=plateau[x][y].getPion().getCouleur();

            if(couleur==Couleur.NOIR){
                this.plat[x][y].setImageBackground(getClass().getResource(Path.NOIR.getPath()));
            }else if(couleur==Couleur.BLANC){
              this.plat[x][y].setImageBackground(getClass().getResource(Path.BLANC.getPath()));
            }else{
              this.plat[x][y].setImageBackground(getClass().getResource(Path.ROUGE.getPath()));
            }
          }else{
            this.plat[x][y].setImageBackground(getClass().getResource(Path.VIDE.getPath()));
          }
        }
      }
    }
  }
  /**
  *permet d'activer ou de désactiver tous les boutons de la grille
  *@param choice false pour les désactiver, truc pour les actiber
  */
  public void setEnabled(boolean choice){
    for(int i=0;i<11;i++){
      for(int j=0;j<11;j++){
        this.plat[i][j].setEnabled(choice);
      }
    }
  }
  /**
  *le getteur du panel construit par cette classe
  *@return le panel
  */
  public JPanel getPanel(){
    return this.plateau;
  }

  /**
  *le getteur du bouton regle
  *@return le bouton regle
  */
  public JButton getRegle(){
    return this.regle;
  }

  /**
  *le getteur du bouton save
  *@return le bouton save
  */
  public JButton getSave(){
    return this.save;
  }

  /**
  *le getteur du bouton retour
  *@return le bouton retour
  */
  public JButton getRetour(){
    return this.quitter;
  }

  /**
  *le getteur du label contenant les infos du joueur
  *@return le label
  */
  public JLabel getInfo(){
    return this.info;
  }

  /**
  *le getteur du label contenant les infos pour jouer
  *@return le label etat
  */
  public JLabel getEtat(){
    return this.etat;
  }
  /**
  *le getteur du tableau contenant tout les boutons custom du "plateau"
  *@return tous les boutons custom dans un tableau
  */
  public CustomButton[][] getCustom(){
    return this.plat;
  }

  /**
  *le getteur du bouton musique
  *@return le bouton musique
  */
  public JButton getMusique(){
    return this.musique;
  }
}
