package jeu.pion;

import java.io.Serializable;
import jeu.Couleur;

/**
*cette classe abstract est un modele pour construire des Pions avec une couleur
*@author Q.Le Lan
*/
public abstract class Pion implements Serializable{

  /**
  *la couleur du pion
  */
  private Couleur couleur;

  /**
  *construteur du Pion
  *@param couleur la couleur du Pion
  *@throws IllegalArgumentException si la couleur est null
  */
  public Pion (Couleur couleur){

    if(couleur==null)throw new IllegalArgumentException("Pions.ne peuvent pas être null");
    this.couleur=couleur;
  }

  /**
  *le getteur de la couleur du Pion
  *@return la couleur du Pion en String
  */
  public Couleur getCouleur(){
    return this.couleur;
  }

  /**
  *permet à chaque sorte de Pion d'avoir sa propre représentation
  */
  public abstract String toString();
}
