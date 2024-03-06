package utilitaire;

import java.awt.Desktop;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import jeu.Jeu;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
*cette classe contient des méthode utile pour le mode console comme par exemple le choix du mode de jeu,
le choix du nom, etc...
*@author Q.Le Lan
*/
public class Utilitaire {


  /**
  *ouvre un JFileChooser avec un filtre d'extension(.ze)
  *@return le fichier choisi par l'utilisateur
  */
  public static File openFile(){
    File file=null;
    JFileChooser filec=new JFileChooser(".");
    FileFilter filtre = new FileNameExtensionFilter(".ze","ze");
    filec.setFileFilter (filtre);
    filec.addChoosableFileFilter(filtre);
    int returnVal = filec.showOpenDialog(null);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
      file=filec.getSelectedFile();
    }
    return file;
  }

  /**
  *ouvre une JOptionPane contenant les règles du jeu
  */
  public static void afficheRegle(){
    JOptionPane jop1=new JOptionPane();
    JOptionPane.showMessageDialog(null,"Voici les r\u00e8gles du jeu Zen l'initi\u00e9 : \n 2 joueurs s'affrontent, le but \u00e9tant de former une chaine avec tous ses pions y compris le zen si il est encore en jeu \n Les pions sont d\u00e9pla\u00e7ables seulement : \n -Si vous le d\u00e9place d'autant de case qu'il y a de pions sur la ligne de d\u00e9placement (y compris lui m\u00eame) \n -Si vous le deplacez en ligne droite, vous pouvez donc le d\u00e9placer dans les 8 directions possibles \n \n  -Vous ne pouvez pas passer au dessus d'un pion adverse \n \n Il existe un pion sp\u00e9ciale, le pion Zen, tout le monde peut le d\u00e9placer mais il doit etre connect\u00e9 \u00e0 un pion \u00e0 son arriv\u00e9e \n S'il y a un pion sur la case o\u00f9 vous vous d\u00e9placez vous mangez le pion adverse (ou le zen), et celui-ci est retir\u00e9 du jeu \n \n Pour toutes autres informations, consultez le pdf \"r\u00e8gles\" ","r\u00e8gles du jeu",JOptionPane.INFORMATION_MESSAGE);
  }

  /**
  *ouvre un JFileChooser et demande a l'utilisateur le nom et emplacement de
  *l'endroit de sauvegarde
  *@param jeu la partie à sauvegarder
  *@throws IllegalArgumentException si le jeu est null
  */
  public static void sauvePartie(Jeu jeu){
    ObjectOutputStream oos=null;
    try{
      if(jeu==null)throw new IllegalArgumentException("Utilitaire.sauvePartie. le parametre est null");

      JFrame parentFrame = new JFrame();
      JFileChooser fileChooser = new JFileChooser();
      int userSelection = fileChooser.showSaveDialog(parentFrame);

      if (userSelection == JFileChooser.APPROVE_OPTION) {
          File fileToSave = fileChooser.getSelectedFile();
          oos =  new ObjectOutputStream(new FileOutputStream(fileToSave)) ;
          oos.writeObject(jeu);
      }
    }catch(FileNotFoundException e){
      System.err.println(e.getMessage());
    }catch(IOException e){
      System.err.println(e.getMessage());
    }finally{
      if(oos!=null){
        try{
          oos.close();
        }catch(IOException e){
          System.out.println(e.getMessage());
        }
      }
    }
  }

  /**
  *ouvre un JFileChooser et demande à l'utilisiteur de choisir un fichier de sauvegarde
  *@return le jeu sauvegarder sans le fichier
  */
  public static Jeu openPartie(){
    Jeu jeu1=null;
    ObjectInputStream ois=null;
    try{
      FileFilter filtre = new FileNameExtensionFilter(".ze","ze");
      File fichier = openFile();
      while(!filtre.accept(fichier)){
        fichier = openFile();
      }

      ois =  new ObjectInputStream(new FileInputStream(fichier)) ;
      jeu1 = (Jeu)ois.readObject();
    }catch(IOException | ClassNotFoundException e){
      System.err.println(e.getMessage());
    }finally{
      if(ois!=null){
        try{
          ois.close();
        }catch(IOException e){
          System.out.println(e.getMessage());
        }
      }
      return jeu1;
    }
  }
}
