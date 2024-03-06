package jeu.joueur;

import jeu.pion.*;
import java.util.ArrayList;
import java.io.Serializable;
import jeu.Plateau;
import jeu.Case;

/**
*cette classe abstract permet de créer un model de joueur avec sa liste de Pion ainsi que son nom
*@author Q.Le Lan
*/
public abstract class Joueur implements Serializable {

  /**
  *le nom du joueur
  */
  private String nom;

  /**
  *la liste de Pion du joueur
  */
  private ArrayList<PionNormal> listePion;

  /**
  *le construteur de Joueur qui défini QUE le nom
  *@param nom le nom du joueur
  *@throws IllegalArgumentException si le nom est null
  */
  public Joueur(String nom){
    if(nom==null)throw new IllegalArgumentException("Joueur.parametre ne peuvent pas être null");

    this.nom=nom;
    this.listePion = new ArrayList<PionNormal>();
  }

  /**
  *set la liste de Pion du joueur
  *@param liste la liste à donner au joueur
  *@throws IllegalArgumentException si le parametre est null
  */
  public void setListePion(ArrayList<PionNormal> liste){
    if(liste==null)throw new IllegalArgumentException("setListePion.parametre ne peuvent pas être null");
    if(liste.size()!=12)throw new IllegalArgumentException("setListePion.la liste ne peut contenir que exactement 12"+": "+ liste.size());
    this.listePion=liste;
  }

  /**
  *getteur de la liste de Pion du joueur
  *@return la liste de Pion dans une ArrayList
  */
  public ArrayList<PionNormal> getListePion(){
    return this.listePion;
  }

  /**
  *getteur du nom du joueur
  *@return le nom du joueur
  */
  public String getNom(){
    return this.nom;
  }

  /**
  *regarde si un Pion est bien dans la listePion du joueur
  *@param pion le pion à regardé s'il appartient bien au joueur
  *@return vrai si il appartient faux sinon
  *@throws IllegalArgumentException si le parametre est null
  */
  public boolean appartient(Pion pion){
    boolean ret=false;
    if(pion==null)throw new IllegalArgumentException("appartient.parametre ne peuvent pas être null");

    if(listePion.contains(pion)){
      ret=true;
    }
    return ret;
  }

  /**
  *regarde si il a un pion adverse au pion appartenant à ce joueur est sur le chemin entre
  *les 2 couples de coordonnes
  *@param x1 la position x du premier couple
  *@param y1 la position y du premier couple
  *@param x2 la position x du second couple
  *@param y2 la position y du second couple
  *@param plat le plateau du jeu
  *@throws IllegalArgumentException si les parametres sont supérieure à 10 et inférieure à 0 ou si le plat est null
  *@return vrai s'il n'y a pas de pion sur le chemin faux sinon
  */
  public boolean checkObstacle(int x1,int y1, int x2, int y2, Plateau plat){
    boolean ret=true;
    Case[][] plateau=plat.getPlateau();
    PionZen zen=plat.getZen();
    if(x1<0 || x1>10)throw new IllegalArgumentException("checkObstacle.parametre non valide x1<0 || x1>10 :"+" "+x1);
    if(y1<0 || y1>10)throw new IllegalArgumentException("checkObstacle.parametre non valide y1<0 || y1>10 :"+" "+y1);
    if(x2<0 || x2>10)throw new IllegalArgumentException("checkObstacle.parametre non valide x2<0 || x2>10 :"+" "+x2);
    if(y2<0 || y2>10)throw new IllegalArgumentException("checkObstacle.parametre non valide y2<0 || y2>10 :"+" "+y2);

    int element=0;
    int element2=0;
    //verticale
      if(x1==x2 && y1!=y2){
        element=Math.min(y1,y2);
        element2=0;
        if(element==y1){
          element2=y2;
        }else{
          element2=y1;
        }
        for(int i=element+1;i<element2;i++){
          if(plateau[x1][i].estOccupe()){
            if(!this.appartient(plateau[x1][i].getPion()) && plateau[x1][i].getPion()!=zen){
              ret=false;
            }
          }
        }
      //horizontal
      }else if(y1==y2 && x1!=x2){
        element=Math.min(x1,x2);
        if(element==x1){
          element2=x2;
        }else{
          element2=x1;
        }
        for(int i=element+1;i<element2;i++){
          if(plateau[i][y1].estOccupe()){
            if(!this.appartient(plateau[i][y1].getPion())&& plateau[i][y1].getPion()!=zen){
              ret=false;
            }
          }
        }
      //diagonal /
      }else if(x1+y1==x2+y2){
        element=Math.max(x1,x2);
        element2=Math.min(x1,x2);
        int maxY=Math.max(y1,y2);
        maxY--;
        for(int i=element2+1;i<element;i++){
          if(plateau[i][maxY].estOccupe()){
            if(!this.appartient(plateau[i][maxY].getPion())&& plateau[i][maxY].getPion()!=zen){
              ret=false;
            }
          }
          maxY--;
        }
      //diagonal \
      }else if(Math.abs(x1-y1)==Math.abs(x2-y2)){
        element=Math.max(x1,x2);
        element2=Math.min(x1,x2);
        int minY=Math.min(y1,y2);
        minY++;
        for(int i=element2+1;i<element;i++){
          if(plateau[i][minY].estOccupe()){
            if(!this.appartient(plateau[i][minY].getPion())&& plateau[i][minY].getPion()!=zen){
              ret=false;
            }
          }
          minY++;
        }
      }
    return ret;
  }

  /**
 *Renvoie un tableau avec les déplacements possible du pion en x,y
 *@param x le x du départ
 *@param y le y du départ
 *@param plat le plateau dans lequel il faut se déplacer
 *@return un tableau d'entiers à 2 dimensions comprenant les déplacements possibles
 *[][0]=x [][1]=y, les déplacement pas possible sont remplacé par des -1
 *@throws IllegalArgumentException si les param ne sont pas valide (null ou inférieure à 0 ou supérieure à 10)
 */
	public int[][] moveDispos(int x, int y, Plateau plat) {
    if(plat==null)throw new IllegalArgumentException("Joueur.moveDispos.le plateau peut pas être null");
    if(x<0 || x>10)throw new IllegalArgumentException("Joueur.moveDispos.parametre non valide x1<0 || x1>10 :"+" "+x);
    if(y<0 || y>10)throw new IllegalArgumentException("Joueur.moveDispos.parametre non valide y1<0 || y1>10 :"+" "+y);
    Case[][] plateau=plat.getPlateau();
		int[][] ret = null;
		int coordX = x;
    int coordY = y;
    ret = new int[8][2];
    //pour verticale
    int sumVert = 0;
    for (int i = 0; i < plateau.length ; i ++) {
      if (plateau[coordX][i].estOccupe()){
        sumVert ++;
      }
    }

    ret[0][0] = coordX;
    ret[0][1] = coordY + sumVert;
    ret[1][0] = coordX;
    ret[1][1] = coordY - sumVert;
    //pour horizontal
    int sumHori = 0;
    for (int j = 0; j < plateau[0].length ; j ++) {
      if (plateau[j][coordY].estOccupe()) {
        sumHori ++;
      }
    }

    ret[2][0] = coordX + sumHori;
    ret[2][1] = coordY;
    ret[3][0] = coordX - sumHori;
    ret[3][1] = coordY;

    int sumDiagGD = 0;
    int k = 0;
    int cx = coordX;
    int cy = coordY;
    //pour la diagonal /
    while(cy>0 && cx<10) {
      cx ++;
      cy --;
    }
    while(cx>=0 && cy<=10) {
      if (plateau[cx][cy].estOccupe()) {
        sumDiagGD ++;
      }
      cx --;
      cy ++;
    }

    ret[4][0] = coordX + sumDiagGD;
    ret[4][1] = coordY - sumDiagGD;
    ret[5][0] = coordX - sumDiagGD;
    ret[5][1] = coordY + sumDiagGD;


    int sumDiagDG = 0;
    k = 0;
    cx = coordX;
    cy = coordY;
    //pour la diagonal \
    while(cx != 0 && cy != 0) {
      cx --;
      cy --;
    }
     while(cy <=10 && cx <= 10) {
      if (plateau[cx][cy].estOccupe()) {
        sumDiagDG ++;
      }
      cx ++;
      cy ++;
    }

    ret[6][0] = coordX + sumDiagDG;
    ret[6][1] = coordY + sumDiagDG;
    ret[7][0] = coordX - sumDiagDG;
    ret[7][1] = coordY - sumDiagDG;

    for(int i=0;i<8;i++){
      int checkX=ret[i][0];
      int checkY=ret[i][1];
      if(checkX<0 ||checkX>10){
        ret[i][0]=-1;
        ret[i][1]=-1;
      }else if(checkY<0 ||checkY>10){
        ret[i][0]=-1;
        ret[i][1]=-1;
      }else if(!checkObstacle(x,y,checkX,checkY,plat)){
        ret[i][0]=-1;
        ret[i][1]=-1;
      }else if(plat.getPlateau()[checkX][checkY].estOccupe() && this.appartient(plat.getPlateau()[checkX][checkY].getPion())){
        ret[i][0]=-1;
        ret[i][1]=-1;
      }
    }
    return ret;
	}
}//end
