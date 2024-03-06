package view;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Image;
import controleur.Controleur;
import jeu.joueur.JoueurHumain;
import jeu.Plateau;
import jeu.Jeu;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
*class qui gere la frame de l'interface graphique et donc qui gere tout les panels et autres classe du package view
*permet de gérer la musique
*c'est donc la view du modele MVC
*@author Q.Le Lan
*/
public class Vue{

  /**
  *l'accueil de l'interface
  */
  private Accueil acc;

  /**
  *le menu de l'interface
  */
  private MenuPlay menu;

  /**
  *la frame de l'interface
  */
  private JFrame vue;

  /**
  *le menu du Multijoueur de l'interface
  */
  private MultiPlayer multi;

  /**
  *le plateau et autre de l'interface
  */
  private Partie partie;

  /**
  *le controleur pour les composant graphique
  */
  private Controleur ctrl;

  /**
  *le menuIA de l'interface
  */
  private MenuIA menuIA;

  /**
  *permet de gérer la musique
  */
  private Clip clip;

  /**
  *construteur de la vue
  */
  public Vue(){
    try{

      clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(getClass().getResource(Path.SOUND.getPath())));
      clip.start();
      clip.loop(Clip.LOOP_CONTINUOUSLY);

      this.acc=new Accueil();
      this.menu=new MenuPlay();
      this.vue=new JFrame();
      this.multi=new MultiPlayer();
      this.menuIA=new MenuIA();

      this.vue.add(acc.getAccueil());
      this.vue.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      this.vue.setVisible(true);
      this.vue.setSize(1300,900);
      this.vue.setTitle("Zen l'initi\u00e9");
      this.vue.setIconImage(new ImageIcon(getClass().getResource(Path.LOGO.getPath())).getImage());
      setControleur();

    }catch(LineUnavailableException | UnsupportedAudioFileException | IOException e ){
      System.out.println(e.getMessage());
    }
  }

  /**
  *permet d'ajouter le controleur au element graphique
  *du menu, accueil, multi
  */
  private void setControleur(){
    this.ctrl=new Controleur(this);
    this.menu.addAction(ctrl);
    this.acc.addAction(ctrl);
    this.multi.addAction(ctrl);
    this.menuIA.addAction(ctrl);
  }

  /**
  *le getteur de l'accueil
  *@return l'accueil
  */
  public Accueil getAcc(){
    return this.acc;
  }

  /**
  *le getteur du menu
  *@return le menu
  */
  public MenuPlay getMenu(){
    return this.menu;
  }

  /**
  *le getteur du multi
  *@return le multi
  */
  public MultiPlayer getMulti(){
    return this.multi;
  }

  /**
  *le getteur de la partie
  *@return la partie
  */
  public Partie getPartie(){
    return this.partie;
  }

  /**
  *le getteur du menuIA
  *@return le MenuIA
  */
  public MenuIA getMenuIA(){
    return this.menuIA;
  }

  /**
  *permet de setter la partie graphique : partie
  *et d'y ajouter son controleur
  *@param j le jeu pour la partie
  *@throws IllegalArgumentException si le jeu est null
  */
  public void setPartie(Jeu j){
    if(j==null)throw new IllegalArgumentException("setPartie.le parametre est null");
    this.partie=new Partie(j.getPlateu().getPlateau());
    this.partie.addAction(this.ctrl);
  }

  /**
  *le getteur de la frame
  *@return la frame de l'interface
  */
  public JFrame getFrame(){
    return this.vue;
  }

  /**
  *éteiny la musique
  */
  public void setMusique(){
    clip.stop();
  }

  /**
  *démarre la musique
  */
  public void setMusiqueTrue(){
    clip.start();
  }
  /**
  *permet de modifier le panel de la frame
  *le panel prendre la frame entièrement
  *@param panel le panel qui serra dans la frame
  *@throws IllegalArgumentException si le panel est null
  */
  public void setPanel(JPanel panel){
    if(panel==null)throw new IllegalArgumentException("setPanel.le parametre est null");
    this.vue.getContentPane().removeAll();
    this.vue.getContentPane().add(panel);
    this.vue.getContentPane().validate();
    this.vue.getContentPane().repaint();
  }
}
