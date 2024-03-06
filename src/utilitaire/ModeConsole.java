package utilitaire;

import java.util.Scanner;
import java.util.ArrayList;
import jeu.*;
import jeu.joueur.*;
import jeu.pion.*;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
*cette classe contient des méthode utile pour le mode console comme par exemple le choix du mode de jeu,
le choix du nom, etc...
*@author Q.Le Lan
*/
public class ModeConsole {

  /**
  *demande au joueur qu'elle action il souhaite effectuer parmis : (1)jouer, (2)regles, (3)quitter
  *@return le numéro correspondant au choix du joueur
  */
  public static int accueil() {
    int ret=0;
    System.out.println();
    System.out.println("////////////////////////////////////////////////////////////");
    System.out.println("///////////Bienvenue dans le jeu : Zen l'initi\u00e9/////////////");
    System.out.println("////////////////////////////////////////////////////////////");
    System.out.println();
    System.out.println("Ce logiciel est une adaption du c\u00e9l\u00e8bre jeu de plateau qui porte le m\u00eame nom, sorti en 1997. Vous pouvez jouer \u00e0 2 (1vs1) ou jouer contre l'IA");
    System.out.println("Vous \u00eates actuellement dans le mode console, il existe aussi un mode graphique, je vous encourage \u00e0 le tester aussi");
    System.out.println("rentrez le num\u00e9ro de votre choix :");
    System.out.println("1 --- jouer");
    System.out.println("2 --- r\u00e8gles");
    System.out.println("3 --- quitter");
    System.out.println();

    Scanner scan=new Scanner(System.in);
    System.out.print("Saisie : ");
    String choix=scan.nextLine();
    while(!choix.equals("1") && !choix.equals("2") && !choix.equals("3")){
      System.out.println("veuillez saisir 1,2 ou 3 en fonction de votre choix");
      System.out.print("Saisie : ");
      choix=scan.nextLine();
    }
    if(choix.equals("1")){
      ret=1;
    }else if(choix.equals("2")){
      ret=2;
    }else{
      ret=3;
    }
    return ret;
  }

  /**
  *demande au joueur de choisir un mode de jeu parmi : (1)multijoueur, (2)contre Ia, (3)charger une Partie ou encore (4)retour
  *@return le numéro correspondant au choix du joueur
  */
  public static int choixMode(){
    int ret=0;
    System.out.println();
    System.out.println("veuillez rentrer le num\u00e9ro correspondant \u00e0 votre choix");
    System.out.println();
    System.out.println("1 --- multijoueur");
    System.out.println("2 --- contre IA");
    System.out.println("3 --- charger une partie");
    System.out.println("4 --- retour");
    System.out.println();

    Scanner scan=new Scanner(System.in);
    System.out.print("Saisie : ");
    String choix=scan.nextLine();
    while(!choix.equals("1") && !choix.equals("2") && !choix.equals("3") && !choix.equals("4")){
      System.out.println("veuillez saisir 1,2,3 ou 4 en fonction de votre choix");
      System.out.print("Saisie : ");
      choix=scan.nextLine();
    }
    if(choix.equals("1")){
      ret=1;
    }else if(choix.equals("2")){
      ret=2;
    }else if(choix.equals("3")){
      ret=3;
    }else{
      ret=4;
    }
    return ret;
  }

  /**
  *demande au joueur de saisir son nom et confirmation si c'est bien le nom saisie qu'il souhaite garder
  *@param numJoueur le numéro du Joueur qui doit choisir sont nom
  *@return le nom du joueur en String
  */
  public static String choixNom(int numJoueur){
    System.out.println();
    String ret="";

    boolean bon=false;
    while(bon==false){

      System.out.println("veuillez rentrer le nom du joueur"+" "+numJoueur);
      System.out.print("Saisie : ");
      Scanner scan=new Scanner(System.in);
      String nom = scan.nextLine();
      System.out.println();
      //demande la confirmation du nom
      System.out.println("\u00eates vous s\u00fbr de garder"+ " "+nom+" "+"comme nom de joueur");
      System.out.println();
      System.out.println("o pour oui, n pour non");
      System.out.print("Saisie : ");
      String choix=scan.nextLine();

      while(!choix.equals("o") && !choix.equals("n")){
        System.out.println();
        System.out.println("o pour oui et n pour non");
        System.out.print("Saisie : ");
        choix=scan.nextLine();
      }
      if(choix.equals("o")){
        bon=true;
        ret=nom;
        System.out.println();
        System.out.println("le nom du joueur"+" "+numJoueur+" "+"est"+" "+nom);
      }
    }
    return ret;
  }

  /**
  *permet l'affichage d'un tour de jeu
  *@param joueur le joueur qui doit jouer
  *@param plateau le plateau qui est en jeu
  */
  public static void affichageTour(Joueur joueur,Plateau plateau){

    if(joueur==null)throw new IllegalArgumentException("affichageTour.le joueur est null");
    if(plateau==null)throw new IllegalArgumentException("affichageTour.le plateau est null");
    ArrayList<PionNormal> pion=joueur.getListePion();
    Couleur color=pion.get(0).getCouleur();
    String colorString=turnInString(color);
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("=============================================");
    System.out.println("==========c'est au joueur "+joueur.getNom()+"==========");
    System.out.println("=============de couleur :"+colorString+"===============");
    System.out.println("=============================================");
    System.out.println();
    System.out.println(plateau.toString());
  }

  /**
  *permet de tranformer l'enum Couleur en un String représentant cette couleur
  *@param color la couleur à transformer en String
  *@return le String représentant la couleur
  *@throws IllegalArgumentException si la couleur est null
  */
  private static String turnInString(Couleur color){
    String ret="";
    if(color==null)throw new IllegalArgumentException("turnInString.la couleur est null");

    if(color==Couleur.NOIR){
      ret="noir";
    }else if(color==Couleur.BLANC){
      ret="blanc";
    }
    return ret;
  }

  /**
  *permet de sauvegarder une partie en utilisant Serializable
  *demande au joueur où il souhaite enregistrer sa partie
  *@param jeu le jeu à sauvegarder
  *@throws IllegalArgumentException si le jeu est null
  */
  public static void sauvePartie(Jeu jeu){
    ObjectOutputStream oos=null;
    try{
      if(jeu==null)throw new IllegalArgumentException("ModeConsole.sauvePartie.le param est null");

      System.out.println();
      System.out.println("entrez le chemin ainsi que le nom du fichier(sans l'extension) o\u00f9 vous voulez enregistrer votre partie");
      System.out.print("Saisie : ");
      Scanner scan=new Scanner(System.in);
      String nom = scan.nextLine();
      File fileToSave=new File(nom+".ze");
      oos =  new ObjectOutputStream(new FileOutputStream(fileToSave)) ;
      oos.writeObject(jeu);
    }catch(FileNotFoundException e){
      System.err.println(e.getMessage());
    }catch(IOException e){
      System.err.println(e.getMessage());
    }finally {
      try{
        if(oos!=null){
          oos.close();
        }
      }catch(IOException e){
        System.err.println(e.getMessage());
      }
    }
  }

  /**
  *permet d'ouvrire une partie (une instance de la class Jeu)
  *demande au joueur où se trouve la partie
  *@return le jeu créer avec le fichier de sauvegarde
  */
  public static Jeu openPartie(){
    ObjectInputStream ois=null;
    Jeu jeu1=null;
    try{
      System.out.println();
      System.out.println("entrez le chemin o\u00f9 ce trouve la partie enregistr\u00e9e");
      Scanner scan=new Scanner(System.in);
      String nom = scan.nextLine();
      File save=new File(nom);
      boolean format=false;
      if(nom.contains(".ze")){
        format=true;
      }
      while(!save.exists() && !format){
        System.out.println();
        System.out.println("Le chemin n'est pas le bon ou le format(ze) est pas bon ! Entrez le chemin o\u00f9 ce trouve partie enregistr\u00e9e");
        System.out.print("Saisie : ");
        nom = scan.nextLine();
        save=new File(nom);
      }
      ois =  new ObjectInputStream(new FileInputStream(save)) ;
      jeu1 = (Jeu)ois.readObject();
    }catch(IOException | ClassNotFoundException e){
      System.err.println(e.getMessage());
    }finally{
      try{
        if(ois!=null){
          ois.close();
        }
      }catch(IOException e){
        System.err.println(e.getMessage());
      }
      return jeu1;
    }
  }

  /**
  *demande au joueur ce qu'il veut faire (save, quitter ou continuer)
  *@param jeu le jeu en cours
  *@return true si le joueur souhaite arreter la partie
  *@throws IllegalArgumentException si le jeu est null
  */
  public static boolean demandeDeSave(Jeu jeu){
    if(jeu==null)throw new IllegalArgumentException("demandeDeSave. le param ne peut pas etre null");

    boolean ret=false;
    Scanner scan=new Scanner(System.in);
    System.out.println("Si vous voulez enregistrer votre partie : \"save\", pour quitter \"quitter\" et si vous voulez enregistrer puis quitter : \"fin\" \t sinon entrer aute chose");
    System.out.print("Saisie : ");
    String nom = scan.nextLine();
    if(nom.equals("save")){
      ModeConsole.sauvePartie(jeu);
    }else if(nom.equals("quitter")){
      ret=true;
    }else if(nom.equals("fini")){
      ModeConsole.sauvePartie(jeu);
      ret=true;
    }
    return ret;
  }

  /**
  *affiche dans la console les regles du jeu
  */
  public static void afficheRegle(){
    System.out.println();
    System.out.println("-----------------Voici les r\u00e8gles du jeu--------------------");

    System.out.println();
    System.out.println("** 2 joueurs s'affrontent, le but \u00e9tant de former une chaine avec tous ses pions y compris le zen s'il y est encore en jeu**");
    System.out.println("** Les pions sont d\u00e9pla\u00e7ables seulement :");
    System.out.println("** -Si vous le d\u00e9placer d'autant de case qu'il y a de pions sur la ligne de d\u00e9placement (y compris lui-m\u00eame)");
    System.out.println("** -Si vous le d\u00e9placer en ligne droite, vous pouvez donc le d\u00e9placer dans les 8 directions possibles");
    System.out.println("** -Vous ne pouvez pas passer au dessus d'un pion adverse **");
    System.out.println();
    System.out.println("** Il existe un pion sp\u00e9ciale, le pion Zen, tout le monde peut le d\u00e9placer mais il doit \u00eatre connect\u00e9 \u00e0 un pion \u00e0 son arriver**");
    System.out.println("** S'il y a un pion sur la case o\u00f9 vous vous d\u00e9placer vous mangez le pion adverse (ou le zen), et celui-ci est retir\u00e9 du jeu **");
    System.out.println();
    System.out.println("** Pour toutes autres informations consultez le pdf \"r\u00e8gles\" ");
  }


  /**
  *permet au joueur humain de joué, demande d'abors les coordonnees du pion à bouger dans la console
  *puis si la forme est valide demande dans la console les coordonnees de la case cible
  *@param plat le plateau sur lequel jouer
  *@param joueur le joueur qui doit jouer
  *@return un tableau d'entier avec {x1,y1,x2,y2} ou 1 est le pion à bouger et 2 la case cible
  */
  public static int[] joue(Plateau plat, JoueurHumain joueur){
    int[] ret=new int[2];
    boolean valide=false;
    while(!valide){
      ret=demandeCoord();
      if(joueur.estValide(ret[0],ret[1],ret[2],ret[3],plat)){
        valide=true;
      }
    }
    return ret;
  }

  /**
  *regarde si une lettre est bien comprise netre a-k ou A-K
  *@param a le char à regardé
  *@return vrai si au bont format faux sinon
  */
  public static boolean checkCoordLettre(char a){
    boolean ret=false;
    String coord=""+a;
    if(coord.matches("[a-k]|[A-K]")){
      ret=true;
    }
    return ret;
  }

  /**
  *demande à l'utilisateur les coordonnees du pion qu'il veut déplacer et les coordnnées de la case cible
  *@return un tableau avec les coordonnées [0]=x1 [1]=y1 [2]=x2 [3]=y2
  */
  public static int[] demandeCoord(){
    System.out.println();
    System.out.println("entrez les coordonn\u00e9es du pion que vous voulez d\u00e9placer");
    System.out.println(("les coordonn\u00e9es doivent \u00eatre au format:lettre,chiffre"));
    Scanner scan=new Scanner(System.in);
    //premiere coord
    System.out.print("Saisie : ");
    String choix=scan.nextLine();
    int[] tab=new int[4];
    //vérif du bon format des coord
    while(!checkCoordLettre(choix.charAt(0))||!checkCoordChiffre(choix)){
      System.out.println("le format n'est pas le bon, donner une lettre entre A et K et un chiffre entre 0 et 10");
      System.out.print("Saisie : ");
      choix=scan.nextLine();
    }

    int x1=turnInNumber(choix.charAt(0));
    int y1=turnInInt(choix);

    System.out.println();
    System.out.println("entrer les coordonn\u00e9es de la case cible dans le m\u00eame format");
    System.out.print("Saisie : ");

    //deuxième coord
    choix=scan.nextLine();
    //vérif du bon format des coord
    while(!checkCoordLettre(choix.charAt(0))||!checkCoordChiffre(choix)){
      System.out.println("le format n'est pas le bon, donner une lettre entre A et K et un chiffre entre 0 et 10");
      System.out.print("Saisie : ");
      choix=scan.nextLine();
    }

    //transforme les rentrer en int pour la suite du jeu
    int x2=turnInNumber(choix.charAt(0));
    int y2=turnInInt(choix);
    tab[0]=x1;
    tab[1]=y1;
    tab[2]=x2;
    tab[3]=y2;

    return tab;
  }

  /**
  *regarde à partir de la saisie du joueur si la coordonnees chiffre est bien
  *comprise entre 0 et 10, la saisie doit être de forme lettre,chiffre
  *@param coord la saisie du joueur à regarder
  *@return vrai si au bon format faux sinon
  *@throws IllegalArgumentException si le parametre est null
  */
  public static boolean checkCoordChiffre(String coord){
    boolean ret=false;
    //System.out.println(coord.length());
    if(coord==null) throw new IllegalArgumentException("checkCoordChiffre.parametre ne peuevent pas être null");
    //pour 10
    if(coord.length()==4){
      if(coord.charAt(2)=='1'&&coord.charAt(3)=='0'){
        ret=true;
      }
      //de 0 à 9
    }else if(coord.length()==3){
      String chiffre=""+coord.charAt(2);
      if(chiffre.matches("[0-9]")){
        ret=true;
      }
    }
    return ret;
  }

  /**
  *transforme des chiffre dans un String (saisie du joueur dans la console) en entier
  *la saisie doit être de forme lettre,chiffre
  *@param coord la saisie du joueur à vérifier
  *@return l'entier correspondant au chiffre dans le String, sinon -1
  *@throws IllegalArgumentException si les parametre sont null
  */
  public static int turnInInt(String coord){
    int posY=-1;

    if(coord==null)throw new IllegalArgumentException("turnInInt.parametre ne peuvent pas être null");
    //pour 10
    if(coord.length()==4 &&coord.charAt(2)=='1'&&coord.charAt(3)=='0'){
      posY=10;
      //de 0 à 9
    }else if(coord.length()==3){
      char chiffre=coord.charAt(2);
      if(chiffre=='0'){
        posY=0;
      }else if(chiffre=='1'){
        posY=1;
      }else if(chiffre=='2'){
        posY=2;
      }else if(chiffre=='3'){
        posY=3;
      }else if(chiffre=='4'){
        posY=4;
      }else if(chiffre=='5'){
        posY=5;
      }else if(chiffre=='6'){
        posY=6;
      }else if(chiffre=='7'){
        posY=7;
      }else if(chiffre=='8'){
        posY=8;
      }else if(chiffre=='9'){
        posY=9;
      }
    }
    return posY;
  }

  /**
  *transforme une lettre comprise entre a-k ou A-k entre un chiffre compris en 0 et 10
  *@param x le char à transformer
  *@return l'entier correspondant sinon -1
  */
  public static int turnInNumber(char x){
    int posX=-1;
    String turnX=String.valueOf(x);

    if(turnX.equalsIgnoreCase("a")){
      posX=0;
    }else if(turnX.equalsIgnoreCase("b")){
      posX=1;
    }else if(turnX.equalsIgnoreCase("c")){
      posX=2;
    }else if(turnX.equalsIgnoreCase("d")){
      posX=3;
    }else if(turnX.equalsIgnoreCase("e")){
      posX=4;
    }else if(turnX.equalsIgnoreCase("f")){
      posX=5;
    }else if(turnX.equalsIgnoreCase("g")){
      posX=6;
    }else if(turnX.equalsIgnoreCase("h")){
      posX=7;
    }else if(turnX.equalsIgnoreCase("i")){
      posX=8;
    }else if(turnX.equalsIgnoreCase("j")){
      posX=9;
    }else if(turnX.equalsIgnoreCase("k")){
      posX=10;
    }
    return posX;
  }

  /**
  *demande au joueur par la console, s'il veut jouer en mode graphique ou en mode console
  *@return 1 si en mode console, 2 en mode graphique
  */
  public static int choixGraphique(){
    int ret=0;
    System.out.println();
    System.out.println("** Veuillez choisir le mode graphique du jeu:");
    System.out.println("1 --- mode console");
    System.out.println("2 --- mode graphique");
    System.out.println();
    System.out.print("Saisie : ");

    Scanner scan=new Scanner(System.in);
    String choix=scan.nextLine();
    while(!choix.equals("1") && !choix.equals("2") ){
      System.out.println("veuillez saisir 1 ou 2 en fonction de votre choix");
      System.out.print("Saisie : ");
      choix=scan.nextLine();
    }
    if(choix.equals("1")){
      ret=1;
    }else if(choix.equals("2")){
      ret=2;
    }
    return ret;
  }
}//end
