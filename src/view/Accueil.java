package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import controleur.Controleur;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
/**
*cette classe permet de créer et de gerer un panel et tous ses composant
*le panel est composé de 3 boutons : jouer, regle et quitter
*@author Q.Le Lan
*/
public class Accueil{

  /**
  *bouton jouer
  */
  private JButton jouer;

  /**
  *bouton regle
  */
  private JButton regle;

  /**
  *bouton quitter
  */
  private JButton quitter;

  /**
  *imagePanel accueil
  */
  private ImagePanel accueil;

  /**
  *le construteur de accueil
  */
  public Accueil(){
    initComponents();
  }

  /**
  *permet d'initialiser le panel et tous ses elements
  */
  private void initComponents(){
    Font font2 = new Font("Arial",Font.BOLD,15);
    this.jouer=new JButton("<html> <u>jouer</u></html>");
    this.jouer.setBorderPainted(false);
    this.jouer.setContentAreaFilled(false);
    this.jouer.setForeground(Color.YELLOW);
    this.jouer.setFont(font2);

    this.regle=new JButton("<html> <u>r\u00e8gles</u></html>");
    this.regle.setBorderPainted(false);
    this.regle.setContentAreaFilled(false);
    this.regle.setForeground(Color.YELLOW);
    this.regle.setFont(font2);

    this.quitter=new JButton("<html> <u>quitter</u></html>");
    this.quitter.setBorderPainted(false);
    this.quitter.setContentAreaFilled(false);
    this.quitter.setForeground(Color.YELLOW);
    this.quitter.setFont(font2);
    this.accueil=new ImagePanel(new ImageIcon(getClass().getResource(Path.FOND3.getPath())).getImage());
    JLabel logo=new JLabel(new ImageIcon(new ImageIcon(getClass().getResource(Path.LOGO.getPath())).getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH)));
    BorderLayout layout = new BorderLayout();
    this.accueil.setLayout(layout);

    JLabel text=new JLabel("<html> <br><br><br> Bienvenue dans le jeu : Zen l'initi\u00e9! <br> Ce logiciel est une adaption du c\u00e9l\u00e8bre jeu de plateau qui porte le m\u00eame nom sorti en 1997. Vous pouvez jouer \u00e0 2 (1vs1) ou jouer contre l'IA</html>");
    text.setForeground(Color.YELLOW);
    Font font = new Font("Arial",Font.BOLD,16);
    text.setFont(font);

    JPanel textPanel=new JPanel(new GridLayout(1,3));
    textPanel.setOpaque(false);

    JPanel pan4=new JPanel();
    JPanel pan5=new JPanel();
    pan4.setOpaque(false);
    pan5.setOpaque(false);

    textPanel.add(pan4);
    textPanel.add(text);
    textPanel.add(pan5);

    JPanel bouton=new JPanel();
    bouton.setOpaque(false);
    bouton.setLayout(new GridLayout(7,1));

    JPanel pan1=new JPanel();
    JPanel pan2=new JPanel();
    JPanel pan3=new JPanel();

    pan1.setOpaque(false);
    pan3.setOpaque(false);
    pan2.setOpaque(false);

    bouton.add(this.jouer);
    bouton.add(pan1);
    bouton.add(this.regle);
    bouton.add(pan2);
    bouton.add(this.quitter);
    bouton.add(pan3);

    JPanel fin = new JPanel();
    fin.setOpaque(false);
    fin.setLayout(new GridLayout(2,1));

    fin.add(textPanel);
    fin.add(bouton);
    this.accueil.add(fin,BorderLayout.CENTER);
    this.accueil.add(logo,BorderLayout.NORTH);
  }

  /**
  *ajoute le controleur en ecouteur d'action
  *ajoute au 3 boutons
  *@param controleur le controleur qui écoutera
  *@throws IllegalArgumentException si le param est null
  */
  public void addAction(Controleur controleur){
    if(controleur==null)throw new IllegalArgumentException("Accueil.addAction.le parametre est null");
    this.jouer.addActionListener(controleur);
    this.regle.addActionListener(controleur);
    this.quitter.addActionListener(controleur);
  }

  /**
  *le getteur du panel construit par la class
  *@return le panel
  */
  public JPanel getAccueil(){
    return this.accueil;
  }

  /**
  *le getteur du bouton retour
  *@return le bouton retour
  */
  public JButton getRetour(){
    return this.quitter;
  }

  /**
  *le getteur du bouton jouer
  *@return le bouton jouer
  */
  public JButton getJouer(){
    return this.jouer;
  }

  /**
  *le getteur du bouton regle
  *@return le bouton regle
  */
  public JButton getRegle(){
    return this.regle;
  }
}
