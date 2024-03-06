package view;

/**
*permet de retenir et modifier facilement le chemin d'image,
*utilise pour la partie modele, il faut mettre un chemin absolue en partant de la racine du .jar ou du dossier class
*@author Q.Le Lan
*/
public enum Path{

  /**
  *le chemin de l'image vide
  */
  VIDE("/img/case/vide4.png"),

  /**
  *le chemin de l'image du pion noir
  */
  NOIR("/img/case/noir4.png"),

  /**
  *le chemin de l'image du pion blanc
  */
  BLANC("/img/case/blanc4.png"),

  /**
  *le chemin de l'image du pion rouge
  */
  ROUGE("/img/case/rouge4.png"),

  /**
  *le chemin de l'image du pion rouge selectionné
  */
  ROUGES("/img/caseS/selectRouge.png"),

  /**
  *le chemin de l'image du pion noir selectionné
  */
  NOIRS("/img/caseS/selectNoir.png"),

  /**
  *le chemin de l'image du pion blanc selectionné
  */
  BLANCS("/img/caseS/selectBlanc.png"),

  /**
  *le chemin de l'image de la case vide selectionné
  */
  VIDES("/img/caseS/selectVide.png"),

  /**
  *le chemin de l'image de pion blanc sur fond noir
  */
  BLANCN("/img/caseN/blanc.png"),

  /**
  *le chemin de l'image de pion noir sur fond noir
  */
  NOIRN("/img/caseN/noir.png"),

  /**
  *le chemin de l'image de pion rouge sur fond noir
  */
  ROUGEN("/img/caseN/rouge.png"),

  /**
  *le chemin de l'image de la case vide avec fond noir 
  */
  VIDEN("/img/caseN/vide.png"),
  /**
  *le chemin de l'image de fond
  */
  FOND("/img/fond/fond.jpg"),

  /**
  *le chemin de l'image de fond3
  */
  FOND3("/img/fond/fond3.png"),

  /**
  *le chemin de l'image du logo
  */
  LOGO("/img/fond/logoVoid2.png"),

  /**
  *le chemin du fichier wav de la musique
  */
  SOUND("/sound/boucle.wav");

  /**
  *le chemin de l'enumeration
  */
  private String path;

  /**
  *constructeur de path
  *@param path le chemin jusqu'a l'image
  */
  Path(String path){
    this.path=path;
  }

  /**
  *le getteur du chemin
  *@return le chemin
  */
  public String getPath(){
    return this.path;
  }
}
