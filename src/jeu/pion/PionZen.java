package jeu.pion;

import java.io.Serializable;
import jeu.Couleur;

/**
*cette classe permet de creer un Pion zen avec les dernierès coordonnees du pion Zen
*cette classe est sousclasse de Pion
*@author Q.Le Lan
*/
public class PionZen extends Pion implements Serializable{

  /**
  *dernière coordonnees X du pionZen
  */
  private int lastX;

  /**
  *dernière coordonnees Y du pionZen
  */
  private int lastY;

  /**
  *boolean qui indique si le pion zen est encore en jeu ou non
  */
  private boolean enJeux;

  /**
  *constructeur du pionZen
  *la couleur de se pion sera toujours rouge
  */
  public PionZen(){
    super(Couleur.ROUGE);

    this.enJeux=true;
    this.lastY=-1;
    this.lastX=-1;
  }

  /**
  *permet de set les coordonnees de la dernier position du Pion
  *@param lastX dernière coordonnees X
  *@param lastY dernière coordonnees Y
  *@throws IllegalArgumentException si les argument sont supérieure à 10 ou inférieure à 0
  */
  public void setLastPos(int lastX, int lastY){

    if (lastX<0 || lastX>10)throw new IllegalArgumentException("setLastPos.coordonnees x<0 || x>10"+": "+lastX);
      this.lastX=lastX;

    if(lastY<0 || lastY>10)throw new IllegalArgumentException("setLastPos.coordonnees y<0 || y>10"+": "+lastY);
      this.lastY=lastY;
  }

  /**
  *getteur de lastX
  *@return la dernière coordonnees X
  */
  public int getLastX(){
    return this.lastX;
  }

  /**
  *getteur de lastY
  *@return la dernière coordonnees Y
  */
  public int getLastY(){
    return this.lastY;
  }

  /**
  *setteur du enJeux
  *@param enJeux le nouveau enJeux
  */
  public void setEnJeux(boolean enJeux){
    this.enJeux=enJeux;
  }

  /**
  *le getteur du enJeux
  *@return vrai si le zen est en jeux, faux sinon
  */
  public boolean getEnJeux(){
    return this.enJeux;
  }

  /**
  *permet de représenter le Pion Zen en String
  *@return une String contenant : Z
  */
  public String toString(){
    String ret="Z";
    return ret;
  }
}//end
