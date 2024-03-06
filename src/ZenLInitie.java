import jeu.*;
import jeu.joueur.*;
import utilitaire.*;
import view.Vue;
import javax.swing.JOptionPane;
import controleur.ControleurConsole;
/**
*cette classe est la classe lanceur du jeu, elle contient le main.
*@author Q.Le Lan
*/
public class ZenLInitie {

  /**
  *lanceur du jeu
  *demande en version graphique ou console
  *@param args les params de la ligne de commande
  */
  public static void main(String[] args){
    int choixMode=ModeConsole.choixGraphique();
    //mode console
    if(choixMode==1){
      ControleurConsole ctrl=new ControleurConsole();
      ctrl.modeConsole();
    }else{
      //mode graphique
      java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
          Vue vue=new Vue();
        }
      });
    }
  }
}//end
