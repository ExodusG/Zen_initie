package jeu.joueur;

import java.util.Scanner;
import java.io.Serializable;
import jeu.*;
import jeu.pion.PionZen;

/**
*cette classe permet de créer un joueur humain, elle hérite de la classe Joueur
*@author Q.Le Lan
*/
public class JoueurHumain extends Joueur implements Serializable{

  /**
  *construteur de JoueurHumain
  *@param nom nom du joueur
  */
  public JoueurHumain (String nom){
    super(nom);
  }

  /**
  *regarde si les 2 couple de coordonnes décrive bien une ligne droite
  *verticale,horizontal,ou en diagonale
  *@param x1 la position x du premier couple
  *@param y1 la position y du premier couple
  *@param x2 la position x du second couple
  *@param y2 la position y du second couple
  *@return vrai si c'est une ligne droite faux sinon
  *@throws IllegalArgumentException si les parametres sont supérieure à 10 et inférieure à 0
  */
  private boolean checkLigneDroite(int x1,int y1, int x2, int y2){
    boolean ret=false;

    if(x1<0 || x1>10)throw new IllegalArgumentException("checkLigneDroite.parametre non valide x1<0 || x1>10 :"+" "+x1);
    if(y1<0 || y1>10)throw new IllegalArgumentException("checkLigneDroite.parametre non valide y1<0 || y1>10 :"+" "+y1);
    if(x2<0 || x2>10)throw new IllegalArgumentException("checkLigneDroite.parametre non valide x2<0 || x2>10 :"+" "+x2);
    if(y2<0 || y2>10)throw new IllegalArgumentException("checkLigneDroite.parametre non valide y2<0 || y2>10 :"+" "+y2);
    //verticale
    if(x1==x2 && y1!=y2){
      ret=true;
    //horizontal
    }else if(y1==y2 && x1!=x2){
      ret=true;
    //diagonal /
    }else if(x1+y1==x2+y2){
      ret=true;
    //diagonal \
    }else if(Math.abs(x1-y1)==Math.abs(x2-y2)){
      ret=true;
    }
    return ret;
  }

  /**
  *regarde si le nombre de case en ligne droite entre les 2 couples de coordonnes
  *conrespondent bien au nombre de pion présent sur la ligne de déplacement y compris le pion
  * en déplacement
  *@param x1 la position x du premier couple
  *@param y1 la position y du premier couple
  *@param x2 la position x du second couple
  *@param y2 la position y du second couple
  *@param plateau le double tableau représentant le plateau
  *@return vrai si le nombre de déplacement est bon faux sinon
  *@throws IllegalArgumentException si les parametres sont supérieure à 10 et inférieure à 0 ou si le plateau est null
  */
  private boolean checknbCase(int x1,int y1, int x2, int y2, Case[][] plateau){
    boolean ret=false;

    if(x1<0 || x1>10)throw new IllegalArgumentException("checknbCase.parametre non valide x1<0 || x1>10 :"+" "+x1);
    if(y1<0 || y1>10)throw new IllegalArgumentException("checknbCase.parametre non valide y1<0 || y1>10 :"+" "+y1);
    if(x2<0 || x2>10)throw new IllegalArgumentException("checknbCase.parametre non valide x2<0 || x2>10 :"+" "+x2);
    if(y2<0 || y2>10)throw new IllegalArgumentException("checknbCase.parametre non valide y2<0 || y2>10 :"+" "+y2);
    if(plateau==null)throw new IllegalArgumentException("checknbCase.le parametre ne peux pas être null");

    int compteur=0;
    //verticale
    if(x1==x2 && y1!=y2){
      for(int i=0;i<11;i++){
        if(plateau[x1][i].estOccupe()){
          compteur+=1;
        }
      }
      if(compteur==Math.abs(y1-y2)){
        ret=true;
      }
    //horizontal
    }else if(y1==y2 && x1!=x2){
      for(int i=0;i<11;i++){
        if(plateau[i][y1].estOccupe()){
          compteur +=1;
        }
      }
      if(compteur==Math.abs(x1-x2)){
        ret=true;
      }
    //diagonal /
    }else if(x1+y1==x2+y2){
      int xDepart=x1;
      int yDepart=y1;
      while(yDepart!=0 && xDepart<10){
        xDepart++;
        yDepart--;
      }
      if(xDepart<11 && yDepart>-1){
        while(xDepart>=0 && yDepart<=10){
          if(plateau[xDepart][yDepart].estOccupe()){
            compteur+=1;

          }
          xDepart--;
          yDepart++;
        }
        if(compteur==Math.abs(x1-x2)){
          ret=true;
        }
      }
    //diagonale \
    }else if(Math.abs(x1-y1)==Math.abs(x2-y2)){
      int xDepart=x1;
      int yDepart=y1;
      while(xDepart!=0 && yDepart!=0){
        xDepart--;
        yDepart--;
      }
      if(xDepart>-1 && yDepart>-1){
        while(yDepart<=10 && xDepart<=10){
          if(plateau[xDepart][yDepart].estOccupe()){
            compteur+=1;
          }
          xDepart++;
          yDepart++;
        }
        if(compteur==Math.abs(x1-x2)){
          ret=true;
        }
      }
    }
    return ret;
  }

  /**
  *regarde si un déplacement entre 2 couple de coordonnes est valide
  *le déplacement est valide si: c'est en ligne droite, se déplace d'autant de cases
  *que de pions sur la ligne, qu'il ne passe pas au dessus de pions adverse
  *peut déplacer un pion à lui ou le zen
  *si le zen est déplacer il doit etre connecté à un autre prion
  * et ne doit pas revenir sur son ancienne position
  *@param x1 la position x du premier couple
  *@param y1 la position y du premier couple
  *@param x2 la position x du second couple
  *@param y2 la position y du second couple
  *@param plat le plateau du jeu
  *@return vrai si le déplacement est valide faux sinon
  *@throws IllegalArgumentException si les parametres sont supérieure à 10 et inférieure à 0 ou si le plat est null
  */
  public boolean estValide(int x1,int y1, int x2, int y2, Plateau plat){
    boolean deplaceValide =false;
    Case[][] plateau=plat.getPlateau();
    PionZen zen=plat.getZen();
    if(x1<0 || x1>10)throw new IllegalArgumentException("estValide.parametre non valide x1<0 || x1>10 :"+" "+x1);
    if(y1<0 || y1>10)throw new IllegalArgumentException("estValide.parametre non valide y1<0 || y1>10 :"+" "+y1);
    if(x2<0 || x2>10)throw new IllegalArgumentException("estValide.parametre non valide x2<0 || x2>10 :"+" "+x2);
    if(y2<0 || y2>10)throw new IllegalArgumentException("estValide.parametre non valide y2<0 || y2>10 :"+" "+y2);
    if(plat==null)throw new IllegalArgumentException("estValide.le parametre ne peux pas être null");

    //deplacement pour le pion zen
    if(checkObj(plateau,x2,y2)){
      if(plateau[x1][y1].getPion()==zen){
        if(plat.checkZen(x2,y2)){
          if(checkLigneDroite(x1,y1,x2,y2)){
            if(checknbCase(x1,y1,x2,y2,plateau)){
              if(checkObstacle(x1,y1,x2,y2,plat)){
                if(zen.getLastX()!=x2 && zen.getLastY()!=y2){
                  deplaceValide=true;
                }else{
                  System.out.println("on ne peut pas faire revenir le zen en arrière");
                }
              }else{
                System.out.println("il y a obstacle sur le chemin");
              }
            }else{
              System.out.println("il n'y pas assez de case");
            }
          }else{
            System.out.println("ce n'est pas une ligne droite");
          }
        }else{
          System.out.println("le zen n'est pas connecté à son arrivé ");
        }
      //deplacement PionNormal
      }else{
        if(plateau[x1][y1].estOccupe()){
          if(this.appartient(plateau[x1][y1].getPion())){
            if(checkLigneDroite(x1,y1,x2,y2)){
              if(checknbCase(x1,y1,x2,y2,plateau)){
                if(checkObstacle(x1,y1,x2,y2,plat)){
                  deplaceValide=true;
                }else {
                  System.out.println("il y a obstacle sur le chemin");
                }
              }else{
                System.out.println("pas le bon nombre de case");
              }
            }else{
              System.out.println("Ce n'est pas une ligne droite");
            }
          }else{
            System.out.println("ce n'est pas votre pion");
          }
        }else{
          System.out.println("il n'y a pas de pions");
        }
      }
    }
    return deplaceValide;
  }

  /**
  *verifie si il n'y a pas de pion du ce joueur sur le tableau de Case en x, y
  *@param plateau le tableau à vérifier
  *@param x les coords X
  *@param y les coords Y
  *@return vrai il n'y a pas de pion, faux sinon
  *@throws IllegalArgumentException si les params ne sont pas valide (null ou inférieure à 0 ou  supérieure à 10);
  */
  private boolean checkObj(Case[][] plateau,int x,int y){
    if(plateau==null)throw new IllegalArgumentException("JoueurHumain.checkObj.le plateau peut pas être null");
    if(x<0 || x>10)throw new IllegalArgumentException("JoueurHumain.checkObj.parametre non valide x1<0 || x1>10 :"+" "+x);
    if(y<0 || y>10)throw new IllegalArgumentException("JoueurHumain.checkObj.parametre non valide y1<0 || y1>10 :"+" "+y);
    boolean ret=true;
    if(plateau[x][y].estOccupe()){
      if(appartient(plateau[x][y].getPion())){
        ret=false;
        System.out.println("vous ne pouvez pas manger votre propre pion");
      }
    }
    return ret;
  }
}//end
