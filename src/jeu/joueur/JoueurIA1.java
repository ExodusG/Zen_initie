package jeu.joueur;
import java.io.Serializable;
import jeu.Plateau;
import jeu.Case;

/**
*permet de construire un Joueur IA, qui est aléatoire, choisi c'est mouvement de manière aléatoire
*sans réel stratégie
*@author Q.Le Lan
*/
public class JoueurIA1 extends Joueur implements Serializable{

  /**
  *construteur du JoueurIA1
  */
  public JoueurIA1(){
    super("ordinateur");
  }

  /**
  *permet au joueur IA de jouer, fait une recherche dans l'odre d'un de ses pions
  *dans le tableau puis calcules les déplacement possible, puis choisir aléatoirement un déplacement possible
  *@param plat le plateau sur lequel jouer
  *@return un tableau d'entier avec {x1,y1,x2,y2} ou 1 est le pion à bouger et 2 la case cible
  *@throws IllegalArgumentException si le param est null
  */
  public int[] joue(Plateau plat){
    if(plat==null)throw new IllegalArgumentException("JoueurIA1.joue.le parametre ne peux pas être null");
    int[] ret=new int[4];
    try{
      int i=0;
      int j=0;
      int[][] move=new int[4][2];
      boolean trouver=false;
      Case[][] plateau=plat.getPlateau();
      while(i<plateau.length && !trouver){
        j=0;
        while(j<plateau.length && !trouver){
          if(plateau[j][i].estOccupe() && appartient(plateau[j][i].getPion())){
            move=moveDispos(j,i,plat);
            int nbTour=0;
            while(nbTour<8 && !trouver){
              int random=(int)(Math.random()*8);
              if(move[random][0]!=-1){
                ret[0]=j;
                ret[1]=i;
                ret[2]=move[random][0];
                ret[3]=move[random][1];
                trouver=true;
              }
              nbTour++;
            }
          }
          j++;
        }
        i++;
      }
      Thread.sleep(50);
    }catch(InterruptedException e){
      System.out.println(e.getMessage());
    }
    return ret;
  }
}//end
