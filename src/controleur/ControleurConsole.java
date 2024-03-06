package controleur;

import utilitaire.ModeConsole;
import jeu.*;
import jeu.joueur.*;

/**
*le controleur du patern MVC pour le mode console
*intéragie avec le model (jeu) et la vue (modeConsole)
*@author Q.Le Lan
*/
public class ControleurConsole{

  /**
  *le jeu en cour
  */
  private Jeu jeu;

  /**
  *le plateau du jeu en cour
  */
  private Plateau plateau;

  /**
  *le lanceur du mode console, intéragie avec l'utilisateur
  *et lance le jeu en fonction des choix du joueur
  */
  public void modeConsole(){
    int choix=ModeConsole.accueil();
    if(choix==2){
      ModeConsole.afficheRegle();
      modeConsole();
    }else if(choix==1){
      int mode=ModeConsole.choixMode();
      if(mode==1){
        String nom=ModeConsole.choixNom(1);
        String nom2=ModeConsole.choixNom(2);
        this.jeu=new Jeu(nom,nom2,Mode.HH);
        this.plateau=jeu.getPlateu();
        this.boucleHH();
      }else if(mode==2){
        String nom=ModeConsole.choixNom(1);
        this.jeu=new Jeu(nom,"",Mode.HA);
        this.plateau=this.jeu.getPlateu();
        this.boucleHA();
      }else if(mode==3){
        this.jeu=ModeConsole.openPartie();
        this.plateau=this.jeu.getPlateu();
        if(this.jeu.getMode()==Mode.HH){
          this.boucleHH();
        }else{
          this.boucleHA();
        }
      }else{
        modeConsole();
      }
    }
  }

  /**
  *boucle du jeu en mode console HH
  */
  private void boucleHH(){
    boolean arret=false;
    while(!arret){
      ModeConsole.affichageTour(this.jeu.getCourant(),this.plateau);
      Joueur attente=this.jeu.getNonCourant();
      int[] coord=ModeConsole.joue(this.plateau,(JoueurHumain)this.jeu.getCourant());

      while(!this.plateau.deplacePion(coord[0],coord[1],coord[2],coord[3],this.jeu.getCourant(),attente)){
        coord=ModeConsole.joue(this.plateau,(JoueurHumain)this.jeu.getCourant());
      }

      arret=ModeConsole.demandeDeSave(this.jeu);

      if(this.plateau.checkVainqueur(this.jeu.getCourant())){
        if(this.plateau.checkVainqueur(this.jeu.getNonCourant())){
          System.out.println("Il a match nul, les 2 joueurs ont gagné en m\u00eame temps !!!!");
          arret=true;
        }else {
          System.out.println("C'est FINI, le joueur "+this.jeu.getCourant().getNom()+" a gagner ! Bravo à lui!");
        }
      }else if(this.plateau.checkVainqueur(this.jeu.getNonCourant())){
        if(this.plateau.checkVainqueur(this.jeu.getCourant())){
          System.out.println("Il a match nul, les 2 joueurs ont gagné en m\u00eame temps !!!!");
          arret=true;
        }else {
          System.out.println("C'est FINI, le joueur "+this.jeu.getNonCourant().getNom()+" a gagner ! Bravo à lui!");
        }
      }
      this.jeu.changeCourant();
    }
  }

  /**
  *boucle du jeu en mode console HA
  */
  private void boucleHA(){
    boolean arret=false;
    while(!arret){
      ModeConsole.affichageTour(this.jeu.getCourant(),this.plateau);
      Joueur attente=this.jeu.getNonCourant();
      int[] coord=ModeConsole.joue(this.plateau,(JoueurHumain)this.jeu.getCourant());

      while(!this.plateau.deplacePion(coord[0],coord[1],coord[2],coord[3],this.jeu.getCourant(),attente)){
        coord=ModeConsole.joue(this.plateau,(JoueurHumain)this.jeu.getCourant());
      }

      arret=ModeConsole.demandeDeSave(this.jeu);

      if(this.plateau.checkVainqueur(this.jeu.getCourant())){
        if(this.plateau.checkVainqueur(this.jeu.getNonCourant())){
          System.out.println("Il a match nul, les 2 joueurs ont gagné en m\u00eame temps !!!!");
          arret=true;
        }else {
          System.out.println("C'est FINI, le joueur "+this.jeu.getCourant().getNom()+" a gagner ! Bravo à lui!");
        }
      }else if(this.plateau.checkVainqueur(this.jeu.getNonCourant())){
        if(this.plateau.checkVainqueur(this.jeu.getCourant())){
          System.out.println("Il a match nul, les 2 joueurs ont gagné en m\u00eame temps !!!!");
          arret=true;
        }else {
          System.out.println("C'est FINI, le joueur "+this.jeu.getNonCourant().getNom()+" a gagner ! Bravo à lui!");
        }
      }

      if(!arret){
        this.jeu.changeCourant();
        JoueurIA1 j2=(JoueurIA1)this.jeu.getCourant();
        coord=j2.joue(this.plateau);
        this.plateau.deplacePion(coord[0],coord[1],coord[2],coord[3],this.jeu.getCourant(),this.jeu.getNonCourant());

        if(this.plateau.checkVainqueur(this.jeu.getCourant())){
          if(this.plateau.checkVainqueur(this.jeu.getNonCourant())){
            System.out.println("Il a match nul, les 2 joueurs ont gagné en m\u00eame temps !!!!");
            arret=true;
          }else {
            System.out.println("C'est FINI, le joueur "+this.jeu.getCourant().getNom()+" a gagner ! Bravo à lui!");
          }
        }
      }
      this.jeu.changeCourant();
    }
  }
}
