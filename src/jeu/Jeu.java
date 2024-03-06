package jeu;

import jeu.*;
import jeu.joueur.*;
import utilitaire.*;
import java.io.Serializable;
import java.util.Scanner;

/**
*cette classe permet de créer un jeu de zen l'initié avec 2 joueur et 1 plateau et un mode de jeux
*@author Q.Le Lan
*/
public class Jeu implements Serializable {

  /**
  *joueur 1 du jeu
  */
  private Joueur joueur1;

  /**
  *joueur 2 du jeu
  */
  private Joueur joueur2;

  /**
  *le joueur en cours
  */
  private Joueur courant;

  /**
  *mode de jeu du jeu
  */
  private Mode modeDeJeu;

  /**
  *plateau du jeu
  */
  private Plateau plateau;


  /**
  *constructeur du jeu
  *@param j1 le nom du premier joueur
  *@param j2 le nom du deuxième joueur
  *@param modeDeJeu le mode de jeu du jeu
  *@throws IllegalArgumentException si les parametres sont null
  */
  public Jeu(String j1, String j2, Mode modeDeJeu){
    if(j1==null || j2== null || modeDeJeu ==null)throw new IllegalArgumentException("Jeu.parametre ne peuvent pas être null");
    if(modeDeJeu==Mode.HH){
      this.joueur1=new JoueurHumain(j1);
      this.joueur2=new JoueurHumain(j2);
    }else{
      this.joueur1=new JoueurHumain(j1);
      this.joueur2=new JoueurIA1();
    }
    this.courant=this.joueur1;
    this.modeDeJeu=modeDeJeu;
    this.plateau=new Plateau(this.joueur1,this.joueur2);
  }

  /**
  *donne le joueur non courant du jeu
  *@return le joueur non courant
  */
  public Joueur getNonCourant(){
    Joueur ret;
    if(this.courant==this.joueur1){
      ret=this.joueur2;
    }else{
      ret=this.joueur1;
    }
    return ret;
  }

  /**
  *changer le joueur courant
  */
  public void changeCourant(){
    if(this.courant==this.joueur1){
      this.courant=this.joueur2;
    }else{
      this.courant=this.joueur1;
    }
  }

  /**
  *getteur du plateau du jeu
  *@return le plateau
  */
  public Plateau getPlateu(){
    return this.plateau;
  }

  /**
  *getteur du mode de jeu du jeu actuel
  *@return le mode de jeu
  */
  public Mode getMode(){
    return this.modeDeJeu;
  }

  /**
  *le getteur du joueur courant
  *@return le joueur courant
  */
  public Joueur getCourant(){
    return this.courant;
  }
}
