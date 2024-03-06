package controleur;

import java.awt.event.*;
import javax.swing.*;
import view.Vue;
import utilitaire.Utilitaire;
import jeu.joueur.JoueurHumain;
import jeu.joueur.Joueur;
import jeu.Jeu;
import jeu.Mode;
import jeu.Couleur;
import view.CustomButton;
import jeu.joueur.JoueurIA1;
/**
*class controleur du modele MVC, elle utilise principalement actionPerformed (bouton)
*pour les differentes classes du package view
*elle contient aussi la boucle du jeu en mode graphique
*@author Q.Le Lan
*/
public class Controleur implements ActionListener{

  /**
  *la vue qui contient tous les membres du package view
  */
  private Vue vue;

  /**
  *le jeu qui est le modele du MVC
  */
  private Jeu jeu;

  /**
  *permet de savoir si un joueur joue, true si il a cliqué sur un bouton du plateau, il revient à false
  *quand un joueur à jouer son tour
  */
  private boolean joue;

  /**
  *permet de savoir si la partie en cours est fini ou non,
  *true si fini, sinon false
  */
  private boolean fini;

  /**
  *true si la musique est en cour, false sinon
  */
  private boolean musique;

  /**
  *un tableau qui permet de connaitre les déplacement voulue par le joueur courant
  *[0]=x1 [1]=y1  [2]=x2 [3]=y3
  */
  private int[] coord;

  /**
  *vrai s'il faut afficher les coordoonées dispo
  */
  private boolean facile;

  /**
  *permert d'avoir les coordnnées disponible pour un mouvement
  */
  private int[][] move;

  /**
  *le construteur du Controleur
  *@param vue la vue pour gérer les composant graphique
  *@throws IllegalArgumentException si la vue est null
  */
  public Controleur(Vue vue){
    if(vue==null)throw new IllegalArgumentException("Controleur.le parametre est null");
    this.joue=false;
    this.fini=false;
    this.vue=vue;
    this.musique=true;
    this.facile=false;
    this.coord=new int[4];
  }

  /**
  *est appellé lorsque une action est effectuer sur les composant que écoute le controleur
  *@param e la source de l'action
  */
  public void actionPerformed(ActionEvent e) {
    Object src=e.getSource();
    //pour quitter l'application

    if(src==this.vue.getAcc().getRetour()){
      this.vue.getFrame().dispose();
      //charger de menu
    }else if(src==this.vue.getMenu().getCharger()){
      this.jeu=Utilitaire.openPartie();
      this.vue.setPartie(this.jeu);
      this.vue.setPanel(this.vue.getPartie().getPanel());
      //retour du menu
    }else if(src==this.vue.getMenu().getRetour()){
      this.vue.setPanel(this.vue.getAcc().getAccueil());
      //jouer de l'accueil
    }else if(src==this.vue.getAcc().getJouer()){
      this.vue.setPanel(this.vue.getMenu().getMenu());
      //reglede l'accueil
    }else if(src==this.vue.getAcc().getRegle()){
      Utilitaire.afficheRegle();
      //Multijoueur du menu
    }else if(src==this.vue.getMenu().getMultijoueur()){
      this.vue.setPanel(this.vue.getMulti().getPanel());
      //retour du Multijoueur
    }else if(src==this.vue.getMulti().getRetour()){
      this.vue.setPanel(this.vue.getMenu().getMenu());
      //IA du menu
    }else if(src==this.vue.getMenu().getIA()){
      this.vue.setPanel(this.vue.getMenuIA().getPanel());
      //jouer du MenuIA
    }else if(src==this.vue.getMenuIA().getJouer()){
      String nom1=this.vue.getMenuIA().getFieldNom1().getText();
      this.vue.getMenuIA().getFieldNom1().setText("");
      this.jeu=new Jeu(nom1,"",Mode.HA);
      this.vue.setPartie(this.jeu);
      setTextInfo(this.jeu.getCourant());
      this.vue.setPanel(this.vue.getPartie().getPanel());

      if(!musique){
        this.vue.getPartie().getMusique().setText("<html><u> d\u00e9marrer la musique</u></html>");
      }


      if(this.vue.getMenuIA().getBox().isSelected()){
        this.facile=true;
      }

      if(fini){
        this.vue.getPartie().setEnabled(true);
        this.fini=false;
      }
      //retour du MenuIA
    }else if(src==this.vue.getMenuIA().getRetour()){
      this.vue.setPanel(this.vue.getMenu().getMenu());
      //jouer du Multijoueur
    }else if(src==this.vue.getMulti().getJouer()){
      String nom1=this.vue.getMulti().getFieldNom1().getText();
      String nom2=this.vue.getMulti().getFieldNom2().getText();

      this.vue.getMulti().getFieldNom1().setText("");
      this.vue.getMulti().getFieldNom2().setText("");

      this.jeu=new Jeu(nom1,nom2,Mode.HH);
      this.vue.setPartie(this.jeu);
      setTextInfo(this.jeu.getCourant());
      this.vue.setPanel(this.vue.getPartie().getPanel());

      if(this.vue.getMulti().getBox().isSelected()){
        this.facile=true;
      }

      if(!musique){
        this.vue.getPartie().getMusique().setText("<html><u> d\u00e9marrer la musique</u></html>");
      }

      if(fini){
        this.vue.getPartie().setEnabled(true);
        this.fini=false;
      }
      //regle pour une partie en cour
    }else if(src==this.vue.getPartie().getRegle()){
      Utilitaire.afficheRegle();
      //save de partie
    }else if(src==this.vue.getPartie().getSave()){
      Utilitaire.sauvePartie(this.jeu);
      //retour de la partie
    }else if(src==this.vue.getPartie().getRetour()){
      this.vue.setPanel(this.vue.getAcc().getAccueil());
      this.facile=false;
      //gérer la musique
    }else if(src==this.vue.getPartie().getMusique()){
      if(musique){
        this.vue.setMusique();
        this.vue.getPartie().getMusique().setText("<html> <u>d\u00e9marrer la musique</u></html>");
        this.musique=false;
      }else{
        this.vue.setMusiqueTrue();
        this.vue.getPartie().getMusique().setText("<html> <u>arr\u00eater la musique</u></html>");
        this.musique=true;
      }
    }
    //pour tous les boutons du plateau et la boucle du jeu
    else{
      JoueurHumain j1=(JoueurHumain)this.jeu.getCourant();

      setTextInfo(j1);
      int i=0;
      int j=0;
      boolean trouver =false;
      CustomButton[][] plat=this.vue.getPartie().getCustom();
      while(i<11 && !trouver){
        j=0;
        while(j<11 && !trouver){
          if(src==plat[i][j]){
            trouver=true;
          }
          j++;
        }
        i++;
      }
      if(trouver){
        //premier bouton
        if(!this.joue){
          this.coord[0]=i-1;
          this.coord[1]=j-1;
          this.joue=true;
          this.vue.getPartie().getEtat().setText("<html>Vous avez selectionn\u00e9 le pion en"+" "+(i-1)+","+(j-1)+ "  cliquez sur une autre case pour le d\u00e9placer</html>");
          this.vue.getPartie().setSelected(this.coord[0],this.coord[1],this.jeu.getPlateu().getPlateau()[this.coord[0]][this.coord[1]]); // marquer la case selectionné
          if(facile){
            if(this.jeu.getPlateu().getPlateau()[this.coord[0]][this.coord[1]].estOccupe()){
              this.move=this.jeu.getCourant().moveDispos(this.coord[0],this.coord[1],this.jeu.getPlateu());
              this.vue.getPartie().afficheMove(move,this.jeu.getPlateu().getPlateau());
              this.vue.setPanel(this.vue.getPartie().getPanel());
            }
          }
        }else{
          //deuxieme bouton
          this.coord[2]=i-1;
          this.coord[3]=j-1;
          boolean valide=j1.estValide(this.coord[0],this.coord[1],this.coord[2],this.coord[3],this.jeu.getPlateu());
          //si le deplacement est bon
          if(valide){
            if(this.jeu.getPlateu().deplacePion(this.coord[0],this.coord[1],this.coord[2],this.coord[3],j1,this.jeu.getNonCourant())){
              this.jeu.changeCourant();
              this.vue.getPartie().updateCustom(this.jeu.getPlateu().getPlateau(),this.coord);
              if(facile){
                this.vue.getPartie().supprMove(this.move,this.jeu.getPlateu().getPlateau());
              }
              this.vue.setPanel(this.vue.getPartie().getPanel());
              this.vue.getPartie().getEtat().setText("<html>Cliquez sur une case pour selectionner le pion \u00e0 d\u00e9placer</html>");
            }

            setTextInfo(this.jeu.getCourant());
            //pour le mode IA on fait jouer le joueur IA
            if(this.jeu.getMode()==Mode.HA){
              JoueurIA1 j2 =(JoueurIA1) this.jeu.getCourant();

              this.coord=j2.joue(this.jeu.getPlateu());

              this.jeu.getPlateu().deplacePion(this.coord[0],this.coord[1],this.coord[2],this.coord[3],j2,this.jeu.getNonCourant());
              this.jeu.changeCourant();

              this.vue.getPartie().updateCustom(this.jeu.getPlateu().getPlateau(),this.coord);

              this.vue.setPanel(this.vue.getPartie().getPanel());
              setTextInfo(this.jeu.getCourant());
            }
            //regarde si 1 des 2 à gagné
            if(this.jeu.getPlateu().checkVainqueur(this.jeu.getNonCourant())){
              this.fini=true;
              this.vue.getPartie().setEnabled(false);

              this.vue.getPartie().getEtat().setText("<html> C'est FINI! le joueur "+this.jeu.getNonCourant().getNom()+" "+"a gagn\u00e9 !</html>");
              if(this.jeu.getPlateu().checkVainqueur(this.jeu.getCourant())){
                this.vue.getPartie().getEtat().setText("<html>C'est FINI! il y a match nul, bravo aux 2 joueurs</html>");
              }
            }else if(this.jeu.getPlateu().checkVainqueur(this.jeu.getCourant())){
              this.fini=true;
              this.vue.getPartie().setEnabled(false);

              this.vue.getPartie().getEtat().setText("<html> C'est FINI! le joueur "+this.jeu.getCourant().getNom()+" "+"a gagn\u00e9 !</html>");
              if(this.jeu.getPlateu().checkVainqueur(this.jeu.getNonCourant())){
                this.vue.getPartie().getEtat().setText("<html>C'est FINI! il y a match nul, bravo aux 2 joueurs</html>");
              }
            }
          }else{
            this.vue.getPartie().getEtat().setText("<html>Le d\u00e9placement voulue n'est pas possible. Veuillez choisir de nouveau un pion \u00e0 d\u00e9placer</html>");
            this.vue.getPartie().setNotSelected(this.coord[0],this.coord[1],this.jeu.getPlateu().getPlateau()[this.coord[0]][this.coord[1]]);// on déselectionne la case
            if(facile){
              this.vue.getPartie().supprMove(this.move,this.jeu.getPlateu().getPlateau());
            }
            this.vue.setPanel(this.vue.getPartie().getPanel());
          }
          this.joue=false;
        }
      }
    }
  }

  /**
  *mets à jour le texte du label info de Partie
  *pour indiquer à quelle joueur c'est au tour de jouer
  *@param j le joueur à jouer
  */
  private void setTextInfo(Joueur j){
    this.vue.getPartie().getInfo().setText("c'est au tour de :"+j.getNom()+" de couleur :"+j.getListePion().get(0).getCouleur());
  }
}//end
