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
*le panel est composé de 2 bouton retour et regle ainsi qu'un JTextField pour demander le prenom
*@author Q.Le Lan
*/
public class MenuIA {

  /**
  *le panel créer
  */
  private ImagePanel multi;

  /**
  *le JTextField du nom
  */
  private JTextField nom1;

  /**
  *le bouton retour
  */
  private JButton retour;

  /**
  *le bouton jouer
  */
  private JButton jouer;

  /**
  *pour afficher les mouves dispo ou non
  */
  private JCheckBox check;


  /**
  *constructeur de MultiPlayer
  */
  public MenuIA(){
    initComponents();
  }

  /**
  *permet d'initialiser le panel et tous ses elements
  */
  private void initComponents(){
    Font font = new Font("Arial",Font.BOLD,16);
    this.multi=new ImagePanel(new ImageIcon(getClass().getResource(Path.FOND.getPath())).getImage());
    this.nom1=new JTextField();
    JLabel labelNom1=new JLabel("nom du joueur 1");
    labelNom1.setFont(font);
    labelNom1.setForeground(Color.YELLOW);

    this.retour=new JButton("<html> <u>retour</html> </u>");
    this.retour.setBorderPainted(false);
    this.retour.setContentAreaFilled(false);
    this.retour.setForeground(Color.YELLOW);
    this.retour.setFont(font);

    this.jouer=new JButton("<html> <u>jouer</html> </u>");
    this.jouer.setBorderPainted(false);
    this.jouer.setContentAreaFilled(false);
    this.jouer.setForeground(Color.YELLOW);
    this.jouer.setFont(font);

    this.check=new JCheckBox();
    this.check.setOpaque(false);
    JLabel box=new JLabel("<html> afficher les d\u00e9placements possibles</html>");
    box.setFont(font);
    box.setForeground(Color.YELLOW);

    JLabel info = new JLabel("Contre IA");
    info.setFont(new Font("Arial",Font.BOLD,22));
    info.setForeground(Color.YELLOW);
    info.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel pan1=new JPanel();
    JPanel pan2=new JPanel();
    JPanel pan3=new JPanel();
    JPanel pan4=new JPanel();
    JPanel pan5=new JPanel();
    JPanel pan6=new JPanel();
    JPanel pan7=new JPanel();
    JPanel pan8=new JPanel();
    JPanel pan9=new JPanel();
    JPanel pan10=new JPanel();
    JPanel pan11=new JPanel();
    JPanel pan12=new JPanel();

    pan1.setOpaque(false);
    pan2.setOpaque(false);
    pan3.setOpaque(false);
    pan4.setOpaque(false);
    pan5.setOpaque(false);
    pan6.setOpaque(false);
    pan7.setOpaque(false);
    pan8.setOpaque(false);
    pan9.setOpaque(false);
    pan10.setOpaque(false);
    pan11.setOpaque(false);
    pan12.setOpaque(false);

    JPanel top=new JPanel();
    top.setLayout(new GridLayout(3,1));
    top.setOpaque(false);

    top.add(pan1);
    top.add(info);
    top.add(pan2);

    JPanel center=new JPanel();
    center.setLayout(new GridLayout(1,5));
    center.setOpaque(false);

    center.add(pan3);
    center.add(labelNom1);
    center.add(pan4);
    center.add(this.nom1);
    center.add(pan12);

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

    JPanel center3=new JPanel(new GridLayout(5,1));
    center3.setOpaque(false);
    JPanel panCenter4=new JPanel();
    panCenter4.setOpaque(false);

    center3.add(pan5);
    center3.add(center);
    center3.add(pan6);
    center3.add(center2);
    center3.add(panCenter4);

    JPanel sud=new JPanel();
    sud.setLayout(new GridLayout(1,5));
    sud.setOpaque(false);

    sud.add(pan7);
    sud.add(this.jouer);
    sud.add(pan8);
    sud.add(this.retour);
    sud.add(pan9);

    JPanel sud2=new JPanel(new GridLayout(3,1));
    sud2.setOpaque(false);
    sud2.add(pan10);
    sud2.add(sud);
    sud2.add(pan11);

    JPanel pane=new JPanel(new GridLayout(2,1));
    pane.setOpaque(false);
    pane.add(center3);
    pane.add(sud2);
    this.multi.setLayout(new BorderLayout());

    this.multi.add(top,BorderLayout.NORTH);
    this.multi.add(pane,BorderLayout.CENTER);
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
