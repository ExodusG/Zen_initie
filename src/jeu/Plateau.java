package jeu;

import jeu.pion.*;
import jeu.joueur.*;
import java.util.ArrayList;
import java.io.Serializable;

/**
*cette classe permet de créer un tableau de jeu ainsi que la manipulation de se tableau
*à partir des cases et des pions. Le tableau à double entrer de Case est gérer de cette manière
*plateau[coordonnes X][coordonnes Y]
*@author Q.Le Lan
*/
public class Plateau implements Serializable {

  /**
  *le plateau en lui meme du jeu, tableau à double entrer de Case
  */
  private Case[][] plateau;

  /**
  *le pion Zen du jeu
  */
  private PionZen zen;

  /**
  *le contructeur de plateau
  *@param j1 le premier joueur
  *@param j2 le deuxième joueur
  *@throws IllegalArgumentException si les parametres sont null
  */
  public Plateau(Joueur j1, Joueur j2){

    if(j1==null || j2==null)throw new IllegalArgumentException("Plateau.parametre ne peuvent pas être null");
      //initialise le tableau
      this.plateau=new Case[11][11];
      for(int i=0;i<11;i++){
        for(int j=0;j<11;j++){
          plateau[i][j]=new Case(i,j);
        }
      }
      //place tout les pions dans les cases
      placement(j1,j2);
  }

  /**
  *permet de créer une liste de Pion Normal, la liste contiendra 11 pions
  *@param couleur la couleur des pions
  *@return une ArrayList de Pion avec tout les pions créer dedans
  *@throws IllegalArgumentException si les parametres sont null
  */
  private ArrayList<PionNormal> creerListePion(Couleur couleur){
    ArrayList<PionNormal> listePion=new ArrayList<PionNormal>();

    if(couleur==null)throw new IllegalArgumentException("creerListePion.parametre ne peuvent pas être null");
    for(int i=0;i<12;i++){
      listePion.add(new PionNormal(couleur));
    }
    return listePion;
  }

  /**
  *place dans le tableau de Case les pions au bon endroit pour le début de la partie
  *@param j1 le premier joueur
  *@param j2 le deuxième joueur
  *@throws IllegalArgumentException si les parametres sont null
  */
  private void placement(Joueur j1, Joueur j2){

    if(j1==null || j2==null)throw new IllegalArgumentException("placement.parametre ne peuvent pas être null");
    //creation de la liste de pion pour j1

    ArrayList<PionNormal> listeJ1= creerListePion(Couleur.BLANC);
    j1.setListePion(listeJ1);

    //creation de la liste de pion pour j2
    ArrayList<PionNormal> listeJ2=creerListePion(Couleur.NOIR);
    j2.setListePion(listeJ2);

    //place les pions de j1
    this.plateau[0][0].setPion(listeJ1.get(0));
    this.plateau[4][1].setPion(listeJ1.get(1));
    this.plateau[6][1].setPion(listeJ1.get(2));
    this.plateau[2][3].setPion(listeJ1.get(3));
    this.plateau[8][3].setPion(listeJ1.get(4));
    this.plateau[0][5].setPion(listeJ1.get(5));
    this.plateau[10][5].setPion(listeJ1.get(6));
    this.plateau[2][7].setPion(listeJ1.get(7));
    this.plateau[8][7].setPion(listeJ1.get(8));
    this.plateau[4][9].setPion(listeJ1.get(9));
    this.plateau[6][9].setPion(listeJ1.get(10));
    this.plateau[10][10].setPion(listeJ1.get(11));

    //place les pions de j2
    this.plateau[5][0].setPion(listeJ2.get(0));
    this.plateau[10][0].setPion(listeJ2.get(1));
    this.plateau[3][2].setPion(listeJ2.get(2));
    this.plateau[7][2].setPion(listeJ2.get(3));
    this.plateau[1][4].setPion(listeJ2.get(4));
    this.plateau[9][4].setPion(listeJ2.get(5));
    this.plateau[1][6].setPion(listeJ2.get(6));
    this.plateau[9][6].setPion(listeJ2.get(7));
    this.plateau[3][8].setPion(listeJ2.get(8));
    this.plateau[7][8].setPion(listeJ2.get(9));
    this.plateau[0][10].setPion(listeJ2.get(10));
    this.plateau[5][10].setPion(listeJ2.get(11));

    //place le zen
    this.zen = new PionZen();
    this.plateau[5][5].setPion(zen);
  }


  /**
  *deplace le pion de joueur, NE VERIFIE PAS LA VALIDITE DU DEPLACEMENT (règle)
  *si en x2,y2 il a un pion de j2 alors le pion est mangé et retiré du jeu
  *@param x1 la position x du premier couple(pion à déplacer)
  *@param y1 la position y du premier couple
  *@param x2 la position x du second couple(case cible)
  *@param y2 la position y du second couple
  *@param joueur le joueur qui est entrain de jouer son tour
  *@param j2 le joueur adverse de joueur
  *@return vrai si le déplacement a été effectuer faux sinon
  *@throws IllegalArgumentException si les parametres sont supérieure à 10 et inférieure à 0 ou si le joueur est null
  */
  public boolean deplacePion(int x1,int y1, int x2,int y2,Joueur joueur,Joueur j2){
    boolean deplace=false;

    if(x1<0 || x1>10)throw new IllegalArgumentException(" deplacePion.parametre non valide x1<0 || x1>10 :"+" "+x1);
    if(y1<0 || y1>10)throw new IllegalArgumentException(" deplacePion.parametre non valide y1<0 || y1>10 :"+" "+y1);
    if(x2<0 || x2>10)throw new IllegalArgumentException(" deplacePion.parametre non valide x2<0 || x2>10 :"+" "+x2);
    if(y2<0 || y2>10)throw new IllegalArgumentException(" deplacePion.parametre non valide y2<0 || y2>10 :"+" "+y2);
    if(joueur==null)throw new IllegalArgumentException(" deplacePion.parametre ne peuvent pas être null");
    if(j2==null)throw new IllegalArgumentException(" deplacePion.parametre ne peuvent pas être null");
    //si il a un pion adverse sur la case cible
    if(this.plateau[x2][y2].estOccupe()){
      if(!joueur.appartient(this.plateau[x2][y2].getPion())){
          Pion deplacer=this.plateau[x1][y1].getPion();
          this.plateau[x1][y1].setPion(null);
          if(this.plateau[x2][y2].getPion()==this.zen){
            this.zen.setEnJeux(false);
          }
          j2.getListePion().remove(this.plateau[x2][y2].getPion());//retire le pion mangé du jeu
          this.plateau[x2][y2].setPion(deplacer);
          deplace=true;
          if(deplacer==this.zen){
            this.zen.setLastPos(x1,y1);
          }
      }
    //si la case cible est libre
    }else if(!this.plateau[x2][y2].estOccupe()){
        Pion deplacer=this.plateau[x1][y1].getPion();
        this.plateau[x1][y1].setPion(null);
        this.plateau[x2][y2].setPion(deplacer);
        deplace=true;
        if(deplacer==this.zen){
          this.zen.setLastPos(x1,y1);
        }
    }
    return deplace;
  }

  /**
  *regarde si le pion zen est connecté a un autre pion autour des 8cases qui entoure la position du zen
  *@param x les coordonnes x du zen
  *@param y les coordonnes y du zen
  *@return vrai le zen est connecté faux sinon
  *@throws IllegalArgumentException si les parametres sont supérieure à 12 et inférieure à 0 ou si le pion n'est pas celui du zen
  */
  public boolean checkZen(int x, int y){
    boolean ret=false;

    if(x<0 || x>10)throw new IllegalArgumentException("checkZen.parametre non valide x<0 || x>10 :"+" "+x);
    if(y<0 || y>10)throw new IllegalArgumentException("checkZen.parametre non valide y<0 || y>10 :"+" "+y);

    if(verifCoord(x-1,y-1) && this.plateau[x-1][y-1].estOccupe()){
      ret=true;
    }else if (verifCoord(x,y-1) && this.plateau[x][y-1].estOccupe()){
      ret = true;
    }else if(verifCoord(x+1,y-1) && this.plateau[x+1][y-1].estOccupe()){
      ret = true;
    }else if(verifCoord(x-1,y) && this.plateau[x-1][y].estOccupe()){
      ret = true;
    }else if(verifCoord(x+1,y) && this.plateau[x+1][y].estOccupe()){
      ret = true;
    }else if(verifCoord(x-1,y+1) && this.plateau[x-1][y+1].estOccupe()){
      ret = true;
    }else if(verifCoord(x,y+1) && this.plateau[x][y+1].estOccupe()){
      ret = true;
    }else if(verifCoord(x+1,y+1) && this.plateau[x+1][y+1].estOccupe()){
      ret=true;
    }
    return ret;
  }

  /**
  *regarde si le joueur à gagner
  *il gagne lorsque tous ses pions sont connecté y compris le zen si il est encore en jeu
  *@param joueur le joueur à regarder
  *@return true si le joueur a gagner, faux sinon
  *@throws IllegalArgumentException si le joueur est null
  */
  public boolean checkVainqueur(Joueur joueur){
    if(joueur==null)throw new IllegalArgumentException("checkVainqueur.le parametre ne peut pas être null");
    boolean vainqueur = false;
    int x=0;
    int y=0;
    boolean trouver=false;
    while (x<11 && !trouver){
      y=0;
      while(y<11 && !trouver){
          Pion pion =this.plateau[x][y].getPion();
        if(pion!=null && joueur.appartient(pion)){
          trouver=true;
        }
        y++;
      }//fin 1er boucle
      x++;
    }//fin 2 eme boucles

    ArrayList<Pion> listPion=new ArrayList<Pion>(joueur.getListePion());
    if(this.zen.getEnJeux()){
      listPion.add(this.zen);
    }
    listPion=recherche(x-1,y-1,joueur,listPion);

    if(listPion.size()==0){
      vainqueur=true;
    }
    return vainqueur;
  }

  /**
  *methode recursif qui regarde si un pions est connecté dans les 8 cases au alentour
  *si il est connecté on l'enleve de la liste, cette méthode permet de tester toute les possibilité possible
  *à partir d'un pion car elle est récursive
  *@param x le x du pion
  *@param y le y du pion
  *@param joueur le joueur au quel le pion appartient
  *@param pionMarque copie de la listePion du joueur
  *@return la liste contenant les pions qui ne sont pas encore trouvé et connecté
  *@throws IllegalArgumentException si les param ne sont pas valide
  */
  private ArrayList<Pion> recherche(int x,int y, Joueur joueur,ArrayList<Pion> pionMarque){
    if(joueur==null ||pionMarque ==null)throw new IllegalArgumentException("recherche.les params ne peuvent pas être null");
    if(x<0 || x>10)throw new IllegalArgumentException("recherche.parametre non valide x<0 || x>10 :"+" "+x);
    if(y<0 || y>10)throw new IllegalArgumentException("recherche.parametre non valide y<0 || y>10 :"+" "+y);

    if(verifCoord(x-1,y-1) && this.plateau[x-1][y-1].estOccupe()){
      Pion suivant=this.plateau[x-1][y-1].getPion();
      if((joueur.appartient(suivant) || suivant==this.zen ) && pionMarque.contains(suivant)){
        pionMarque.remove(suivant);
        pionMarque=recherche(x-1,y-1,joueur,pionMarque);
      }
    } if (verifCoord(x,y-1) && this.plateau[x][y-1].estOccupe()){
      Pion suivant=this.plateau[x][y-1].getPion();
      if((joueur.appartient(suivant) || suivant==this.zen )&& pionMarque.contains(suivant)){
        pionMarque.remove(suivant);
        pionMarque=recherche(x,y-1,joueur,pionMarque);
      }
    }if(verifCoord(x+1,y-1) && this.plateau[x+1][y-1].estOccupe()){
      Pion suivant=this.plateau[x+1][y-1].getPion();
      if((joueur.appartient(suivant) || suivant==this.zen )&& pionMarque.contains(suivant)){
        pionMarque.remove(suivant);
        pionMarque=recherche(x+1,y-1,joueur,pionMarque);
      }
    } if(verifCoord(x-1,y) && this.plateau[x-1][y].estOccupe()){
      Pion suivant=this.plateau[x-1][y].getPion();
      if((joueur.appartient(suivant) || suivant==this.zen )&& pionMarque.contains(suivant)){
        pionMarque.remove(suivant);
        pionMarque=recherche(x-1,y,joueur,pionMarque);
      }
    } if(verifCoord(x+1,y) && this.plateau[x+1][y].estOccupe()){
      Pion suivant=this.plateau[x+1][y].getPion();
      if((joueur.appartient(suivant) || suivant==this.zen )&& pionMarque.contains(suivant)){
        pionMarque.remove(suivant);
        pionMarque=recherche(x+1,y,joueur,pionMarque);
      }
    } if(verifCoord(x-1,y+1) && this.plateau[x-1][y+1].estOccupe()){
      Pion suivant=this.plateau[x-1][y+1].getPion();
      if((joueur.appartient(suivant) || suivant==this.zen )&& pionMarque.contains(suivant)){
        pionMarque.remove(suivant);
        pionMarque=recherche(x-1,y+1,joueur,pionMarque);
      }
    } if(verifCoord(x,y+1) && this.plateau[x][y+1].estOccupe()){
      Pion suivant=this.plateau[x][y+1].getPion();
      if((joueur.appartient(suivant) || suivant==this.zen )&& pionMarque.contains(suivant)){
        pionMarque.remove(suivant);
        pionMarque=recherche(x,y+1,joueur,pionMarque);
      }
    } if(verifCoord(x+1,y+1) && this.plateau[x+1][y+1].estOccupe()){
      Pion suivant=this.plateau[x+1][y+1].getPion();
      if((joueur.appartient(suivant) || suivant==this.zen )&& pionMarque.contains(suivant)){
        pionMarque.remove(suivant);
        pionMarque=recherche(x+1,y+1,joueur,pionMarque);
      }
    }
    return pionMarque;
  }

  /**
  *regarde si les coordonnes sont valide
  *@param x le x à regarder
  *@param y le y à regarder
  *@return true si les coordonnes sont bonnes, false sinon
  */
  private boolean verifCoord(int x, int y){
    boolean ret=true;
    if(x<0 || x>=11){
      ret=false;
    }
    if(y<0 || y>=11){
      ret=false;
    }
    return ret;
  }

  /**
  *le getteur du pion Zen
  *@return le pion zen
  */
  public PionZen getZen(){
    return this.zen;
  }

  /**
  *le getteur du tableau en 2d de case
  *@return le tableau en 2d
  */
  public Case[][] getPlateau(){
    return this.plateau;
  }

  /**
  *transforme le plateau en un String
  *@return le plateau en String
  */
  public String toString(){
    String ret="";
    ret="\t"+"  "+"A"+"   B"+"   C"+"   D"+"   E"+"   F"+"   G"+"   H"+"   I"+"   J"+"   K"+"\n";
    for(int i=0;i<11;i++){
      ret+=i+"\t"+"|";

      for(int j=0;j<11;j++){
        ret+=" "+plateau[j][i].toString()+" "+"|";
      }
      ret+="\n";
    }
    return ret;
  }
}//end
