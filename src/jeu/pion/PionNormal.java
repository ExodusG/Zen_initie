package jeu.pion;

import jeu.joueur.*;
import java.io.Serializable;
import jeu.Couleur;

/**
*cette classe permet de créer un pion normal qui appartient à un joueur, hérite de la classe Pion
*@author Q.Le Lan
*/
public class PionNormal extends Pion implements Serializable {


  /**
  *constructeur de PionNormal
  *@param couleur la couleur du PionNormal
  */
  public PionNormal (Couleur couleur){
    super(couleur);

  }

  /**
  *permet de représenter le PionNormal sous forme de chaine caractère
  *ici si la couleur est noir alors le String sera : N
  *si c'est blanc alors :B
  *@return B ou N celon la couleur du PionNormal sinon renvoit un String vide :""
  */
  public String toString(){
    String ret="";
    if(this.getCouleur()==Couleur.NOIR){
      ret="N";
    }else {
      ret="B";
    }
    return ret;
  }
}//end
