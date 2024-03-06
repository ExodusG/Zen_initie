package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import controleur.Controleur;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;

/**
*cette classe permet de créer et de gerer un panel et tous ses composant
*le panel permet d'entrer 2 noms et posséde 2 boutons : jouer et retour
*@author Q.Le Lan
*/
public class MultiPlayer{

  /**
  *le panel créé par cette classe
  */
  private ImagePanel multi;

  /**
  *le JTextField pour le nom1
  */
  private JTextField nom1;

  /**
  *le JTextField pour le nom2
  */
  private JTextField nom2;

  /**
  *le bouton pour retour
  */
  private JButton retour;

  /**
  *le bouton pour jouer
  */
  private JButton jouer;

  /**
  *pour afficher les mouves dispo ou non
  */
  private JCheckBox check;


  /**
  *constructeur de MultiPlayer
  */
  public MultiPlayer(){
    initComponents();
  }

  /**
  *permet d'initialiser le panel et tous ses elements
  */
  private void initComponents(){
    Font font = new Font("Arial",Font.BOLD,16);
    this.multi=new ImagePanel(new ImageIcon(getClass().getResource(Path.FOND.getPath())).getImage());
    this.nom1=new JTextField();
    this.nom2=new JTextField();

    JLabel labelNom1=new JLabel("nom du joueur 1");
    labelNom1.setFont(font);
    labelNom1.setForeground(Color.YELLOW);

    JLabel labelNom2=new JLabel("nom du joueur 2");
    labelNom2.setFont(font);
    labelNom2.setForeground(Color.YELLOW);

    this.check=new JCheckBox();
    this.check.setOpaque(false);

    this.retour=new JButton("<html> <u>retour</html> </u>");
    this.retour.setBorderPainted(false);
    this.retour.setContentAreaFilled(false);
    this.retour.setForeground(Color.YELLOW);
    this.retour.setFont(font);

    this.jouer=new JButton("<html> <u>jouer</u></html>");
    this.jouer.setBorderPainted(false);
    this.jouer.setContentAreaFilled(false);
    this.jouer.setForeground(Color.YELLOW);
    this.jouer.setFont(font);

    JLabel box=new JLabel("<html> afficher les d\u00e9placements possibles</html>");
    box.setFont(font);
    box.setForeground(Color.YELLOW);
    JLabel info=new JLabel("<html> <u>Multijoueur</u></html>");
    info.setFont(new Font("Arial",Font.BOLD,22));
    info.setForeground(Color.YELLOW);
    info.setHorizontalAlignment(SwingConstants.CENTER);

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
    JPanel pan17=new JPanel();
    JPanel pan18=new JPanel();
    JPanel pan19=new JPanel();
    JPanel pan20=new JPanel();
    JPanel pan21=new JPanel();
    JPanel pan22=new JPanel();
    JPanel pan23=new JPanel();

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
    pan17.setOpaque(false);
    pan18.setOpaque(false);
    pan19.setOpaque(false);
    pan20.setOpaque(false);
    pan21.setOpaque(false);
    pan22.setOpaque(false);
    pan23.setOpaque(false);

    JPanel label=new JPanel();
    label.setOpaque(false);
    label.setLayout(new GridLayout(1,3));
    label.add(pan1);
    label.add(info);
    label.add(pan2);


    JPanel center=new JPanel();
    center.setOpaque(false);
    GridLayout layout=new GridLayout(3,5);
    center.setLayout(layout);
    //layout.setVgap(20);

    center.add(pan3);
    center.add(labelNom1);
    center.add(pan4);
    center.add(this.nom1);
    center.add(pan5);

    center.add(pan6);
    center.add(pan7);
    center.add(pan8);
    center.add(pan9);
    center.add(pan10);

    center.add(pan11);
    center.add(labelNom2);
    center.add(pan12);
    center.add(this.nom2);
    center.add(pan13);

    JPanel center2=new JPanel(new GridLayout(1,5));
    center2.setOpaque(false);

    JPanel panCenter=new JPanel();
    JPanel panCenter2=new JPanel();
    JPanel panCenter3=new JPanel();
    panCenter.setOpaque(false);
    panCenter2.setOpaque(false);
    panCenter3.setOpaque(false);

    center2.add(panCenter);
    center2.add(box);
    center2.add(panCenter2);
    center2.add(check);
    center2.add(panCenter3);

    JPanel center4=new JPanel(new GridLayout(6,1));
    center4.setOpaque(false);
    JPanel panCenter4=new JPanel();
    panCenter4.setOpaque(false);
    center4.add(pan15);
    center4.add(pan16);
    center4.add(center);
    center4.add(pan17);
    center4.add(center2);
    center4.add(panCenter4);

    JPanel sud=new JPanel();
    sud.setOpaque(false);
    sud.setLayout(new GridLayout(1,5));

    sud.add(pan18);
    sud.add(this.jouer);
    sud.add(pan19);
    sud.add(this.retour);
    sud.add(pan21);

    JPanel sud2=new JPanel(new GridLayout(3,1));
    sud2.setOpaque(false);
    sud2.add(pan22);
    sud2.add(sud);
    sud2.add(pan23);

    JPanel center3=new JPanel(new GridLayout(2,1));
    center3.setOpaque(false);
    center3.add(center4);
    center3.add(sud2);

    this.multi.setLayout(new BorderLayout());
    this.multi.add(label,BorderLayout.NORTH);
    //this.multi.add(sud,BorderLayout.SOUTH);
    this.multi.add(center3,BorderLayout.CENTER);
  }

  /**
  *ajoute le controleur en ecouteur d'action
  *ajoute au 2 bouton
  *@param controleur le controleur qui écoutera
  *@throws IllegalArgumentException si le param est null
  */
  public void addAction(Controleur controleur){
    if(controleur==null)throw new IllegalArgumentException("MultiPlayer.addAction.le parametre est null");
    this.jouer.addActionListener(controleur);
    this.retour.addActionListener(controleur);
  }

  /**
  *le getteur du bouton retour
  *@return le bouton retour
  */
  public JButton getRetour(){
    return this.retour;
  }

  /**
  *le getteur du bouton jouer
  *@return le bouton jouer
  */
  public JButton getJouer(){
    return this.jouer;
  }

  /**
  *le getteur du JTextField qui contient le nom 1
  *@return JTextField 1
  */
  public JTextField getFieldNom1(){
    return this.nom1;
  }

  /**
  *le getteur du JTextField qui contient le nom 2
  *@return JTextField 2
  */
  public JTextField getFieldNom2(){
    return this.nom2;
  }

  /**
  *le getteur du panel construit par cette classe
  *@return le panel
  */
  public JPanel getPanel(){
    return this.multi;
  }

  /**
  *guetteur du JCheckBox
  *@return la JCheckBox
  */
  public JCheckBox getBox(){
    return this.check;
  }
}
