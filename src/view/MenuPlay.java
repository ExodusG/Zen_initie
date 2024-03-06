package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import controleur.Controleur;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

/**
*cette class permet de créer et de gérer un panel qui affiche un menu de jeu avec 4 boutons
*Multijoueur,IA, retour et charger
*@author Q.Le Lan
*/
public class MenuPlay{

  /**
  *le bouton multi
  */
  private JButton multi;

  /**
  *le bouton IA
  */
  private JButton IA;

  /**
  *le bouton charger une ancienne partie
  */
  private JButton charger;

  /**
  *le bouton de retour
  */
  private JButton retour;

  /**
  *le panel créé par cette classe
  */
  private ImagePanel menu;

  /**
  *le constru de MenuPlay
  */
  public MenuPlay(){
    initComponents();
  }

  /**
  *permet d'initialiser le panel et tous ses elements
  */
  private void initComponents(){
    Font font2 = new Font("Arial",Font.BOLD,15);
    Font font = new Font("Arial",Font.BOLD,16);

    this.multi=new JButton("<html> <u>Multijoueur</u></html>");
    this.multi.setBorderPainted(false);
    this.multi.setContentAreaFilled(false);
    this.multi.setForeground(Color.YELLOW);
    this.multi.setFont(font2);

    this.IA=new JButton("<html> <u>Contre IA</u></html>");
    this.IA.setBorderPainted(false);
    this.IA.setContentAreaFilled(false);
    this.IA.setForeground(Color.YELLOW);
    this.IA.setFont(font2);

    this.charger=new JButton("<html> <u>Charger une partie</u></html>");
    this.charger.setBorderPainted(false);
    this.charger.setContentAreaFilled(false);
    this.charger.setForeground(Color.YELLOW);
    this.charger.setFont(font2);

    this.retour=new JButton("<html> <u>retour</u></html>");
    this.retour.setBorderPainted(false);
    this.retour.setContentAreaFilled(false);
    this.retour.setForeground(Color.YELLOW);
    this.retour.setFont(font2);
    this.menu=new ImagePanel(new ImageIcon(getClass().getResource(Path.FOND3.getPath())).getImage());
    JLabel info=new JLabel("<html>Veuillez choisir un mode de jeu, ou cliquez sur charger une partie pour reprendre une ancienne partie sauvegard\u00e9e sur votre ordinateur</html>");
    info.setForeground(Color.YELLOW);
    info.setFont(font);

    BorderLayout layout = new BorderLayout();
    this.menu.setLayout(layout);

    JPanel pan7=new JPanel();
    JPanel pan8=new JPanel();
    JPanel pan9=new JPanel();
    JPanel pan10=new JPanel();
    JPanel pan11=new JPanel();
    JPanel pan12=new JPanel();
    JPanel pan13=new JPanel();
    JPanel pan14=new JPanel();
    JPanel pan15=new JPanel();
    JPanel pan16=new JPanel();

    pan7.setOpaque(false);
    pan8.setOpaque(false);
    pan9.setOpaque(false);
    pan10.setOpaque(false);
    pan11.setOpaque(false);
    pan12.setOpaque(false);
    pan13.setOpaque(false);
    pan14.setOpaque(false);
    pan15.setOpaque(false);
    pan16.setOpaque(false);

    JPanel bouton1=new JPanel();
    bouton1.setLayout(new GridLayout(1,3));
    bouton1.setOpaque(false);

    bouton1.add(pan7);
    bouton1.add(info);
    bouton1.add(pan8);

    JPanel bouton2=new JPanel();
    bouton2.setLayout(new GridLayout(1,3));
    bouton2.setOpaque(false);

    bouton2.add(pan9);
    bouton2.add(this.multi);
    bouton2.add(pan10);

    JPanel bouton3=new JPanel();
    bouton3.setLayout(new GridLayout(1,3));
    bouton3.setOpaque(false);

    bouton3.add(pan11);
    bouton3.add(this.IA);
    bouton3.add(pan12);

    JPanel bouton4=new JPanel();
    bouton4.setLayout(new GridLayout(1,3));
    bouton4.setOpaque(false);

    bouton4.add(pan13);
    bouton4.add(this.charger);
    bouton4.add(pan14);

    JPanel bouton5=new JPanel();
    bouton5.setLayout(new GridLayout(1,3));
    bouton5.setOpaque(false);

    bouton5.add(pan15);
    bouton5.add(this.retour);
    bouton5.add(pan16);

    JPanel bouton=new JPanel();
    bouton.setLayout(new GridLayout(11,1));
    bouton.setOpaque(false);

    JPanel pan1=new JPanel();
    JPanel pan2=new JPanel();
    JPanel pan3=new JPanel();
    JPanel pan4=new JPanel();
    JPanel pan5=new JPanel();
    JPanel pan6=new JPanel();

    pan1.setOpaque(false);
    pan2.setOpaque(false);
    pan3.setOpaque(false);
    pan4.setOpaque(false);
    pan5.setOpaque(false);
    pan6.setOpaque(false);

    bouton.add(pan1);
    bouton.add(bouton1);
    bouton.add(pan2);
    bouton.add(bouton2);
    bouton.add(pan3);
    bouton.add(bouton3);
    bouton.add(pan4);
    bouton.add(bouton4);
    bouton.add(pan5);
    bouton.add(bouton5);
    bouton.add(pan6);

    this.menu.add(bouton,BorderLayout.CENTER);

  }

  /**
  *ajoute le controleur en ecouteur d'action
  *ajoute au 4 boutons
  *@param controleur le controleur qui écoutera
  *@throws IllegalArgumentException si le param est null
  */
  public void addAction(Controleur controleur){
    if(controleur==null)throw new IllegalArgumentException("addAction.le parametre est null");

    this.retour.addActionListener(controleur);
    this.multi.addActionListener(controleur);
    this.charger.addActionListener(controleur);
    this.IA.addActionListener(controleur);
  }

  /**
  *le getteur du panel que construit cette class
  *@return le panel
  */
  public JPanel getMenu(){
    return this.menu;
  }

  /**
  *le getteur du bouton retour
  *@return le bouton retour
  */
  public JButton getRetour(){
    return this.retour;
  }

  /**
  *le getteur du bouton charger
  *@return le bouton charger
  */
  public JButton getCharger(){
    return this.charger;
  }

  /**
  *le getteur du bouton IA
  *@return le bouton IA
  */
  public JButton getIA(){
    return this.IA;
  }

  /**
  *le getteur du bouton Multijoueur
  *@return le bouton du Multijoueur
  */
  public JButton getMultijoueur(){
    return this.multi;
  }
}
