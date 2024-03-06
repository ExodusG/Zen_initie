package jeu;

import jeu.pion.*;
import java.io.Serializable;

/**
*permet de créer une case avec des coordonnees x et y et peut être occupé par un Pion
*@author Q.Le Lan
*/
public class Case implements Serializable{

  /**
  *les coordonnes x de la case
  */
  private int x;

  /**
  *les coordonnes y de la case
  */
  private int y;

  /**
  *le pion qui occupe la case si elle est occupé
  */
  private Pion occupePar;

  /**
  *le constructeur de Case
  *@param x la position en x de la case
  *@param y la position en y de la case
  *@param occupePar le pion qui occupe la case 
  *@throws IllegalArgumentException si le pion est null ou si x ou y est sup à 12 et inf à 0
  */
  public Case(int x, int y, Pion occupePar){
    if(x<0 || x>12)throw new IllegalArgumentException("Case.coordonnees x<0 || x>12"+": "+x);
      this.x=x;

    if(y<0 || y>12)throw new IllegalArgumentException("Case.coordonnees y<0 || y>12"+": "+y);
      this.y=y;

    if(occupePar==null)throw new IllegalArgumentException("Case.parametrene peuvent pas être null");
      this.occupePar=occupePar;
  }

  /**
  *le constructeur de Case
  *@param x la position x de la case
  *@param y la position y de la case
  *@throws IllegalArgumentException si x ou y est supérieure à 12 et inférieure à 0
  */
  public Case(int x,int y){
    if(x<0 || x>12)throw new IllegalArgumentException("Case.coordonnees x<0 || x>12"+": "+x);
      this.x=x;

    if(y<0 || y>12)throw new IllegalArgumentException("Case.coordonnees y<0 || y>12"+": "+y);
      this.y=y;
  }

  /**
  *regarde si la case est occupé par un Pion
  *@return vrai si elle est occupé, faux sinon
  */
  public boolean estOccupe(){
    boolean ret=false;

    if(this.occupePar!=null){
      ret=true;
    }
    return ret;
  }

  /**
  *le getteur du pion qui occupe la case
  *@return le pion qui occupe la casse sinon null
  */
  public Pion getPion(){
    return this.occupePar;
  }

  /**
  *le setteur du Pion qui occupe la case
  *@param pion le pion qui va occupé la case
  */
  public void setPion(Pion pion){
    this.occupePar=pion;
  }

  /**
  *le getteur de la position X de la case
  *@return la position x
  */
  public int getX(){
    return this.x;
  }

  /**
  *le getteur de la position Y de la case
  *@return la position y
  */
  public int getY(){
    return this.y;
  }

  /**
  *représente la case sous forme de String
  *@return le toString du pion si elle est occupé sinon un espace vide
  */
  public String toString(){
    String ret=" ";
    if(this.occupePar!=null){
      ret=this.occupePar.toString();
    }
    return ret;
  }
}//end
